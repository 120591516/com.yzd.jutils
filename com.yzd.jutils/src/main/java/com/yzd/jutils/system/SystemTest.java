package com.yzd.jutils.system;

import java.io.PrintStream;

/**
 * Created by lifan on 2019/7/9.
 */
public class SystemTest {
    /**
     * 将system.out.println输出到文件
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        System.setOut(new PrintStream("D:/test.txt"));
        System.out.println("111");
        System.out.println("===");
        System.out.println("222");
        System.out.println("===");
        System.out.println("333");
    }
}
