package com.laboratory.syetm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
	@SequenceGenerator(name = "item_seq", initialValue = 1001, sequenceName = "item_seq", allocationSize = 1)
	@Column(name = "item_id")
	private int itemId;
	@Column(name = "name")
	private String name;
	@Column(name = "quantity")
	private String quantity;
	@Column(name = "cat_id")
	private int categoryId;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cat_id", referencedColumnName = "cat_id", insertable = false, updatable = false)
	private Category category;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "expmnt_id")
	public int getItemId() {
		return itemId;
	}
	
	
	

}
