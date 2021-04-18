package com.naveen.example.sample.web;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naveen.example.sample.business.service.StudentService;
import com.naveen.example.sample.data.entity.Student;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @MockBean
    private StudentService studentService;
    
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objMapper = new ObjectMapper();

    List<Student> studentList = new ArrayList<Student>();

    Student student = new Student();

    @Before
    public void init(){
        student.setStudentId(1);
        student.setStudentName("JUnit");
        student.setAverageScore(100);
        student.setClassRank(1);
        studentList.add(student);

        given(studentService.getStudentById(1)).willReturn(student);
        given(studentService.getStudents()).willReturn(studentList);
    }
    

    @Test
    public void getStudents() throws Exception{
        this.mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("JUnit")));
    }

    @Test
    public void editStudent() throws Exception{
        this.mockMvc.perform(get("/student/edit/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("JUnit")));
    }

    @Test
    public void deleteStudent() throws Exception{
        this.mockMvc.perform(get("/student/delete/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("JUnit")));
    }

    @Test
    public void saveStudent() throws Exception{
        
        String studentObjString = this.objMapper.writeValueAsString(student);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                                            .post("/student/save/delete")
                                            .accept(MediaType.APPLICATION_JSON)
                                            .content(studentObjString)
                                            .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(redirectedUrl("/student"))
                .andExpect(status().isFound());
    }

}
