package com.dc.stud_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.stud_api.common.Result;
import com.dc.stud_api.dto.CreateStudentRequest;
import com.dc.stud_api.models.Student;
import com.dc.stud_api.services.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import com.dc.stud_api.services.ServiceError;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService _studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllUsers() {
        Result<List<Student>, ServiceError> res = _studentService.listStudents();

        if (res.hasError()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(res.getData());
    }

    @PostMapping
    public ResponseEntity<Student> createUser(@Valid @RequestBody CreateStudentRequest request) {
        Result<Student, ServiceError> res = _studentService.createStudent(request.getFname(), request.getLname(),
                request.getSurname(),
                request.getBirthdate(), request.getGroup());

        if (res.hasError()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(res.getData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable @NotNull int id) {
        Result<?, ServiceError> res = _studentService.deleteStudent(id);

        if (res.hasError()) {
            switch (res.getError()) {
                case ServiceError.STUDENT_DOESNT_EXIST:
                    return ResponseEntity.notFound().build();
                default:
                    return ResponseEntity.internalServerError().build();
            }
        }

        return ResponseEntity.noContent().build();
    }
}