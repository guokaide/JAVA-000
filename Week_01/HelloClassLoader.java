package com.jvm.practice.week1;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class HelloClassLoader extends ClassLoader {
    private String classFilePath;

    public HelloClassLoader(String classFilePath) {
        this.classFilePath = classFilePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = parseClassFile();
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] parseClassFile() {
        byte[] data = null;
        try (FileInputStream fileInputStream = new FileInputStream(classFilePath);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            data = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (255 - data[i]);
        }
        return data;
    }
}
