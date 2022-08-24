package com.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.account.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByCustomerId(Long id);
}
