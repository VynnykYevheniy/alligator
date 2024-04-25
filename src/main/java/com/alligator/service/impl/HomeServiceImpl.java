package com.alligator.service.impl;

import com.alligator.dto.AboutDTO;
import com.alligator.dto.BusinessCardDTO;
import com.alligator.dto.HomeDTO;
import com.alligator.dto.SliderDTO;
import com.alligator.model.Image;
import com.alligator.model.enumeration.AboutCategory;
import com.alligator.model.enumeration.ImageCategory;
import com.alligator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
	private final AboutService aboutService;
	private final SliderService sliderService;
	private final BusinessService businessService;

	@Autowired
	public HomeServiceImpl(AboutService aboutService, SliderService sliderService, BusinessServiceImpl businessService) {
		this.aboutService = aboutService;
		this.sliderService = sliderService;
		this.businessService = businessService;
	}

	@Override
	@Transactional(readOnly = true)
	public HomeDTO find() {
		List<SliderDTO> slider = sliderService.findAll();
		AboutDTO aboutDTO = aboutService.findByCategory(AboutCategory.HOME);
		List<BusinessCardDTO> businessCardDTO = businessService.findAll();
		return  new HomeDTO(slider, aboutDTO, businessCardDTO);
	}

}