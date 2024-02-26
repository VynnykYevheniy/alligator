package com.alligator.controller;

import com.alligator.dto.MenuDTO;
import com.alligator.service.MenuService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
	private final MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping("/sidebar")
	public List<MenuDTO> getMenu() {
		return menuService.findAll();
	}

	@PostMapping("/create")
	public List<MenuDTO> create(@RequestBody List<MenuDTO> menu) {
		return menuService.save(menu);
	}

	@PostMapping("/update")
	public List<MenuDTO> update(@RequestBody List<MenuDTO> menu) {
		return menuService.update(menu);
	}

	@PostMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		menuService.delete(id);
	}
}
