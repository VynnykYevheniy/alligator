package com.alligator.mapper;

import com.alligator.dto.AboutDTO;
import com.alligator.model.About;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AboutMapper extends CoreMapper<About, AboutDTO> {
}