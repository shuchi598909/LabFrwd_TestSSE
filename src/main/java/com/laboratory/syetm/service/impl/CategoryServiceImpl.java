package com.laboratory.syetm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.laboratory.syetm.dao.CategoryDAO;
import com.laboratory.syetm.exception.LaboratoryException;
import com.laboratory.syetm.model.Category;
import com.laboratory.syetm.repository.CategoryRepository;
import com.laboratory.syetm.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<CategoryDAO> createCategories(List<CategoryDAO> categoryDAOs) {
		List<CategoryDAO> categories = new ArrayList<>();
		try {
			List<Category> cateList = mapper.map(categoryDAOs, new TypeToken<List<Category>>() {
			}.getType());

			cateList.stream().forEach(category -> {
				log.info("inserting item with item name : {}", category.getCategoryName());
				Category cat = persistCategory(category);
				categories.add(mapper.map(cat, CategoryDAO.class));
			});
		} catch (Exception e) {
			log.error("Error occured while saving categories");
			throw new LaboratoryException("Error occured while saving categories", HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return categories;

	}
	/**
	 * save the category in db.
	 * @param category
	 * @return save category
	 */

	private Category persistCategory(Category category) {
		Category persistedCategory = null;
		try {
			persistedCategory = categoryRepository.saveOrUpdate(category);
		} catch (Exception e) {
			throw new LaboratoryException("Error occured while saving category", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return persistedCategory;
	}

	@Override
	public CategoryDAO getCategories(String categoryName) {
		log.info("Fetching category");
		Category category = categoryRepository.findByCategoryName(categoryName);

		if (ObjectUtils.isEmpty(category)) {
			log.error("No category found for category name : {} ", categoryName);
			throw new LaboratoryException("No category found", HttpStatus.NOT_FOUND);
		}

		return mapper.map(category, CategoryDAO.class);
	}

	@Override
	public CategoryDAO updateCategories(CategoryDAO categoryDAO) {
		log.info("updating category name");
		Category category = null;

		try {
			category = categoryRepository.saveOrUpdate(mapper.map(categoryDAO, Category.class));
		} catch (Exception e) {
			log.error("Error occured while updating category");
			throw new LaboratoryException("Error occured while updating category", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return mapper.map(category, CategoryDAO.class);
	}

}
