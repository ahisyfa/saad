package com.github.ahisyfa.saad.saad.service;

import com.github.ahisyfa.saad.saad.entity.Tuition;
import com.github.ahisyfa.saad.saad.repository.TuitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

/**
 * TuitionService
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: StudentService.java, v 0.1 2023-07-15  19.06 Ahmad Isyfalana Amin Exp $
 */
@Service
public class TuitionService {

    @Autowired
    private TuitionRepository tuitionRepository;

    public boolean isExist(Long tuitionId) {
        return tuitionRepository.existsById(tuitionId);
    }

    public List<Tuition> findAll() {
        return tuitionRepository.findAll();
    }

    public Optional<Tuition> findById(Long tuitionId) {
        return tuitionRepository.findById(tuitionId);
    }

    public List<Tuition> findByPeriod(YearMonth period) {
        return tuitionRepository.findByPeriod(period);
    }

    public List<Tuition> findBetweenDate(LocalDate startDate, LocalDate endDate) {
        return tuitionRepository.findBetweenDate(startDate, endDate);
    }

    public Tuition save(Tuition tuition) {
        return tuitionRepository.save(tuition);
    }
    
    public boolean deleteById(Long tuitionId) {
        tuitionRepository.deleteById(tuitionId);
        return !tuitionRepository.existsById(tuitionId);
    }
}