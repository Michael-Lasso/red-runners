package com.redrunner.model.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.redrunner.model.domain.Street_Intersection;

@Component
public interface Street_IntersectionRepository extends JpaRepository<Street_Intersection, Long> {
//    Collection<Bookmark> findByAccountUsername(String username);
}
