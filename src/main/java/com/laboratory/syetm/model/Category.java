package com.laboratory.syetm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
	@SequenceGenerator(name = "cat_seq", sequenceName = "cat_seq", allocationSize = 1, initialValue = 2001)
	@Column(name = "cat_id")
	private int categoryId;
	@Column(name = "cat_name")
	private String categoryName;
}
