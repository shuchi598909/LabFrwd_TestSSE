package com.laboratory.syetm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.laboratory.syetm.dao.ExperimentDAO;
import com.laboratory.syetm.dao.ItemDAO;
import com.laboratory.syetm.service.ExperimentService;

@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
public class ExperimentControllerTest {

	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@InjectMocks
	private ExperimentController experimentController;

	@Mock
	private ExperimentService experimentService;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(experimentController).build();
	}

	@Test
	public void testCreateCategory() throws Exception {
		Mockito.when(experimentService.createExperiment(Mockito.any())).thenReturn(getExperimentDAO());
		ExperimentDAO experiment = objectMapper.readValue(
				this.mockMvc.perform(
						post("/experiment")
						.content(objectMapper.writeValueAsString(getExperimentDAO()))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
						ExperimentDAO.class);

		assertEquals("Random Test", experiment.getExperimentName());

	}

	@Test
	public void testGetItem() throws Exception {
		Mockito.when(experimentService.getExperiment(Mockito.anyString())).thenReturn(getExperimentDAO());
		ExperimentDAO experiment = objectMapper.readValue(
				this.mockMvc.perform(
						get("/experiment")
						.param("experimentName", "Random Test")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
						ExperimentDAO.class);


		assertEquals("Random Test",experiment.getExperimentName());
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
	
}
