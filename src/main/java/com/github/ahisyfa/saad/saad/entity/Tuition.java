package com.github.ahisyfa.saad.saad.entity;

import com.github.ahisyfa.saad.saad.entity.converter.YearMonthAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Entity class for Tuition
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: Tuition.java, v 0.1 2023-07-15  18.17 Ahmad Isyfalana Amin Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tuition", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "period"}))
public class Tuition extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tuition_id")
    private Long id;

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "student_id")
    private Student student;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "period",  columnDefinition = "text", length = 7)
    @Convert(converter = YearMonthAttributeConverter.class)
    private YearMonth period;

    @Column(name = "amount")
    private Long amount;

}