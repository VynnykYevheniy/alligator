package com.alligator.mapper;

import com.alligator.dto.NewsDTO;
import com.alligator.model.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {News.class, NewsDTO.class})
public interface NewsMapper extends CoreMapper<News, NewsDTO> {
}