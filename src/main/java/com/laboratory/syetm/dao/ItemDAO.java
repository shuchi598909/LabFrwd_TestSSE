package com.laboratory.syetm.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDAO {
	@NonNull
	private String name;
	@NonNull
	private String quantity;
	private CategoryDAO category;

}
