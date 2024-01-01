package com.github.ahisyfa.saad.saad.controller;

import com.github.ahisyfa.saad.saad.entity.Student;
import com.github.ahisyfa.saad.saad.helper.StudentExporterPdf;
import com.github.ahisyfa.saad.saad.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * StudentControllerø
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: StudentController.java, v 0.1 2023-07-15  19.20 Ahmad Isyfalana Amin Exp $
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public String studentList(Model model) {
        List<Student> students = studentService.findAll();

        model.addAttribute("students", students);

        return "student/student_list.html";
    }

    @GetMapping("/student/export")
    public void exportStudentList(HttpServletResponse response) throws IOException {
        List<Student> students = studentService.findAll();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename:user.pdf");

        StudentExporterPdf.export(students, response);
    }

    @GetMapping("/student/add")
    public String add(Model model) {
        Student student = new Student();

        model.addAttribute("student", student);

        return "student/student_form";
    }

    @PostMapping("/student/add")
    public String add(
            @Valid @ModelAttribute("student")Student student,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("student", student);

            return "student/student_form";
        }

        studentService.save(student);

        return "redirect:/student";
    }

    @GetMapping("/student/edit/{studentId}")
    public String add(@PathVariable Long studentId,  Model model) throws Exception {
        Optional<Student> studentOpt = studentService.findById(studentId);

        if (!studentOpt.isPresent()) {
            throw new Exception("Invalid studentId");
        }

        model.addAttribute("student", studentOpt.get());

        return "student/student_form_edit";
    }

    @PostMapping("/student/edit/{studentId}")
    public String submitEditStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("student", student);

            return "student/student_form_edit";
        }

        studentService.save(student);

        return "redirect:/student";
    }
}