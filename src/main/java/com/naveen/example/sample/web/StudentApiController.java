package com.naveen.example.sample.web;

import java.util.List;

import com.naveen.example.sample.business.service.StudentService;
import com.naveen.example.sample.data.entity.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/student")
public class StudentApiController {
    private final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentApiController.class);

    @Autowired
    public StudentApiController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping
    public List<Student> getStudentList(){
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/save/{mode}")
    public void saveStudent(@PathVariable String mode, @RequestBody Student student){
        logger.info(student.toString());
        if(mode.equals("delete")){
            studentService.deleteStudent(student);
        }else{
            studentService.saveStudent(student);
        }
    }

    
}
