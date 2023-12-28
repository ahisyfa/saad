package com.github.ahisyfa.saad.saad.service;

import com.github.ahisyfa.saad.saad.entity.Student;
import com.github.ahisyfa.saad.saad.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * StudentService
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: StudentService.java, v 0.1 2023-07-15  19.06 Ahmad Isyfalana Amin Exp $
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public boolean deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
        return !studentRepository.existsById(studentId);
    }
}