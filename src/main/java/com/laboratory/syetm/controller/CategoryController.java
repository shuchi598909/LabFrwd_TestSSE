package com.laboratory.syetm.controller;

import java.util.List;

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

import com.laboratory.syetm.dao.CategoryDAO;
import com.laboratory.syetm.service.CategoryService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Inserts categories")
	public ResponseEntity<List<CategoryDAO>> postCategories(@RequestBody List<CategoryDAO> categoryDAOs) {
		log.info("inserting categories");
		return new ResponseEntity<List<CategoryDAO>>(categoryService.createCategories(categoryDAOs), HttpStatus.OK);
	}
	
	@GetMapping(path = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Fetch a category")
	public ResponseEntity<CategoryDAO> getCategories(@RequestParam String categoryName) {
		log.info("Fetch a category");
		return new ResponseEntity<CategoryDAO>(categoryService.getCategories(categoryName), HttpStatus.OK);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Update category")
	public ResponseEntity<CategoryDAO> putCategories(@RequestBody CategoryDAO categoryDAO) {
		log.info("Update categorys");
		return new ResponseEntity<CategoryDAO>(categoryService.updateCategories(categoryDAO), HttpStatus.OK);
	}

}
