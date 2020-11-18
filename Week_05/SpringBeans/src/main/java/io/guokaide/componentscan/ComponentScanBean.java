package io.guokaide.componentscan;

import io.guokaide.hello.HelloBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
//@Service
public class ComponentScanBean implements HelloBean {

    @Override
    public void sayHello() {
        System.out.println("Hello, I'm a Component Scan Bean");
    }
}
