package com.xy.NIO;

import com.xy.NIO.version1.EchoServer;

import java.io.IOException;
import java.nio.channels.*;

/**
 * @fileName:NIOTest
 * @author:xy
 * @date:2018/12/5
 * @description:
 */
public class NIOTest {
    public static void main(String[] args) throws Exception {
        EchoServer echoServer=new EchoServer();
        echoServer.startServer();
    }
}
