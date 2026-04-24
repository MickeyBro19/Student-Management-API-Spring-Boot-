package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllStudent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateStudent(id,student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        service.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student Deleted Successfully");
    }

}
