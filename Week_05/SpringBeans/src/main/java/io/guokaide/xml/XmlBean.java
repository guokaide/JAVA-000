package io.guokaide.xml;

import io.guokaide.hello.HelloBean;

public class XmlBean implements HelloBean {
    @Override
    public void sayHello() {
        System.out.println("Hello, I'm a XML Bean");
    }
}
