package com.alligator.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsCardDTO {
	Long id;
	String title;
	String description;
	String src;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	Date date;
}