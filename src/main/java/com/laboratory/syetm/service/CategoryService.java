package com.laboratory.syetm.service;

import java.util.List;

import com.laboratory.syetm.dao.CategoryDAO;

public interface CategoryService {
	
	public List<CategoryDAO> createCategories(List<CategoryDAO> categoryDAOs);
	public CategoryDAO getCategories(String categoryName);
	public CategoryDAO updateCategories(CategoryDAO categoryDAOs);

}
