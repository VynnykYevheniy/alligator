package com.alligator.service.impl;

import com.alligator.dto.MenuDTO;
import com.alligator.mapper.MenuMapper;
import com.alligator.model.Menu;
import com.alligator.repository.MenuRepository;
import com.alligator.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
	private final MenuRepository menuRepository;
	private final MenuMapper menuMapper;

	@Autowired
	public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper) {
		this.menuRepository = menuRepository;
		this.menuMapper = menuMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MenuDTO> findAll() {
		return sortByPosition(menuRepository.findAllByParentIsNull().stream()
				.map(menuMapper::toDto)
				.collect(Collectors.toList()));
	}

	@Override
	@Transactional
	public List<MenuDTO> save(List<MenuDTO> menu) {
		List<Menu> menuEntity = menu.stream()
				.map(menuMapper::toEntity)
				.toList();
		reverseSave(menuEntity);
		return sortByPosition(menuMapper.toDto(menuEntity));
	}

	@Override
	@Transactional
	public List<MenuDTO> update(List<MenuDTO> menu) {
		List<Menu> menuEntity = menu.stream()
				.map(menuMapper::toEntity)
				.toList();
		reverseSave(menuEntity);
		return sortByPosition(menuMapper.toDto(menuEntity));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Menu menu = menuRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Menu not found with id " + id));
		menuRepository.deleteById(menu.getId());
	}

	private List<MenuDTO> sortByPosition(List<MenuDTO> menuDTOs) {
		return menuDTOs.stream()
				.sorted(Comparator.comparingInt(MenuDTO::getPosition))
				.peek(menuDTO -> menuDTO.setSubMenu(sortByPosition(menuDTO.getSubMenu())))
				.collect(Collectors.toList());
	}

	private void reverseSave(List<Menu> menus) {
		for (Menu menu : menus) {
			saveMenuRecursively(menu);
		}
	}

	private void saveMenuRecursively(Menu menu) {
		menu = menuRepository.save(menu);
		for (Menu subMenu : menu.getSubMenu()) {
			subMenu.setParent(menu);
			saveMenuRecursively(subMenu);
		}
	}
}