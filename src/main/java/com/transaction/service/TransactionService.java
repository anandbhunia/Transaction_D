package com.transaction.service;

import java.util.List;

import com.transaction.model.Transaction;

public interface TransactionService {

	public Transaction saveTransaction(Transaction transaction);
	
	public Transaction updateTransaction(Transaction transaction);
	
	public Transaction getTransaction(Integer id);
	
	public void deleteTransaction(Integer id);
	
	public List<Transaction> getAllTransaction(int pageNo, int pageSize, String sortBy);
	
	public List<Transaction> getByAmount(Double amount);
	
	public List<Transaction> getByPolicyId(Integer id);
	
	public List<Transaction> getByStatus(String status);
}
