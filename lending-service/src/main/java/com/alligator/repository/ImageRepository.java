package com.alligator.repository;

import com.alligator.model.Image;
import com.alligator.model.enumeration.ImageCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	List<Image> findByCategory(ImageCategory imageCategory);
}