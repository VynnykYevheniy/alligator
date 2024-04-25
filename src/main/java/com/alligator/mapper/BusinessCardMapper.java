package com.alligator.mapper;

import com.alligator.dto.BusinessCardDTO;
import com.alligator.dto.BusinessDTO;
import com.alligator.model.Business;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BusinessCardMapper extends CoreMapper<Business, BusinessCardDTO> {
	@Override
	@Mapping(target = "description", source = "shortDescription")
	BusinessCardDTO toDto(Business business);
}