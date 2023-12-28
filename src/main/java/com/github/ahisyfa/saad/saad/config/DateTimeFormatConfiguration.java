package com.github.ahisyfa.saad.saad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * DateTimeFormatConfiguration
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: DateTimeFormatConfiguration.java, v 0.1 2023-07-22  20.48 Ahmad Isyfalana Amin Exp $
 */
@Configuration
public class DateTimeFormatConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Used for handle this error
     *
     * Failed to convert property value of type java.lang.String to required type java.time.LocalDate for property birthDate;
     * nested exception is org.springframework.core.convert.ConversionFailedException:
     * Failed to convert from type [java.lang.String] to type [@javax.persistence.Column java.time.LocalDate]
     * for value [2023-05-01]; nested exception is java.lang.IllegalArgumentException: Parse attempt failed for value [2023-05-01]
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }
}
