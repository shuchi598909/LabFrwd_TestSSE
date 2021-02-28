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
import com.laboratory.syetm.dao.ExperimentDAO;
import com.laboratory.syetm.dao.ItemDAO;
import com.laboratory.syetm.model.Category;
import com.laboratory.syetm.model.Experiment;
import com.laboratory.syetm.model.Item;
import com.laboratory.syetm.repository.ExperimentRepository;
import com.laboratory.syetm.service.impl.ExperimentServiceImpl;
import com.laboratory.syetm.service.impl.ItemServiceImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class ExperimentServiceTest {
	
	@InjectMocks
	private ExperimentServiceImpl experimentService;

	@Spy
	private ModelMapper mapper;

	@Mock
	private ExperimentRepository experimentRepository;
	
	@Mock
	private ItemServiceImpl itemService;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateExperiment() {
		Mockito.when(experimentRepository.save(Mockito.any())).thenReturn(getExperiment());
		Mockito.when(itemService.saveItems(Mockito.any())).thenReturn(getItem().get(0));
		ExperimentDAO experiment = experimentService.createExperiment(getExperimentDAO());
		assertNotNull(experiment);
		assertEquals("Random Test", experiment.getExperimentName());

	}
	
	@Test
	public void testFetchExperiment() {
		Mockito.when(experimentRepository.findByExperimentName(Mockito.anyString())).thenReturn(getExperiment());
		ExperimentDAO experiment = experimentService.getExperiment("Random Test");
		assertNotNull(experiment);
		assertEquals("Random Test", experiment.getExperimentName());

	}

	
	private ExperimentDAO getExperimentDAO() {
		ExperimentDAO experiment = new ExperimentDAO();
		experiment.setItem(getItemDAO());
		experiment.setExperimentName("Random Test");
		experiment.setExperimentPhase("Initial");
		return experiment;
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
	
	private Experiment getExperiment() {
		Experiment experiment = new Experiment();
		experiment.setItem(getItem());
		experiment.setExperimentName("Random Test");
		experiment.setExperimentPhase("Initial");
		return experiment;
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
