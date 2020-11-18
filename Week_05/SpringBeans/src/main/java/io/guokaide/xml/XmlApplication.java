package io.guokaide.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(XmlBean.class);
        XmlBean xmlBean = (XmlBean) context.getBean("xmlBean");
        xmlBean.sayHello();
    }
}
