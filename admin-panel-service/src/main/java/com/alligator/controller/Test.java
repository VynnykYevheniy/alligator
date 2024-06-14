package com.alligator.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/panel", produces = MediaType.APPLICATION_JSON_VALUE)
public class Test {
	@GetMapping
	public String test() {
		return "Hi! This is Admin panel";
	}
}
