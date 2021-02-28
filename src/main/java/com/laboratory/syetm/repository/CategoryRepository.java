package com.laboratory.syetm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.laboratory.syetm.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	public Category findByCategoryName(String categoryName);

	public default Category saveOrUpdate(Category category) {
		Category cat = findByCategoryName(category.getCategoryName());
		
		if(!ObjectUtils.isEmpty(cat) && cat.getCategoryName().equals(category.getCategoryName())) {
				return cat;
		}
		
		return save(category);
	}

}
