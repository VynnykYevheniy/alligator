package com.alligator.service;

import com.alligator.dto.NewsDTO;

import java.util.List;

public interface NewsService {
	List<NewsDTO> findAll();

	List<NewsDTO> findAllSortByDate();

	NewsDTO findById(Long id);
}