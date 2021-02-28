package com.laboratory.syetm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import com.laboratory.syetm.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	public List<Item> findAllByCategory_CategoryName(String categoryName);
	
	public Item findByName(String itemName);	
	
	public default Item saveOrUpdate(Item item) {
		Item existingItem = findByName(item.getName());
		if(!ObjectUtils.isEmpty(existingItem)) {
			item.setCategory(Optional.ofNullable(item.getCategory()).orElse(existingItem.getCategory()));
			item.setName(Optional.ofNullable(item.getName()).orElse(existingItem.getName()));
			item.setQuantity(Optional.ofNullable(item.getQuantity()).orElse(existingItem.getName()));
		}
		
		return item;
	}

}
