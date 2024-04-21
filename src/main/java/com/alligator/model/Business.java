package com.alligator.model;

import com.alligator.model.enumeration.BusinessCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Business {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "name")
	String name;

	@Column(name = "type")
	BusinessCategory type;

	@Column(name = "icon_url")
	String iconUrl;

	@Embedded
	@Column(name = "contact")
	Contact contact;

	@Column(name = "description")
	String description;

	@Column(name = "position")
	int position;

	@Column(name = "path")
	String path;

	@ManyToOne
	@JoinColumn(name = "parent")
	Business parent;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade = CascadeType.REMOVE)
	List<Business> additionalBusinesses = new ArrayList<>();
}