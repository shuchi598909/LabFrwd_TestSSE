package com.laboratory.syetm.dao;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperimentDAO {
	@NonNull
	private String experimentName;
	private String experimentPhase;
	private String experimentDescription;
	private List<ItemDAO> item;
}
