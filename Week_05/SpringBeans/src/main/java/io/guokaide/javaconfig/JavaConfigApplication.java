package io.guokaide.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfigBean.class);
        JavaConfigBean javaConfigBean = (JavaConfigBean) context.getBean("javaConfigBean");
        javaConfigBean.sayHello();
    }
}
