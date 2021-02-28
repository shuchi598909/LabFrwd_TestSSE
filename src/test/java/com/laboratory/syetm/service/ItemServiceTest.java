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
import com.laboratory.syetm.dao.ItemDAO;
import com.laboratory.syetm.model.Category;
import com.laboratory.syetm.model.Item;
import com.laboratory.syetm.repository.CategoryRepository;
import com.laboratory.syetm.repository.ItemRepository;
import com.laboratory.syetm.service.impl.ItemServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class ItemServiceTest {

	@InjectMocks
	private ItemServiceImpl itemServiceImpl;

	@Spy
	private ModelMapper mapper;

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private ItemRepository itemRepository;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateItem() {
		Mockito.when(itemRepository.save(Mockito.any())).thenReturn(getItem().get(0));
		Mockito.when(categoryRepository.saveOrUpdate(Mockito.any())).thenReturn(getItem().get(0).getCategory());
		List<ItemDAO> itemDAOs = itemServiceImpl.createItem(getItemDAO());
		assertNotNull(itemDAOs);
		assertEquals("Sodium Nitrate", itemDAOs.get(0).getName());

	}
	
	@Test
	public void testFetchItem() {
		Mockito.when(itemRepository.findAllByCategory_CategoryName(Mockito.anyString())).thenReturn(getItem());
		List<ItemDAO> itemDAOs = itemServiceImpl.fetchItems("Sample");
		assertNotNull(itemDAOs);
		assertEquals("Sodium Nitrate", itemDAOs.get(0).getName());

	}
	
	@Test
	public void testUpdateItem() {
		Mockito.when(itemRepository.saveOrUpdate(Mockito.any())).thenReturn(getItem().get(0));
		ItemDAO itemDAO = itemServiceImpl.updateItem(getItemDAO().get(0));
		assertNotNull(itemDAO);
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

	private List<Item> getItem() {
		Category category = new Category();
		category.setCategoryName("Chemical");
		Item item = new Item();
		item.setName("Sodium Nitrate");
		item.setQuantity("0.5gms");
		item.setCategory(category);

		List<Item> items = new ArrayList<Item>();
		items.add(item);

		return items;
	}
}
