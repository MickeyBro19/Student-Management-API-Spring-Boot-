package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository repo;
    List<Student> students=new ArrayList<>();

    public List<Student> getAllStudent(){
        return repo.findAll();
    }

    public Boolean addStudent( Student student){
        repo.save(student);
        return true;
    }

    public Student getStudentById(int id) {
        return repo.findById(id).orElse(null);
    }
}
