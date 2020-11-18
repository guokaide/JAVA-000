package io.guokaide.springbootpractice;

import io.guokaide.customspringbootstarter.model.ISchool;
import io.guokaide.helloservicespringbootstarter.service.HelloService;
import io.guokaide.springbootpractice.hikaricp.HikaricpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringbootPracticeApplication implements CommandLineRunner {

    private final HelloService helloService;

    private final ISchool school;

    private final HikaricpService hikaricpService;

    public SpringbootPracticeApplication(HelloService helloService, ISchool school, HikaricpService hikaricpService) {
        this.helloService = helloService;
        this.school = school;
        this.hikaricpService = hikaricpService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPracticeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        helloService.sayHello();
        school.ding();
        hikaricpService.createTable();
        hikaricpService.insertData();
        log.info("===> before update");
        hikaricpService.showData();
        try {
            hikaricpService.updateData();
        } catch (Exception e) {
            log.info(e.toString());
        }
        log.info("===> after update");
        hikaricpService.showData();
    }
}
