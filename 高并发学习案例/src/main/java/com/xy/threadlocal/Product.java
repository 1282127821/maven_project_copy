package com.xy.threadlocal;

/**
 * @fileName:Product
 * @author:xy
 * @date:2018/11/4
 * @description:
 */
public class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
