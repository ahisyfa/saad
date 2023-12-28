package com.github.ahisyfa.saad.saad.repository;

import com.github.ahisyfa.saad.saad.entity.Tuition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

/**
 * TuitionRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: TuitionRepository.java, v 0.1 2023-07-15  18.34 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface TuitionRepository extends JpaRepository<Tuition, Long> {

    @Override
    @Query("SELECT t FROM Tuition t JOIN FETCH t.student s")
    List<Tuition> findAll();

    @Query("SELECT t FROM Tuition t JOIN FETCH t.student s WHERE t.period = ?1")
    List<Tuition> findByPeriod(YearMonth period);

    @Query("SELECT t FROM Tuition t JOIN FETCH t.student s WHERE t.paymentDate BETWEEN :startDate AND :endDate ORDER BY t.paymentDate")
    List<Tuition> findBetweenDate(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);
}