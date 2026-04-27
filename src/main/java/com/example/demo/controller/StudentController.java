package com.example.demo.controller;

import com.example.demo.DTO.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping
    public ResponseEntity<Page<StudentDTO>> getAllStudents(Pageable pageable){
        Page<StudentDTO> DTOPage=service.getAllStudent(pageable).map(service::convertToDTO);
        return ResponseEntity.status(HttpStatus.OK).body(DTOPage);
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
