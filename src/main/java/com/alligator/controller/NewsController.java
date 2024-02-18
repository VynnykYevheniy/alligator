package com.alligator.controller;

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
	public List<NewsDTO> getAll() {
		return newsService.findAll();
	}

	@GetMapping("/desc")
	public List<NewsDTO> getAllByDateDesc() {
		return newsService.findAllSortByDate();
	}

	@PostMapping("/{id}")
	public NewsDTO getById(@PathVariable("id") Long id) {
		return newsService.findById(id);
	}
}
