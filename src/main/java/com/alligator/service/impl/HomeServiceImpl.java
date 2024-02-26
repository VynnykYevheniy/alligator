package com.alligator.service.impl;

import com.alligator.dto.HomeDTO;
import com.alligator.mapper.HomeMapper;
import com.alligator.repository.HomeRepository;
import com.alligator.service.HomeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
	private final HomeRepository homeRepository;
	private final HomeMapper homeMapper;

	public HomeServiceImpl(HomeRepository homeRepository, HomeMapper homeMapper) {
		this.homeRepository = homeRepository;
		this.homeMapper = homeMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<HomeDTO> findAll() {
		return homeMapper.toDto(homeRepository.findAll());
	}
}