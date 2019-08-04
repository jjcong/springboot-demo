package com.jincong.springboot.test;

import org.springframework.util.StopWatch;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用NIO实现文件的复制
 *
 * @author j_cong
 * @version V1.0
 * @date 2018/03/07
 */
public class CopyFileTest {

    public static void main(String[] args) throws IOException {


        String src = "E:\\1.sql";
        String dst = "E:\\2.sql";

        copyFileByIO(src, dst);

        copyFileByNIO(src, dst);

    }

    public static void copyFileByIO(String src, String dst) throws IOException {

        StopWatch stopWatch = new StopWatch("文件复制测试");

        stopWatch.start("常规IO");

        //1、读数据，通过inputStream获取Channel，抛异常
        FileInputStream fi = new FileInputStream(src);
        FileOutputStream fo = new FileOutputStream(dst);
        byte[] bt = new byte[1024];
        int readByte = 0;

        while ((readByte = fi.read(bt)) > 0) {
            fo.write(bt, 0, readByte);
        }

        fi.close();
        fo.close();

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    public static void copyFileByNIO(String src, String dst) throws IOException {

        StopWatch stopWatch = new StopWatch("文件复制测试");

        stopWatch.start("NIO");

        //1、读数据，通过inputStream获取Channel，抛异常
        FileInputStream fi = new FileInputStream(src);
        FileChannel inChannel = fi.getChannel();

        //把Channel中的数据写入buffer中，若读到末尾，则返回-1

        //2、将数据写入文件
        //获得一个通道
        FileOutputStream fo = new FileOutputStream(dst);
        FileChannel outChannel = fo.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            int eof = inChannel.read(buffer);
            if (eof == -1) {
                break;
            }
            //将读模式改为写模式
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        inChannel.close();
        outChannel.close();
        fi.close();
        fo.close();

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }


}
