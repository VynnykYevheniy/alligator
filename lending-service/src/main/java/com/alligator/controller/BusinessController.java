package com.alligator.controller;

import com.alligator.dto.BusinessCardDTO;
import com.alligator.dto.BusinessDTO;
import com.alligator.model.enumeration.BusinessCategory;
import com.alligator.service.BusinessService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/business", produces = MediaType.APPLICATION_JSON_VALUE)
public class BusinessController {
	private final BusinessService businessService;

	public BusinessController(BusinessService businessService) {
		this.businessService = businessService;
	}

	@GetMapping
	public List<BusinessCardDTO> getAll() {
		return businessService.findAll();
	}

	@GetMapping("/{category}")
	public BusinessDTO getBusiness(@PathVariable BusinessCategory category) {
		return businessService.findBuType(category);
	}
}