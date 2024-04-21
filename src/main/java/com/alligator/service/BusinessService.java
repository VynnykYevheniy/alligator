package com.alligator.service;

import com.alligator.dto.BusinessCardDTO;
import com.alligator.dto.BusinessDTO;
import com.alligator.model.enumeration.BusinessCategory;

import java.util.List;

public interface BusinessService {
    List<BusinessCardDTO> findAll();

    BusinessDTO findBuType(BusinessCategory type);
}
