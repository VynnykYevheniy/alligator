package com.alligator.repository;

import com.alligator.model.About;
import com.alligator.model.enumeration.AboutCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends JpaRepository<About, Long> {
	About findByCategory(AboutCategory category);
}