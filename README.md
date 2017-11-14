# AndroidSerialPortLib
A Serialport support lib for android, using kotlin

# Quick Start
You can use this as an android module, and import into you Application use:

```gradle
implementation project(':serialportutil')
```

This class is thread safe class, which means you can use it in any thread you want.

to use this lib you can simply create a Object using:
```kotlin
SerialPort(val device: File, val baudrate: Int, val flags: Int = 0)
```
* device means the serialport name ,for example File("dev/ttyS1/")
* baudrate is the baudrate you connect with the serialport device, for example 19200
* flags you can set it as default

if you want to send some data(byte[]) to serial port. you can simply use 
```kotlin
fun write(cmd: ByteArray)
```
same as wirte you can read the data from serialport using:
```kotlin
fun read(): ByteArray?
```

# About C part code
the c code was using google open source project.I just wrap them using kotlin.

# Make it better
if you have some problems using this lib or you have some awsome idea to make this lib better, You can direct send your opinions to 443517937@qq.com

