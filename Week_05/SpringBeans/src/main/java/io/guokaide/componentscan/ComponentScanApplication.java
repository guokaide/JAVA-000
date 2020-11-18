package io.guokaide.componentscan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentScanApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanBean.class);
        ComponentScanBean componentScanBean = (ComponentScanBean) context.getBean("componentScanBean");
        componentScanBean.sayHello();
    }
}
