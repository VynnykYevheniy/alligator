package com.alligator.service.impl;

import com.alligator.dto.AboutDTO;
import com.alligator.mapper.AboutMapper;
import com.alligator.model.enumeration.AboutCategory;
import com.alligator.repository.AboutRepository;
import com.alligator.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutServiceImpl implements AboutService {
	private final AboutRepository aboutRepository;
	private final AboutMapper aboutMapper;

	@Autowired
	public AboutServiceImpl(AboutRepository aboutRepository, AboutMapper aboutMapper) {
		this.aboutRepository = aboutRepository;
		this.aboutMapper = aboutMapper;
	}

	@Override
	public AboutDTO findByCategory(AboutCategory category) {
		return aboutMapper.toDto(aboutRepository.findByCategory(category));
	}
}