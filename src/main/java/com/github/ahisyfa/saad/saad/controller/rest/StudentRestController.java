package com.github.ahisyfa.saad.saad.controller.rest;

import com.github.ahisyfa.saad.saad.entity.Student;
import com.github.ahisyfa.saad.saad.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: StudentRestController.java, v 0.1 2023-07-15  20.47 Ahmad Isyfalana Amin Exp $
 */
@RestController()
@RequestMapping("/rest/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @PostMapping("")
    public Student add(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping("/{studentId}")
    public Optional<Student> findById(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    @PutMapping("/{studentId}")
    public Student update(@PathVariable String studentId, @RequestBody Student student) {
        return studentService.save(student);
    }

    @DeleteMapping("/{studentId}")
    public boolean deleteById(@PathVariable Long studentId) {
        return studentService.deleteById(studentId);
    }

}