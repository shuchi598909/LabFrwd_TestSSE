package com.laboratory.syetm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratory.syetm.model.Experiment;

@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Integer> {
	public Experiment findByExperimentName(String experimentName);

}
