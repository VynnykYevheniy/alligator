package com.alligator.service.impl;

import com.alligator.dto.BusinessCardDTO;
import com.alligator.dto.BusinessDTO;
import com.alligator.mapper.BusinessCardMapper;
import com.alligator.mapper.BusinessMapper;
import com.alligator.model.enumeration.BusinessCategory;
import com.alligator.repository.BusinessRepository;
import com.alligator.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;
    private final BusinessCardMapper businessCardMapper;

    @Autowired
    public BusinessServiceImpl(BusinessRepository businessRepository, BusinessMapper businessMapper, BusinessCardMapper businessCardMapper) {
        this.businessRepository = businessRepository;
        this.businessMapper = businessMapper;
		this.businessCardMapper = businessCardMapper;
	}

    @Override
    public List<BusinessCardDTO> findAll() {
        return businessCardMapper.toDto(businessRepository.findAll());
    }

    @Override
    public BusinessDTO findBuType(BusinessCategory type) {
        return businessMapper.toDto(businessRepository.findByType(type));
    }
}
