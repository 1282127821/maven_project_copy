package com.xy.Files$Paths;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @fileName:FilesAndPathsTest
 * @author:xy
 * @date:2018/10/9
 * @description:
 */
public class FilesAndPathsTest {
    public static void main(String[] args) throws IOException, InterruptedException {
    Files.createDirectory(Paths.get("G:\\work\\data\\idea_workspace\\maven_project\\second\\src\\com\\xy\\Files$Paths"+"\\test"));
    Files.deleteIfExists(Paths.get("G:\\work\\data\\idea_workspace\\maven_project\\second\\src\\com\\xy\\Files$Paths"+"\\test"));
        //list得到的是一个 Stream流，可以使用lambda
        Stream<Path> list = Files.list(Paths.get("G:/work/data/idea_workspace/maven_project/"));
        list.forEach((l)->{System.out.println(l.getFileName());});
        //计算某个文件的大小（字节大小，注意 这里和实际大小有区别的，我记得以前学习file就有过类似问题）
        long size = Files.size(Paths.get("G:\\work\\data\\idea_workspace\\maven_project\\second\\src\\com\\xy\\Files$Paths"+"\\FilesAndPathsTest.java"));
        System.out.println(size);
        FileOutputStream fout=new FileOutputStream(new File("G:\\work\\data\\idea_workspace\\maven_project\\second\\src\\com\\xy\\Files$Paths"+"\\tmp2.text"));
        Files.copy(Paths.get("G:\\work\\data\\idea_workspace\\maven_project\\second\\src\\com\\xy\\Files$Paths\\tmp.text"),fout );
//        fout.flush();
        /*
        获取一个流 ，且设置编码为utf-8
         */
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("G:\\work\\data\\idea_workspace\\maven_project\\second\\src\\com\\xy\\Files$Paths\\tmp.text"),StandardCharsets.UTF_8);
        String s = bufferedReader.readLine();
        System.out.println(s);
    }
}
