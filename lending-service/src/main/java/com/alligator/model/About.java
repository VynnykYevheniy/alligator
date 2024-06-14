package com.alligator.model;

import com.alligator.model.enumeration.AboutCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "about")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class About {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "icon_url")
	String iconUrl;

	@Column(name = "title")
	String title;

	@Column(name = "description", columnDefinition = "TEXT", length = 65535)
	String description;

	@Column(name = "category")
	@Enumerated(EnumType.STRING)
	AboutCategory category;
}