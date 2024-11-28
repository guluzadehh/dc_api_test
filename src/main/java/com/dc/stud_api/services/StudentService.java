package com.dc.stud_api.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.stud_api.models.Student;
import com.dc.stud_api.repository.interfaces.IStudentRepository;

@Service
public class StudentService {
    private IStudentRepository _studentRepo;

    @Autowired
    public StudentService(IStudentRepository studentRepo) {
        _studentRepo = studentRepo;
    }

    public Result<List<Student>> listStudents() {
        return new Result<List<Student>>(_studentRepo.getAll(), null);
    }

    public Result<Student> createStudent(String fName, String lName, String surname, Date birthdate, Integer group) {
        Student student = _studentRepo.add(fName, lName, surname, birthdate, group);
        return new Result<>(student, null);
    }

    public Result<?> deleteStudent(Integer id) {
        Student student = _studentRepo.findById(id);

        if (student == null) {
            return new Result<>(null, ErrorType.STUDENT_NOT_FOUND);
        }

        _studentRepo.delete(id);

        return new Result<>(null, null);
    }
}
