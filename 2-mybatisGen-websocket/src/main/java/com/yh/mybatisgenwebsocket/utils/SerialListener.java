package com.yh.mybatisgenwebsocket.utils;

import com.yh.mybatisgenwebsocket.SpringUtil;
import com.yh.mybatisgenwebsocket.entity.Flow1;
import com.yh.mybatisgenwebsocket.service.Impl.Flow1ServiceImpl;
import gnu.io.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class SerialListener extends Thread implements SerialPortEventListener {

    static final Integer DataLength = 7;//readBuffer[]数据长度
    static CommPortIdentifier portId; // 串口通信管理类
    static Enumeration<?> portList; // 有效连接上的端口的枚举

    //COM2->COM3
    InputStream inputStream; // 从串口来的输入流
    static OutputStream outputStream;// 向串口输出的流
    static SerialPort serialPort; // 串口的引用

    // 堵塞队列用来存放读到的数据---COM2
    public static BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();

    @Override
    /**
     * SerialPort EventListener 的方法,持续监听端口上是否有数据流
     */
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:// 10通讯中断
            case SerialPortEvent.OE:// 7溢位错误
            case SerialPortEvent.FE:// 9帧错误
            case SerialPortEvent.PE:// 8奇偶校验错
            case SerialPortEvent.CD:// 6载波检测
            case SerialPortEvent.CTS:// 3清除发送
            case SerialPortEvent.DSR:// 4数据设备准备好
            case SerialPortEvent.RI:// 5振铃指示
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:// 2输出缓冲区已清空
                break;
            case SerialPortEvent.DATA_AVAILABLE:// 当有可用数据时读取数据
                byte[] readBuffer = new byte[DataLength];
                try {

                    int numBytes = -1;
                    while (inputStream.available() > 0) {

                        //加个定时模块，保证一条数据完全送达，从而接收，具体定时时间还需要商议
                        //一定记得从串口发指令取数据之后加一个延时，等待底层数据传输完成再去buffer里面取，不然很大可能数据包不完整。
//                        try {
//                            Thread.sleep(200);
//                        } catch (InterruptedException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
                        TimeUnit.MILLISECONDS.sleep(20);//20毫秒

                        numBytes = inputStream.read(readBuffer);
                        System.out.println("COM1-ContinueRead【1】----从串口1读到的字节数——"+numBytes);
                        System.out.println("COM1-ContinueRead【2】----ArraysToString of readBuffer:"+new String(readBuffer, Charset.forName("utf-8")));
//                        msgQueue.add(new String(readBuffer, Charset.forName("gbk")));//gbk为一种格式，区别于utf-8

                        //插入数据到数据库flow1
                        Flow1ServiceImpl flow1Service= SpringUtil.getBean(Flow1ServiceImpl.class);
                        flow1Service.insert(new Flow1(null,new String(readBuffer, Charset.forName("gbk")),DateUtil.getYsSDateAgo(0)));
                        System.out.println("插入数据库成功");

                        readBuffer = new byte[DataLength];// 重新构造缓冲对象，否则有可能会影响接下来接收的数据
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    /**
     * 通过程序打开COM串口，设置监听器以及相关的参数
     * @return 返回1 表示端口打开成功，返回 0表示端口打开失败
     */
    public int startComPort() {
        // 通过串口通信管理类获得当前连接上的串口列表
        portList = CommPortIdentifier.getPortIdentifiers();

        //設置中間變量，判斷串口是否打開，若打開，則是哪個串口
        //1---只有COM2；2---只有COM4;3---都打開；0---一個也沒有
//        int sigSerial=0;

        while (portList.hasMoreElements()) {

            // 获取相应串口对象
            portId = (CommPortIdentifier) portList.nextElement();

            System.out.println("设备类型：--->" + portId.getPortType());
            System.out.println("设备名称：---->" + portId.getName());
            // 判断端口类型是否为串口
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                // 判断如果COM串口存在，就打开该串口
                if (portId.getName().equals("COM3")) {
                    try {
                        // 打开串口名字为COM_1(名字任意),延迟为2毫秒
                        serialPort = (SerialPort) portId.open("COM2", 2000);

                    } catch (PortInUseException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 设置当前串口的输入输出流
                    try {
                        inputStream = serialPort.getInputStream();
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 给当前串口添加一个监听器
                    try {
                        serialPort.addEventListener(this);
                    } catch (TooManyListenersException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 设置监听器生效，即：当有数据时通知
                    serialPort.notifyOnDataAvailable(true);

                    // 设置串口的一些读写参数
                    try {
                        // 比特率、数据位、停止位、奇偶校验位
                        serialPort.setSerialPortParams(4800,
                                SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    public static void open() {
        Executors.newCachedThreadPool().submit(() ->{
            SerialListener serialListener=new SerialListener();
            int i = serialListener.startComPort();

            if (i == 1) {
                // 启动线程来处理收到的数据
                serialListener.start();
            }
        });
    }
}
