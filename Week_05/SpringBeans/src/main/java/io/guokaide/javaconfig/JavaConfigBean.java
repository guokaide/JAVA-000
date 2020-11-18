package io.guokaide.javaconfig;

import io.guokaide.hello.HelloBean;

public class JavaConfigBean implements HelloBean {
    @Override
    public void sayHello() {
        System.out.println("Hello, I'm a Java Config Bean");
    }
}
