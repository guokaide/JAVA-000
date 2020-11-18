package io.guokaide.customspringbootstarter.autoconfigure;

import io.guokaide.customspringbootstarter.model.ISchool;
import io.guokaide.customspringbootstarter.model.Klass;
import io.guokaide.customspringbootstarter.model.School;
import io.guokaide.customspringbootstarter.model.Student;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "custom.starter.enabled", havingValue = "true")
@ConditionalOnClass(ISchool.class)
@EnableConfigurationProperties(CustomAutoConfigurationProperties.class)
@Data
public class CustomAutoConfiguration {

    @Autowired
    private CustomAutoConfigurationProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public Klass klass() {
        return new Klass(this.properties.getStudents());
    }

    @Bean
    @ConditionalOnMissingBean
    public Student student(Klass klass) {
        return klass.getStudents().size() > 0 ? klass.getStudents().get(0) : new Student(100, "student100");
    }

    @Bean
    @ConditionalOnMissingBean
    public ISchool school(Klass klass, Student student) {
        return new School(klass, student);
    }
}
