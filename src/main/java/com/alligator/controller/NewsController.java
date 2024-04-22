package com.alligator.controller;

import com.alligator.dto.NewsCardDTO;
import com.alligator.dto.NewsDTO;
import com.alligator.service.NewsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {
	private final NewsService newsService;

	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@GetMapping
	public List<NewsCardDTO> getAll() {
		return newsService.findAll();
	}
	@GetMapping("/{id}")
	public NewsDTO getById(@PathVariable("id") Long id) {
		return newsService.findById(id);
	}
}
