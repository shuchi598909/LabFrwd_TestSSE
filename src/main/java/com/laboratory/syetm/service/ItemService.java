package com.laboratory.syetm.service;

import java.util.List;

import com.laboratory.syetm.dao.ItemDAO;

public interface ItemService {
	
	public List<ItemDAO> createItem(List<ItemDAO> item);
	public List<ItemDAO> fetchItems(String categoryName);
	public ItemDAO updateItem(ItemDAO itemDAO);

}
