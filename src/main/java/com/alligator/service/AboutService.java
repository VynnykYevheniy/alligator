package com.alligator.service;

import com.alligator.dto.AboutDTO;
import com.alligator.model.enumeration.AboutCategory;

public interface AboutService {
	AboutDTO findByCategory(AboutCategory category);
}