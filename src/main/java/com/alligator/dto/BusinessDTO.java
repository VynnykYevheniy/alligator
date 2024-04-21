package com.alligator.dto;

import com.alligator.model.Contact;
import com.alligator.model.Image;
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
public class BusinessDTO {
    String iconUrl;
    String name;
    String description;
    List<BusinessCardDTO> subBusinessCard = new ArrayList<>();
    Contact contact;
    List<Image> gallery = new ArrayList<>();
}