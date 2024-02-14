package com.alligator.mapper;

import com.alligator.dto.HomeDTO;
import com.alligator.model.Home;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Home.class, HomeDTO.class})
public interface HomeMapper extends CoreMapper<Home, HomeDTO> {
}