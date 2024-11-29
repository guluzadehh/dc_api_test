package com.dc.stud_api.repository.interfaces;

import java.util.Date;
import java.util.List;

import com.dc.stud_api.common.Result;
import com.dc.stud_api.models.Student;
import com.dc.stud_api.repository.RepoError;

public interface IStudentRepository {
    Result<Student, RepoError> add(String fName, String lName, String surname, Date birthdate, Integer group);

    Result<?, RepoError> delete(Integer id);

    Result<List<Student>, RepoError> getAll();

    Result<Student, RepoError> findById(Integer id);
}
