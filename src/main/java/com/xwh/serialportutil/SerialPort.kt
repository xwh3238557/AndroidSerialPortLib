package com.xwh.serialportutil

import java.io.*
import java.lang.Exception
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.withLock


/**
 * Created by WenhaoXia on 2017/11/7.
 */
open class SerialPort(val device: File, val baudrate: Int, val flags: Int = 0) {
    /**
     * native methods
     */
    private external fun open(path: String, baudrate: Int, flags: Int): FileDescriptor?

    private external fun close()

    private val readWriteLock = ReentrantReadWriteLock()

    private lateinit var fileDescriptor: FileDescriptor
    private lateinit var fileInputStream: FileInputStream
    private lateinit var fileOutputStream: FileOutputStream

    constructor(devicePath: String, baudrate: Int) : this(File(devicePath), baudrate)

    init {
        System.loadLibrary("serialport-lib")

        if (!device.canRead() || !device.canWrite()) {
            try {
                val su = Runtime.getRuntime().exec("/system/bin/su")
                val cmd = ("chmod 666 " + device.absolutePath + "\n" + "exit\n")
                su.outputStream.write(cmd.toByteArray())
                if (su.waitFor() != 0 || !device.canRead() || !device.canWrite()) {
                    throw SecurityException()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                throw SecurityException()
            }
        }
        fileDescriptor = open(device.absolutePath, baudrate, flags)?.also {
            fileInputStream = FileInputStream(it)
            fileOutputStream = FileOutputStream(it)
        } ?: throw IOException()
    }

    fun write(cmd: ByteArray) {
        readWriteLock.writeLock().withLock { fileOutputStream.write(cmd) }
    }

    fun read(): ByteArray? {
        readWriteLock.readLock().withLock {
            return fileInputStream.available().let { available ->
                if (available == 0) {
                    null
                } else {
                    ByteArray(available).let { byteArr ->
                        val readLength = fileInputStream.read(byteArr)
                        if (byteArr.size == available) {
                            byteArr
                        } else {
                            byteArr.sliceArray(IntRange(0, readLength - 1))
                        }
                    }
                }
            }
        }
    }
}