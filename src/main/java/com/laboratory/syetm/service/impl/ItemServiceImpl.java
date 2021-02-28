package com.laboratory.syetm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.laboratory.syetm.dao.ItemDAO;
import com.laboratory.syetm.exception.LaboratoryException;
import com.laboratory.syetm.model.Category;
import com.laboratory.syetm.model.Item;
import com.laboratory.syetm.repository.CategoryRepository;
import com.laboratory.syetm.repository.ItemRepository;
import com.laboratory.syetm.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<ItemDAO> createItem(List<ItemDAO> itemDAOs) {

		List<Item> itemList = mapper.map(itemDAOs, new TypeToken<List<Item>>() {
		}.getType());

		List<ItemDAO> items = new ArrayList<>();
		itemList.stream().forEach(item -> {
			log.info("inserting item with item name : {}", item.getName());
			Item persistedItem = saveItems(item);
			items.add(mapper.map(persistedItem, ItemDAO.class));
		});

		return items;
	}

	public Item saveItems(Item item) {
		try {
		Category category = categoryRepository.saveOrUpdate(item.getCategory());
		item.setCategoryId(category.getCategoryId());
		item.setCategory(category);
		Item persistedItem = itemRepository.save(item);
		return persistedItem;
		} catch(Exception e) {
			log.error("Exception occured while saving items", e.getMessage());
			throw new LaboratoryException("Error occured while saving items", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

	@Override
	public List<ItemDAO> fetchItems(String categoryName) {
		log.info("fetching items for category name : {} ", categoryName);
		List<Item> items = itemRepository.findAllByCategory_CategoryName(categoryName);
		
		if(ObjectUtils.isEmpty(items)) {
			log.error("No Item found for category name : {} ", categoryName);
			throw new  LaboratoryException("No item found", HttpStatus.NOT_FOUND);
		}
		
		List<ItemDAO> itemList = mapper.map(items, new TypeToken<List<ItemDAO>>() {
		}.getType());

		return itemList;
	}

	@Override
	public ItemDAO updateItem(ItemDAO itemDAO) {
		log.info("Updating item");
		Item item = mapper.map(itemDAO, Item.class);
		try {
			item = itemRepository.saveOrUpdate(item);
		} catch (Exception e) {
			log.error("Error while updating item with item name :{}", itemDAO.getName());
			throw new LaboratoryException("Error while updating item", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return mapper.map(item, ItemDAO.class);
	}

}
