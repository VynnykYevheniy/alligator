package com.alligator.config;

import com.alligator.model.enumeration.BusinessCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StringToBusinessTypeConverter implements Converter<String, BusinessCategory> {

    @Override
    public BusinessCategory convert(@NonNull String source) {
        return BusinessCategory.fromString(source);
    }
}