package com.transaction.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.exception.ResourceNotFoundException;
import com.transaction.model.Transaction;
import com.transaction.repository.TransactionRepository;
import com.transaction.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Transaction saveTransaction(Transaction transaction) {
		
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {

		Optional<Transaction> returnedOption = transactionRepository.findById(transaction.getId());
		if (returnedOption.isPresent()) {
			return transactionRepository.save(transaction);
		}
		else {
			throw new ResourceNotFoundException("No Transaction found with id: "+transaction.getId());
		}
	}

	@Override
	public Transaction getTransaction(Integer id) {
		
		return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Transaction found with id: "+id));
	}

	@Override
	public void deleteTransaction(Integer id) {

		Optional<Transaction> returnedOption = transactionRepository.findById(id);
		if (returnedOption.isPresent()) {
			transactionRepository.delete(returnedOption.get());
		}
		else {
			throw new ResourceNotFoundException("No Transaction found with id: "+id);
		}
	}

	@Override
	public List<Transaction> getAllTransaction() {
		
		return transactionRepository.findAll();
	}

	@Override
	public List<Transaction> getByAmount(Double amount) {
		
		List<Transaction> transactionList = transactionRepository.findByAmount(amount);
		if (transactionList.isEmpty()) {
			throw new ResourceNotFoundException("No Transactions found with amount: "+amount);
		}
		else {			
			return transactionList;
		}
	}

	@Override
	public List<Transaction> getByPolicyId(Integer id) {

		List<Transaction> transactionList = transactionRepository.findByPolicyId(id);
		if (transactionList.isEmpty()) {
			throw new ResourceNotFoundException("No Transactions found with Policy id: "+id);
		}
		else {
			return transactionList;
		}
	}

	@Override
	public List<Transaction> getByStatus(String status) {

		List<Transaction> transactionList = transactionRepository.findByStatus(status); 
		if (transactionList.isEmpty()) {
			throw new ResourceNotFoundException("No Transactions found with status: "+status);
		}
		else {
			return transactionList;
		}
	}

}
