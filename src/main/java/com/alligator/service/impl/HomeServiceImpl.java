package com.alligator.service.impl;

import com.alligator.dto.HomeDTO;
import com.alligator.mapper.HomeMapper;
import com.alligator.repository.HomeRepository;
import com.alligator.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
	@Autowired
	HomeRepository homeRepository;
	@Autowired
	HomeMapper homeMapper;

	@Override
	public List<HomeDTO> findAll() {
		return homeMapper.toDto(homeRepository.findAll());
	}
}
