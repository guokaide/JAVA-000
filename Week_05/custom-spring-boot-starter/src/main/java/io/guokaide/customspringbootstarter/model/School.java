package io.guokaide.customspringbootstarter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class School implements ISchool {

    Klass class1;
    Student student100;

    @Override
    public void ding() {
        log.info("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
    }
}
