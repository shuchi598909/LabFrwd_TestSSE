package com.laboratory.syetm.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laboratory.syetm.dao.ItemDAO;
import com.laboratory.syetm.service.ItemService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Insert Items")
	public ResponseEntity<List<ItemDAO>> postItems(@RequestBody List<ItemDAO> items) {
		log.info("inserting items");
		return new ResponseEntity<List<ItemDAO>>(itemService.createItem(items), HttpStatus.OK);
	}

	@GetMapping(path = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Fetches an Item")
	public ResponseEntity<List<ItemDAO>> getItems(@RequestParam(value = "categoryName") String categoryName) {
		log.info("fetching items");
		return new ResponseEntity<List<ItemDAO>>(itemService.fetchItems(categoryName), HttpStatus.OK);
	}

	@PutMapping(path = "/item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Updating an Item")
	public ResponseEntity<ItemDAO> putItem(@RequestBody ItemDAO itemDAO) {
		log.info("Updating items");
		return new ResponseEntity<ItemDAO>(itemService.updateItem(itemDAO), HttpStatus.OK);
	}
}
