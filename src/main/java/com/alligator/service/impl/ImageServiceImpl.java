package com.alligator.service.impl;

import com.alligator.model.Image;
import com.alligator.repository.ImageRepository;
import com.alligator.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
	private final ImageRepository imageRepository;

	public ImageServiceImpl(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	@Override
	public Image save(Image image) {
		return imageRepository.save(image);
	}

	@Override
	public Optional<Image> findById(Long id) {
		return imageRepository.findById(id);
	}
}