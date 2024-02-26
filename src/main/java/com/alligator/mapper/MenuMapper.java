package com.alligator.mapper;

import com.alligator.dto.MenuDTO;
import com.alligator.model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuMapper extends CoreMapper<Menu, MenuDTO> {
}