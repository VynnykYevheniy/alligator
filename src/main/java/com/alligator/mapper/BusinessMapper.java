package com.alligator.mapper;

import com.alligator.dto.BusinessDTO;
import com.alligator.model.Business;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BusinessMapper extends CoreMapper<Business, BusinessDTO> {
}
