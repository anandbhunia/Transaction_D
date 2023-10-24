package com.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	public Transaction findByTransactionId(Integer id);
	
	public List<Transaction> findByAmount(Double amount);
	
	public List<Transaction> findByStatus(String status);
	
	public List<Transaction> findByPolicyId(Integer policyId);
}
