package com.alligator.service;

import com.alligator.model.Image;

import java.util.Optional;

public interface ImageService {

	Image save(Image image);

	Optional<Image> findById(Long id);
}