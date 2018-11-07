package com.xy.枚举抽象方法;

import java.net.ServerSocket;

enum My_Color {
    /** 红色*/
    RED{
        @Override
        public void getColor() {
            System.out.println("RED");
        }
    },
    /** 绿色*/
    GREEN(){
        @Override
        public void getColor() {
            System.out.println("GREEN");
        }
    },
    /** 蓝色*/
    BLUE(1){
        @Override
        public void getColor() {
            System.out.println("BLUE");
        }
    };

    public abstract void getColor();

    My_Color() {
        System.out.println("这是无参构造");
    }

    My_Color(int num) {
        System.out.println("这是有参构造"+num);
    }
}
public class EnumTest{
    public static void main(String[] args) {
        My_Color.RED.getColor();
        My_Color.GREEN.getColor();
        My_Color.BLUE.getColor();
    }
}
