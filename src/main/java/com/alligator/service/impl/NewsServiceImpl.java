package com.alligator.service.impl;

import com.alligator.dto.NewsDTO;
import com.alligator.mapper.NewsMapper;
import com.alligator.repository.NewsRepository;
import com.alligator.service.NewsService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NewsServiceImpl implements NewsService {
	private final NewsRepository newsRepository;
	private final NewsMapper newsMapper;

	public NewsServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper) {
		this.newsRepository = newsRepository;
		this.newsMapper = newsMapper;
	}

	@Override
	public List<NewsDTO> findAll() {
		return newsMapper.toDto(newsRepository.findAll());
	}

	@Override
	public List<NewsDTO> findAllSortByDate() {
		return newsMapper.toDto(newsRepository.findAll(Sort.by(Sort.Direction.DESC, "date")));
	}

	@Override
	public NewsDTO findById(Long id) {
		return newsMapper.toDto(newsRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("News with id " + id + " was not found")));
	}
}