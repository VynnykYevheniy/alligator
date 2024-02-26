package com.alligator.mapper;

import com.alligator.dto.NewsDTO;
import com.alligator.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsMapper extends CoreMapper<News, NewsDTO> {
}