package com.dc.stud_api.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

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
    public Student add(String fName, String lName, String surname, Date birthdate, Integer group) {
        Student student = new Student(fName, lName, surname, birthdate, group);
        student.setId(_students.size() + 1);
        _students.put(student.getId(), student);
        return student;
    }

    @Override
    public void delete(Integer id) {
        _students.remove(id);
    }

    @Override
    public List<Student> getAll() {
        return new ArrayList<>(_students.values());
    }

    @Override
    public Student findById(Integer id) {
        return _students.get(id);
    }
}
