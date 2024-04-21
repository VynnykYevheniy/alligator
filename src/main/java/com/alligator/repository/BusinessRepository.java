package com.alligator.repository;

import com.alligator.model.Business;
import com.alligator.model.enumeration.BusinessCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    Business findByType(BusinessCategory type);
}