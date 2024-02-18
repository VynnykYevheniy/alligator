package com.alligator.service;

import com.alligator.dto.HomeDTO;

import java.util.List;

public interface HomeService {
	List<HomeDTO> findAll();
}