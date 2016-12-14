package com.redrunner.model.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.redrunner.model.domain.Account;
import com.redrunner.model.domain.Street_Intersection;

@Component
public interface Street_IntersectionRepository extends JpaRepository<Street_Intersection, Long> {
	public Optional<Street_Intersection> findByLongitudeAndLatitude(String longitude, String latitude);
}
