package com.alligator.service;

import com.alligator.dto.AboutDTO;
import com.alligator.model.About;
import com.alligator.model.Image;
import com.alligator.model.enumeration.AboutCategory;
import com.alligator.model.enumeration.ImageCategory;

import java.util.List;

public interface AboutService {
	AboutDTO findByCategory(AboutCategory category);
}