package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/students")//http://localhost:8080/SpringMVC/students
//class:tüm methodlar için
// method level :sadece o method için mapping yapar
public class StudentController {
    @Autowired
    private StudentService service;

    //controllerdan requeste göre geriye ModelAndView(data+view name) veya
    //String tipinde view name döndürülür.
    //@RequestMapping("/students/hi")
    @GetMapping("/hi")
    public ModelAndView sayHi(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Hi, ");
        mav.addObject("messagebody","I am a Student Management System");
        mav.setViewName("hi");//hi.jsp
        return mav;
    }
    //view resolver mav içindeki view name e göre hi.jsp dosyasını bulur.
    //mav içindeki model ı hi.jsp içerisine bind eder.

    //1-Student Creation
    //kullanıcıdan bilgileri almak için form göstrelim
    @GetMapping("/new")//http://localhost:8080/SpringMVC/students/new
    public String sendStudentForm(@ModelAttribute("student") Student student){
        return "studentForm";
    }
    //@ModelAttribute ann:Studentformdaki bilgilerle Student tipinde bir obje oluşturur,
    //daha sonra bu objenin kullanılmasını sağlar. view ile controller arasında data transferini sağlar


    //formun submit://http://localhost:8080/SpringMVC/students/saveStudent, method:POST
    //tüm listeyi gösterelim
    @PostMapping("/saveStudent")
    public String createStudent(@ModelAttribute Student student){
        //service in save metodu gerekli
        service.saveStudent(student);
        return "redirect:/students";//http://localhost:8080/SpringMVC/students
    }

    //tüm studentları listeleyen request:http://localhost:8080/SpringMVC/students
    //2-read:
    @GetMapping
    public ModelAndView listAllStudents(){
        List<Student> students=service.getAll();
        ModelAndView mav=new ModelAndView();
        mav.addObject("studentList",students);
        mav.setViewName("students");//students.jsp
        return mav;
    }


    //3-update:http://localhost:8080/SpringMVC/students/update?id=4
    @GetMapping("/update")
    public ModelAndView showFormForUpdate(@RequestParam("id") Long id){
        Student foundStudent=service.getStudentById(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("student",foundStudent);
        mav.setViewName("studentForm");
        return mav;
    }
}