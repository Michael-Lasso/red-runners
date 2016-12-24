package com.redrunner.model.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.redrunner.model.domain.RedRunner;

@Component
public interface RedRunnerRepository extends JpaRepository<RedRunner, Long> {
	Optional<RedRunner> findByStreetid(Long streetid);
}
