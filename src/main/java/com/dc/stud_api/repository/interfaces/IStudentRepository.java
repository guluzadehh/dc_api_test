package com.dc.stud_api.repository.interfaces;

import java.util.Date;
import java.util.List;

import com.dc.stud_api.models.Student;

public interface IStudentRepository {
    Student add(String fName, String lName, String surname, Date birthdate, Integer group);

    void delete(Integer id);

    List<Student> getAll();

    Student findById(Integer id);
}
