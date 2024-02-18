package com.alligator.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "home")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Home {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "title")
	String title;

	@Column(name = "description")
	String description;

	@Column(name = "image_url")
	String imageUrl;

	@Column(name = "url")
	String url;
}