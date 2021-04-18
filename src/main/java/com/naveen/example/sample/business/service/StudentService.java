package com.naveen.example.sample.business.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.naveen.example.sample.data.entity.Student;
import com.naveen.example.sample.data.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        Iterable<Student> iterable = studentRepository.findAll();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .sorted((s1, s2) -> s2.getStudentName()
                .compareTo(s1.getStudentName()))
                .collect(Collectors.toList());
    }

    public Student getStudentById(int id){
        return studentRepository.findById(id).get();
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }
    
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }
}

    
