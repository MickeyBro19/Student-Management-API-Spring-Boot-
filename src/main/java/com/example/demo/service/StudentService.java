package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository repo;

    public List<Student> getAllStudent(){
        return repo.findAll();
    }

    public Student addStudent( Student student){
        return repo.save(student);
    }

    public Student getStudentById(int id) {
        return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Not Found with id: "+ id ));
    }

    public Student updateStudent(int id, Student student) {
        Student findStudent=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Not Found with id: "+ id ));
            findStudent.setName(student.getName());
            findStudent.setAge(student.getAge());
            return repo.save(findStudent);
    }

    public boolean deleteStudent(int id) {
        Student findStudent=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Not Found with id: "+ id ));
            repo.delete(findStudent);
            return true;
    }
}
