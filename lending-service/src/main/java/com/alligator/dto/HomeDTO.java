package com.alligator.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeDTO {
	List<SliderDTO> slider = new ArrayList<>();
	AboutDTO about;
	List<BusinessCardDTO> business = new ArrayList<>();
}