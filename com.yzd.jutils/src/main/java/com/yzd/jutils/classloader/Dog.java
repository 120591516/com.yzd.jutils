package com.yzd.jutils.classloader;

public class Dog {

    public Dog() {
        System.out.println("Dog is loaded by "+this.getClass().getClassLoader());
    }
}
