package com.github.ahisyfa.saad.saad.entity.converter;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.time.YearMonth;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: YearMonthAttributeConverter.java, v 0.1 2023-07-16  14.24 Ahmad Isyfalana Amin Exp $
 */
public class YearMonthAttributeConverter implements AttributeConverter<YearMonth, String> {
    @Override
    public String convertToDatabaseColumn(YearMonth yearMonth) {
        if (yearMonth != null) {
            return yearMonth.toString();
        }
        return null;
    }

    @Override
    public YearMonth convertToEntityAttribute(String s) {
        if (StringUtils.hasText(s)) {
            return YearMonth.parse(s);
        }
        return null;
    }
}