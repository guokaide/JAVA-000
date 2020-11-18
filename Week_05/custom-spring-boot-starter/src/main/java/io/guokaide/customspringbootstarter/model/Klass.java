package io.guokaide.customspringbootstarter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Klass {

    List<Student> students;

    public void dong() {
        log.info(this.getStudents().toString());
    }
}
