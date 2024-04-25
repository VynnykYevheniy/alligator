package com.alligator.mapper;

import com.alligator.dto.BusinessDTO;
import com.alligator.model.Business;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BusinessMapper extends CoreMapper<Business, BusinessDTO> {
	@Override
	@Mapping(target = "description", source = "fullDescription")
	BusinessDTO toDto(Business business);
}