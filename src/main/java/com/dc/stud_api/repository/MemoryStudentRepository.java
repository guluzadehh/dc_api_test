package com.dc.stud_api.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.dc.stud_api.common.Result;
import com.dc.stud_api.models.Student;
import com.dc.stud_api.repository.interfaces.IStudentRepository;

@Repository
public class MemoryStudentRepository implements IStudentRepository {
    private Map<Integer, Student> _students = new ConcurrentHashMap<>();

    public MemoryStudentRepository() {
        Student student1 = new Student("jack", "nich", "surn", new Date(), 24);
        student1.setId(1);
        _students.put(1, student1);
    }

    @Override
    public Result<Student, RepoError> add(String fName, String lName, String surname, Date birthdate, Integer group) {
        Student student = new Student(fName, lName, surname, birthdate, group);
        student.setId(_students.size() + 1);
        _students.put(student.getId(), student);
        return Result.success(student);
    }

    @Override
    public Result<?, RepoError> delete(Integer id) {
        Result<?, RepoError> res = findById(id);

        if (res.hasError()) {
            return res;
        }

        _students.remove(id);
        return Result.success(null);
    }

    @Override
    public Result<List<Student>, RepoError> getAll() {
        return Result.success(new ArrayList<>(_students.values()));
    }

    @Override
    public Result<Student, RepoError> findById(Integer id) {
        Student student = _students.get(id);

        if (student == null) {
            return Result.error(RepoError.STUDENT_NOT_FOUND);
        }

        return Result.success(student);
    }
}
