package com.laboratory.syetm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import com.laboratory.syetm.dao.CategoryDAO;
import com.laboratory.syetm.model.Category;
import com.laboratory.syetm.repository.CategoryRepository;
import com.laboratory.syetm.service.impl.CategoryServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class CategoryServiceTest {
	
	@InjectMocks
	private CategoryServiceImpl categoryService;

	@Spy
	private ModelMapper mapper;

	@Mock
	private CategoryRepository categoryRepository;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateCategory() {
		Mockito.when(categoryRepository.saveOrUpdate(Mockito.any())).thenReturn(getCategory().get(0));
		List<CategoryDAO> catList = categoryService.createCategories(getCategoryDAOs());
		assertNotNull(catList);
		assertEquals("Sample", catList.get(0).getCategoryName());

	}
	
	@Test
	public void testFetchItem() {
		Mockito.when(categoryRepository.findByCategoryName(Mockito.anyString())).thenReturn(getCategory().get(1));
		CategoryDAO category = categoryService.getCategories("Chemical");
		assertNotNull(category);
		assertEquals("Chemical", category.getCategoryName());

	}
	
	@Test
	public void testUpdateItem() {
		Mockito.when(categoryRepository.saveOrUpdate(Mockito.any())).thenReturn(getCategory().get(2));
		CategoryDAO category = categoryService.updateCategories(getCategoryDAOs().get(2));
		assertNotNull(category);
		assertEquals("Device", category.getCategoryName());

	}
	
	private List<CategoryDAO> getCategoryDAOs() {
		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.setCategoryName("Sample");
		
		CategoryDAO categoryDAO1 = new CategoryDAO();
		categoryDAO1.setCategoryName("Chemical");
		
		CategoryDAO categoryDAO2 = new CategoryDAO();
		categoryDAO2.setCategoryName("Device");
		
		List<CategoryDAO> categoryDAOs = new ArrayList<CategoryDAO>();
		categoryDAOs.add(categoryDAO);
		categoryDAOs.add(categoryDAO1);
		categoryDAOs.add(categoryDAO2);
		
		return categoryDAOs;
		
	}
	
	private List<Category> getCategory() {
		Category category = new Category();
		category.setCategoryName("Sample");
		
		Category category1 = new Category();
		category1.setCategoryName("Chemical");
		
		Category category2 = new Category();
		category2.setCategoryName("Device");
		
		List<Category> categories = new ArrayList<Category>();
		categories.add(category);
		categories.add(category1);
		categories.add(category2);
		
		return categories;
		
	}


}
