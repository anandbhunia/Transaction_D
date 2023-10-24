package com.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.dto.ApiResponseDto;
import com.transaction.model.Transaction;
import com.transaction.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/add")
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
		
		Transaction newTransaction = transactionService.saveTransaction(transaction);
		return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Transaction> changeTransaction(@RequestBody Transaction transaction) {
		
		Transaction updatedTransaction = transactionService.updateTransaction(transaction);
		return ResponseEntity.status(HttpStatus.OK).body(updatedTransaction);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable Integer id) {
	
		Transaction transaction = transactionService.getTransaction(id);
		return ResponseEntity.status(HttpStatus.OK).body(transaction);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponseDto> removeTransaction(@PathVariable Integer id) {
	
		transactionService.deleteTransaction(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("Record deleted successfully."));
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Transaction>> getAllTransaction() {
		
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAllTransaction());
	}
	
	@GetMapping("/get/byAmount")
	public ResponseEntity<List<Transaction>> getByAmount(@RequestParam("amount") Double amount) {
		
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.getByAmount(amount));
	}
	
	@GetMapping("/get/byPolicyId")
	public ResponseEntity<List<Transaction>> getByPolicyId(@RequestParam("id") Integer id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.getByPolicyId(id));
	}

	@GetMapping("/get/byStatus")
	public ResponseEntity<List<Transaction>> getByStatus(@RequestParam("status") String status) {
		
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.getByStatus(status));
	}
}
