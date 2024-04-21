package com.alligator.service;

import com.alligator.dto.NewsCardDTO;
import com.alligator.dto.NewsDTO;

import java.util.List;

public interface NewsService {
	List<NewsCardDTO> findAll();

	NewsDTO findById(Long id);
}