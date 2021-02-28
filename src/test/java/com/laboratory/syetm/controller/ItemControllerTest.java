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
import com.laboratory.syetm.dao.ItemDAO;
import com.laboratory.syetm.service.ItemService;


@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
public class ItemControllerTest {
	
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@InjectMocks
	private ItemController itemController;
	
	@Mock
	private ItemService itemService;
	
	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
	}
	
	@Test
	public void testCreateItem() throws Exception {
		Mockito.when(itemService.createItem(Mockito.any())).thenReturn(getItemDAO());
		ItemDAO[] itemDAO = objectMapper.readValue(this.mockMvc.perform(
				post("/item")
				.content(objectMapper.writeValueAsString(getItemDAO()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(),ItemDAO[].class);
		
		assertEquals("Sodium Nitrate", itemDAO[0].getName());
	}
	
	@Test
	public void testGetItem() throws Exception {
		Mockito.when(itemService.fetchItems(Mockito.anyString())).thenReturn(getItemDAO());
		ItemDAO[] itemDAO = objectMapper.readValue(this.mockMvc.perform(
				get("/item")
				.param("categoryName", "Sample")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(),ItemDAO[].class);
		
		assertEquals("Sodium Nitrate", itemDAO[0].getName());
		
	}
	
	@Test
	public void testPutItem() throws Exception {
		Mockito.when(itemService.updateItem(Mockito.any())).thenReturn(getItemDAO().get(0));
		ItemDAO itemDAO = objectMapper.readValue(this.mockMvc.perform(
				put("/item")
				.content(objectMapper.writeValueAsString(getItemDAO().get(0)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(),ItemDAO.class);
		
		assertEquals("Sodium Nitrate", itemDAO.getName());
		
	}
	
	private List<ItemDAO> getItemDAO() {
		CategoryDAO category = new CategoryDAO();
		category.setCategoryName("Chemical");
		ItemDAO item = new ItemDAO();
		item.setName("Sodium Nitrate");
		item.setQuantity("0.5gms");
		item.setCategory(category);
		
		List<ItemDAO> itemsDaos = new ArrayList<ItemDAO>();
		itemsDaos.add(item);
		
		return itemsDaos;
	}
}