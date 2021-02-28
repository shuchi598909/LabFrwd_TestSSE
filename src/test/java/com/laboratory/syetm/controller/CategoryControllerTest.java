package com.laboratory.syetm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratory.syetm.dao.CategoryDAO;
import com.laboratory.syetm.service.CategoryService;

@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
public class CategoryControllerTest {
	
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@InjectMocks
	private CategoryController categoryController;
	
	@Mock
	private CategoryService categoryService;
	
	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}
	
	@Test
	public void testCreateCategory() throws Exception {
		Mockito.when(categoryService.createCategories(Mockito.any())).thenReturn(getCategoryDAOs());
		CategoryDAO[] category = objectMapper.readValue(this.mockMvc.perform(
				 post("/category")
				.content(objectMapper.writeValueAsString(getCategoryDAOs()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(), CategoryDAO[].class);
		
		assertEquals("Sample", category[0].getCategoryName());
		
	}
	
	
	@Test
	public void testGetItem() throws Exception {
		Mockito.when(categoryService.getCategories(Mockito.anyString())).thenReturn(getCategoryDAOs().get(0));
		CategoryDAO category = objectMapper.readValue(this.mockMvc.perform(
				get("/category")
				.param("categoryName", "Sample")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(),CategoryDAO.class);
		
		assertEquals("Sample", category.getCategoryName());
	}
	
	@Test
	public void testPutCategory() throws Exception {
		Mockito.when(categoryService.updateCategories(Mockito.any())).thenReturn(getCategoryDAOs().get(0));
		CategoryDAO category = objectMapper.readValue(this.mockMvc.perform(
				put("/category")
				.content(objectMapper.writeValueAsString(getCategoryDAOs().get(0)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(),CategoryDAO.class);
		
		assertEquals("Sample", category.getCategoryName());
		
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

}
