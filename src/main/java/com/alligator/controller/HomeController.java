package com.alligator.controller;

import com.alligator.dto.HomeDTO;
import com.alligator.service.HomeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/home", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {
	private final HomeService homeService;

	public HomeController(HomeService homeService) {
		this.homeService = homeService;
	}

	@GetMapping
	public List<HomeDTO> getAll() {
		return homeService.findAll();
	}
}
