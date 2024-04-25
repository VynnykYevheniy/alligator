package com.alligator.service.impl;

import com.alligator.dto.MenuDTO;
import com.alligator.mapper.MenuMapper;
import com.alligator.model.Menu;
import com.alligator.model.enumeration.MenuCategory;
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
		List<Menu> mainMenus = menuRepository.findAllByParentIsNullAndCategory(MenuCategory.MAIN);
		List<MenuDTO> menuDTOs = mainMenus.stream()
				.map(menuMapper::toDto)
				.collect(Collectors.toList());
		return sortByPosition(menuDTOs);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MenuDTO> findSideBar() {
		List<Menu> menus = menuRepository.findAllByParentIsNullAndCategory(MenuCategory.SIDEBAR);
		List<MenuDTO> dtos = menus.stream()
				.map(menuMapper::toDto)
				.collect(Collectors.toList());
		return sortByPosition(dtos);
	}

	@Override
	@Transactional
	public List<MenuDTO> save(List<MenuDTO> menu) {
		List<Menu> menuEntity = menu.stream()
				.map(menuMapper::toEntity)
				.toList();
		saveRecursively(menuEntity);
		return sortByPosition(menuMapper.toDto(menuEntity));
	}

	@Override
	@Transactional
	public List<MenuDTO> update(List<MenuDTO> menu) {
		List<Menu> entityMenus = menu.stream()
				.map(menuMapper::toEntity)
				.toList();
		saveRecursively(entityMenus);
		return sortByPosition(menuMapper.toDto(entityMenus));
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

	private void saveRecursively(List<Menu> menus) {
		for (Menu menu : menus) {
			saveRecursively(menu);
		}
	}

	private void saveRecursively(Menu menu) {
		menu = menuRepository.save(menu);
		for (Menu subMenu : menu.getSubMenu()) {
			subMenu.setParent(menu);
			saveRecursively(subMenu);
		}
	}
}