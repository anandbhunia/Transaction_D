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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/add")
	@ApiOperation(value = "Request to add transaction")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"), 
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Error")})
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
		
		Transaction newTransaction = transactionService.saveTransaction(transaction);
		return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
	}
	
	@PutMapping("/update")
	@ApiOperation(value = "Request to edit transaction")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), 
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Error")})
	public ResponseEntity<Transaction> changeTransaction(@RequestBody Transaction transaction) {
		
		Transaction updatedTransaction = transactionService.updateTransaction(transaction);
		return ResponseEntity.status(HttpStatus.OK).body(updatedTransaction);
	}
	
	@GetMapping("/get/{id}")
	@ApiOperation(value = "Request to get transaction using id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), 
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Error")})
	public ResponseEntity<Transaction> getTransaction(@PathVariable Integer id) {
	
		Transaction transaction = transactionService.getTransaction(id);
		return ResponseEntity.status(HttpStatus.OK).body(transaction);
	}
	
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Request to delete transaction using id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), 
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Error")})
	public ResponseEntity<ApiResponseDto> removeTransaction(@PathVariable Integer id) {
	
		transactionService.deleteTransaction(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("Record deleted successfully."));
	}
	
	@GetMapping("/getAll")
	@ApiOperation(value = "Request to get all transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), 
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Error")})
	public ResponseEntity<List<Transaction>> getAllTransaction(@RequestParam(defaultValue = "1", value = "page") int pageNo,
			@RequestParam(defaultValue = "10", name = "records") int pageSize,
			@RequestParam(defaultValue = "transactionId", name = "sort") String sortBy) {
		pageNo = pageNo > 0 ? pageNo -1 : 0;
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAllTransaction(pageSize, pageSize, sortBy));
	}
	
	@GetMapping("/get/byAmount")
	@ApiOperation(value = "Request to get transactions using amount")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), 
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Error")})
	public ResponseEntity<List<Transaction>> getByAmount(@RequestParam("amount") Double amount) {
		
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.getByAmount(amount));
	}
	
	@GetMapping("/get/byPolicyId")
	@ApiOperation(value = "Request to get transactions using policy id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), 
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Error")})
	public ResponseEntity<List<Transaction>> getByPolicyId(@RequestParam("id") Integer id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.getByPolicyId(id));
	}

	@GetMapping("/get/byStatus")
	@ApiOperation(value = "Request to get transactions by status")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), 
			@ApiResponse(code = 404, message = "Resource Not Found"),
			@ApiResponse(code = 500, message = "Internal Error")})
	public ResponseEntity<List<Transaction>> getByStatus(@RequestParam("status") String status) {
		
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.getByStatus(status));
	}
}
