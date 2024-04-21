package com.alligator.service.impl;

import com.alligator.dto.NewsCardDTO;
import com.alligator.dto.NewsDTO;
import com.alligator.mapper.NewsCardMapper;
import com.alligator.mapper.NewsMapper;
import com.alligator.repository.NewsRepository;
import com.alligator.service.NewsService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NewsServiceImpl implements NewsService {
	private final NewsRepository newsRepository;
	private final NewsMapper newsMapper;
	private final NewsCardMapper newsCardMapper;

	public NewsServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper, NewsCardMapper newsCardMapper) {
		this.newsRepository = newsRepository;
		this.newsMapper = newsMapper;
		this.newsCardMapper = newsCardMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<NewsCardDTO> findAll() {
		return newsCardMapper.toDto(newsRepository.findAll(Sort.by(Sort.Direction.DESC, "date")));
	}

	@Override
	@Transactional(readOnly = true)
	public NewsDTO findById(Long id) {
		return newsMapper.toDto(newsRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("News with id " + id + " was not found")));
	}
}