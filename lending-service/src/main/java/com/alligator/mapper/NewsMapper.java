package com.alligator.mapper;

import com.alligator.dto.NewsCardDTO;
import com.alligator.dto.NewsDTO;
import com.alligator.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsMapper extends CoreMapper<News, NewsDTO> {
	@Mapping(target = "description", source = "news.fullDescription")
	NewsDTO toDto(News news);
}