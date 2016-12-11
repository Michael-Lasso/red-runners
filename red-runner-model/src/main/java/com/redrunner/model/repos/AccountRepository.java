package com.redrunner.model.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.redrunner.model.domain.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
