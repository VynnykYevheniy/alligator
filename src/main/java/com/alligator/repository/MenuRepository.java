package com.alligator.repository;

import com.alligator.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	List<Menu> findAllByParentIsNull();
}