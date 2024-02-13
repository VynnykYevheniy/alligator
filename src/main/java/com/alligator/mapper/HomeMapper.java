package com.alligator.mapper;

import com.alligator.dto.HomeDTO;
import com.alligator.model.Home;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Home.class, HomeDTO.class})
public interface HomeMapper {
	HomeDTO toDto(Home home);

	Home toEntity(HomeDTO homeDTO);

	List<HomeDTO> toDto(List<Home> home);

	List<Home> toEntity(List<HomeDTO> homeDTO);

}
