package com.alligator.service.impl;

import com.alligator.dto.SliderDTO;
import com.alligator.mapper.SliderMapper;
import com.alligator.repository.SliderRepository;
import com.alligator.service.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SliderServiceImpl implements SliderService {
	private final SliderRepository sliderRepository;
	private final SliderMapper sliderMapper;

	@Autowired
	public SliderServiceImpl(SliderRepository sliderRepository, SliderMapper sliderMapper) {
		this.sliderRepository = sliderRepository;
		this.sliderMapper = sliderMapper;
	}

	@Override
	public List<SliderDTO> findAll() {
		return sliderMapper.toDto(sliderRepository.findAll());
	}
}
