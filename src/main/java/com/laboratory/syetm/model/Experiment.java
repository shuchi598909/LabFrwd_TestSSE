package com.laboratory.syetm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "experiment")
@NoArgsConstructor
@AllArgsConstructor
public class Experiment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expmnt_seq")
	@SequenceGenerator(name = "expmnt_seq", sequenceName = "expmnt_seq", allocationSize = 1, initialValue = 3001)
	@Column(name = "expmnt_id")
	private int experimentId;
	@Column(name = "expmnt_name")
	private String experimentName;
	@Column(name = "expmnt_phase")
	private String experimentPhase;
	@Column(name = "expmnt_description")
	private String experimentDescription;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "EXPMNT_ITEM", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = {
			@JoinColumn(name = "expmnt_id") })
	@JoinColumn(updatable = true, insertable = false)
	private List<Item> item = new ArrayList<>();

}
