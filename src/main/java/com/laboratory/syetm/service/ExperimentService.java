package com.laboratory.syetm.service;

import com.laboratory.syetm.dao.ExperimentDAO;

public interface ExperimentService {
	
	public ExperimentDAO createExperiment(ExperimentDAO experiment);
	public ExperimentDAO getExperiment(String experimentName);

}
