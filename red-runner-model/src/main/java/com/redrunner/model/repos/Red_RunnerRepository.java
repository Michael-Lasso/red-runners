package com.redrunner.model.repos;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.redrunner.model.domain.Red_Runner;

@Component
public interface Red_RunnerRepository extends JpaRepository<Red_Runner, Long> {
	Collection<Red_Runner> findByStreetId(Long streetId);
}
