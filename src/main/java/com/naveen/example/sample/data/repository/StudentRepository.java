package com.naveen.example.sample.data.repository;

import com.naveen.example.sample.data.entity.Student;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer>{
    
}
