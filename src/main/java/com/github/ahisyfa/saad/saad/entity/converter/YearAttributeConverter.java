package com.github.ahisyfa.saad.saad.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Year;

/**
 * YearAttributeConverter
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: YearAttributeConverter.java, v 0.1 2023-07-15  21.36 Ahmad Isyfalana Amin Exp $
 */
@Converter(autoApply = true)
public class YearAttributeConverter implements AttributeConverter<Year, String> {

    @Override
    public String convertToDatabaseColumn(Year attribute) {
        if (attribute != null) {
            return attribute.toString();
        }
        return null;
    }

    @Override
    public Year convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            return Year.parse(dbData);
        }
        return null;
    }

}