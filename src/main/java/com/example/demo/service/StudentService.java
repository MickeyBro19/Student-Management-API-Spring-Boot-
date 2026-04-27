package com.example.demo.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.ResourceNotFoundException;


@Service
public class StudentService {

    private static final Logger logger= LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentRepository repo;

    public Page<Student> getAllStudent(Pageable pageable){
        logger.info("Fetching all the Students");
        return repo.findAll(pageable);
    }

    public Student addStudent( Student student){
        logger.info("Adding new student: {}", student.getName());
        return repo.save(student);
    }

    public Student getStudentById(int id) {
        logger.info("Fetching student with id: {}",id);
        return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Not Found with id: "+ id ));
    }

    public Student updateStudent(int id, Student student) {
        logger.info("Updating student details id: {} name: {}",id,student.getName());
        Student findStudent=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Not Found with id: "+ id ));
            findStudent.setName(student.getName());
            findStudent.setAge(student.getAge());
            return repo.save(findStudent);
    }

    public boolean deleteStudent(int id) {
        logger.warn("Deleting student with id: {}",id);
        Student findStudent=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Not Found with id: "+ id ));
            repo.delete(findStudent);
            return true;
    }

    public StudentDTO convertToDTO(Student student){
        return new StudentDTO(student.getId(),student.getName());
    }

}
