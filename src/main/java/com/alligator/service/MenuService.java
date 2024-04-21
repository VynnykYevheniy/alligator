package com.alligator.service;

import com.alligator.dto.MenuDTO;

import java.util.List;

public interface MenuService {
	List<MenuDTO> findAll();
	List<MenuDTO> findSideBar();

	List<MenuDTO> save(List<MenuDTO> menu);

	List<MenuDTO> update(List<MenuDTO> menu);

	void delete(Long id);
}