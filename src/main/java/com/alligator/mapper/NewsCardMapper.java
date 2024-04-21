package com.alligator.mapper;

import com.alligator.dto.NewsCardDTO;
import com.alligator.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsCardMapper extends CoreMapper<News, NewsCardDTO> {
	@Mapping(target = "description", source = "news.shortDescription")
	NewsCardDTO toDto(News news);
}