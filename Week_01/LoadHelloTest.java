package com.jvm.practice.week1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoadHelloTest {
    public static void main(String[] args) {
        String classFilePath = LoadHelloTest.class.getResource("Hello.xlass").getPath();
        try {
            Object object = new HelloClassLoader(classFilePath).findClass("Hello").newInstance();
            Method method = object.getClass().getMethod("hello");
            method.invoke(object);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
