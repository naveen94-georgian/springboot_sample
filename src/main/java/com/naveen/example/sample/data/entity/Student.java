package com.naveen.example.sample.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="STUDENT")
public class Student {
    
    @Id
    @Column(name="STUDENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @Column(name="STUDENT_NAME")
    private String studentName;

    @Column(name="AVERAGE_SCORE")
    private int averageScore;

    @Column(name="CLASS_RANK")
    private int classRank;

    public int getStudentId() {
        return this.studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAverageScore() {
        return this.averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public int getClassRank() {
        return this.classRank;
    }

    public void setClassRank(int classRank) {
        this.classRank = classRank;
    }

    public String toString() {
        return "\nStudentId: " + this.getStudentId() 
        + "\t Student Name: "+ this.getStudentName()
        + "\t Average Score: "+ this.getAverageScore()
        + "\t Class Rank: "+ this.getClassRank();
    }
    

}
