package com.alligator.mapper;

import com.alligator.dto.HomeDTO;
import com.alligator.model.Home;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HomeMapper extends CoreMapper<Home, HomeDTO> {
}