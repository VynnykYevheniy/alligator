package com.alligator.model;

import com.alligator.model.enumeration.MenuCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "name")
	String name;

	@Column(name = "url")
	String url;

	@Column(name = "position")
	int position;

	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	MenuCategory category;

	@ManyToOne
	@JoinColumn(name = "parent")
	Menu parent;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade = CascadeType.REMOVE)
	List<Menu> subMenu = new ArrayList<>();
}