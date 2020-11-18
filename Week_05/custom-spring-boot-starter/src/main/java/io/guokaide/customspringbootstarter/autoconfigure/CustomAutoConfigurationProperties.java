package io.guokaide.customspringbootstarter.autoconfigure;

import io.guokaide.customspringbootstarter.model.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

// https://stackoverflow.com/questions/56637271/reason-the-elements-were-left-unbound
// https://stackoverflow.com/questions/41417933/spring-configuration-properties-metadata-json-for-nested-list-of-objects
@ConfigurationProperties(prefix = "custom.starter")
@Data
public class CustomAutoConfigurationProperties {
    List<Student> students;
}
