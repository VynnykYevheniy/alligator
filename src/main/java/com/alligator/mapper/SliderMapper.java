package com.alligator.mapper;

import com.alligator.dto.SliderDTO;
import com.alligator.model.Slider;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SliderMapper extends CoreMapper<Slider, SliderDTO> {
}
