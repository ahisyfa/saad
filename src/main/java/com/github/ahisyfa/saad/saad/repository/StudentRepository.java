package com.github.ahisyfa.saad.saad.repository;

import com.github.ahisyfa.saad.saad.entity.Student;
import com.github.ahisyfa.saad.saad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: UserRepository.java, v 0.1 2023-07-15  15.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}