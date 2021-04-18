package com.naveen.example.sample.web;

import com.naveen.example.sample.business.service.StudentService;
import com.naveen.example.sample.data.entity.Student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class StudentController {
    private final StudentService studentService;
    // private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public RedirectView redirectToHome() {
        return new RedirectView("/student");
    }
    
    @GetMapping("/student")
    public String getStudents(Model model){
        model.addAttribute("students", studentService.getStudents());
        return "student";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(@PathVariable int id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("mode", "edit");
        return "student-edit";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable int id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("mode", "delete");
        return "student-edit";
    }

    @GetMapping("/student/add")
    public String addStudent(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("mode", "add");
        return "student-edit";
    }

    @PostMapping("/student/save/{mode}")
    public RedirectView saveStudent(@PathVariable String mode, @ModelAttribute Student student){
        if(mode.equals("delete")){
            studentService.deleteStudent(student);
        }else{
            studentService.saveStudent(student);
        }
        return new RedirectView("/student");
    }
}
