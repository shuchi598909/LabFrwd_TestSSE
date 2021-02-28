package com.laboratory.syetm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.laboratory.syetm.dao.ExperimentDAO;
import com.laboratory.syetm.exception.LaboratoryException;
import com.laboratory.syetm.model.Experiment;
import com.laboratory.syetm.model.Item;
import com.laboratory.syetm.repository.ExperimentRepository;
import com.laboratory.syetm.service.ExperimentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExperimentServiceImpl implements ExperimentService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ExperimentRepository experimentRepository;

	@Autowired
	private ItemServiceImpl itemService;

	@Override
	public ExperimentDAO createExperiment(ExperimentDAO experimentDAO) {
		Experiment experiment = getExperiment(experimentDAO);
		ExperimentDAO persistedExperiment = mapper.map(experimentRepository.save(experiment), ExperimentDAO.class);
		return persistedExperiment;
	}

	private Experiment getExperiment(ExperimentDAO experimentDAO) {
		List<Item> items = new ArrayList<Item>();
		try {
			if (!ObjectUtils.isEmpty(experimentDAO.getItem())) {
				List<Item> itemList = mapper.map(experimentDAO.getItem(), new TypeToken<List<Item>>() {
				}.getType());
				itemList.forEach(item -> {
					item = itemService.saveItems(item);
					items.add(item);
				});
			}
		} catch (Exception e) {
			log.error("Error occured while saving items");
			throw new LaboratoryException("Error occured while saving items", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Experiment experiment = mapper.map(experimentDAO, Experiment.class);
		experiment.setItem(items);
		return experiment;
	}

	@Override
	public ExperimentDAO getExperiment(String experimentName) {
		log.info("Fetching experiment detail with experiment name: {}", experimentName);
		Experiment experiment = experimentRepository.findByExperimentName(experimentName);
		if (ObjectUtils.isEmpty(experiment)) {
			log.error("No experiment found with experiment name :{} ", experimentName);
			throw new LaboratoryException("No experiment found", HttpStatus.NOT_FOUND);
		}
		log.info("Fetched experiment detail : {}", experiment);
		return mapper.map(experiment, ExperimentDAO.class);
	}

}
