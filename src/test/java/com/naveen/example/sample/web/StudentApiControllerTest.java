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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StudentApiController.class)
public class StudentApiControllerTest {
    
    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    private List<Student> studentList = new ArrayList<Student>();
    private Student student = new Student();

    private final ObjectMapper objMapper = new ObjectMapper();

    @Before
    public void init(){
        student.setStudentId(1);
        student.setStudentName("JUnit");
        student.setAverageScore(100);
        student.setClassRank(1);
        studentList.add(student);

        given(studentService.getStudents()).willReturn(studentList);
        given(studentService.getStudentById(1)).willReturn(student);
    }

    @Test
    public void getStudents() throws Exception{
        String studentListJSONString = objMapper.writeValueAsString(studentList);
        this.mockMvc.perform(get("/api/student"))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo(studentListJSONString)));
    }

    @Test
    public void getStudent() throws Exception{
        String studentObjectJSONString = objMapper.writeValueAsString(student);
        this.mockMvc.perform(get("/api/student/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo(studentObjectJSONString)));
    }

    @Test
    public void addStudent() throws Exception{
        String studentObjString = objMapper.writeValueAsString(student);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                                            .post("/api/student/save/add")
                                            .accept(MediaType.APPLICATION_JSON)
                                            .content(studentObjString)
                                            .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
    }

}
