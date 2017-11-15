# English：

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


# 中文：

# AndroidSerialPortLib
一个android的 串口开发工具

# Quick Start
这个项目可以直接当做android stuio的modle使用， 在导入后可以简单的使用以下代码引入

```gradle
implementation project(':serialportutil')
```

这个工具类是线程安全的， 你可以在任意线程中使用类中的读写方法

你可以简单的使用以下代码创建一个Serialport对象
```kotlin
SerialPort(val device: File, val baudrate: Int, val flags: Int = 0)
```
* device 这个是串口代表的file 比如： File("dev/ttyS1/")
* baudrate 这个是你用来和串口通讯时使用的波特率 比如 19200
* flags 如无特殊需求可以直接使用默认值

如果你想向某个串口发送数据 你可以使用如下方法：
```kotlin
fun write(cmd: ByteArray)
```

同样的你可以使用如下方法 读取串口的返回值
```kotlin
fun read(): ByteArray?
```

# About C part code
C部分的代码我直接使用的google的serialport开源项目

# Make it better
如果你在使用发现的bug或有更加好的建议 可以直接联系443517937@qq.com
