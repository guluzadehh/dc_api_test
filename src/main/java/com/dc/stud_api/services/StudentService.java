package com.dc.stud_api.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.stud_api.common.Result;
import com.dc.stud_api.models.Student;
import com.dc.stud_api.repository.RepoError;
import com.dc.stud_api.repository.interfaces.IStudentRepository;

@Service
public class StudentService {
    private IStudentRepository _studentRepo;

    @Autowired
    public StudentService(IStudentRepository studentRepo) {
        _studentRepo = studentRepo;
    }

    public Result<List<Student>, ServiceError> listStudents() {
        Result<List<Student>, RepoError> res = _studentRepo.getAll();

        if (res.hasError()) {
            return Result.error(ServiceError.DEFAULT);
        }

        return Result.success(res.getData());
    }

    public Result<Student, ServiceError> createStudent(String fName, String lName, String surname, Date birthdate,
            Integer group) {
        Result<Student, RepoError> res = _studentRepo.add(fName, lName, surname, birthdate, group);

        if (res.hasError()) {
            return Result.error(ServiceError.DEFAULT);
        }

        return Result.success(res.getData());
    }

    public Result<?, ServiceError> deleteStudent(Integer id) {
        Result<?, RepoError> res = _studentRepo.delete(id);

        if (res.hasError()) {
            if (res.getError() == RepoError.STUDENT_NOT_FOUND) {
                return Result.error(ServiceError.STUDENT_DOESNT_EXIST);
            }

            return Result.error(ServiceError.DEFAULT);
        }

        return Result.success(null);
    }
}
