package com.laboratory.syetm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laboratory.syetm.dao.ExperimentDAO;
import com.laboratory.syetm.service.ExperimentService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ExperimentController {
	
	@Autowired
	private ExperimentService experimentService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/experiment", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Inserts experiment detail")
	public ResponseEntity<ExperimentDAO> postExperiment(@RequestBody ExperimentDAO experimentDAO) {
		log.info("inserting Experiment detail");
		return new ResponseEntity<ExperimentDAO>(experimentService.createExperiment(experimentDAO), HttpStatus.OK);
	}
	

	@GetMapping(path = "/experiment", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Inserts experiment detail")
	public ResponseEntity<ExperimentDAO> getExperiment(@RequestParam String experimentName) {
		log.info("inserting Experiment detail");
		return new ResponseEntity<ExperimentDAO>(experimentService.getExperiment(experimentName), HttpStatus.OK);
	}
}
