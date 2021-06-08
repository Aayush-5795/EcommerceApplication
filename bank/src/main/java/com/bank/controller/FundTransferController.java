package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.FundRequestDto;
import com.bank.dto.FundTransferResponseDto;
import com.bank.service.FundTransferService;

@RestController
@RequestMapping("/banks")
public class FundTransferController {
	
	@Autowired
	FundTransferService fundTransferService;
	
	@PostMapping("/fundTransfer")
	public FundTransferResponseDto fundTransfer(@RequestBody FundRequestDto request) {
		
		return fundTransferService.fundTransfer(request);
	}
	
	@GetMapping("/checkamount")
	public boolean checkAmount(@RequestParam Long accountNumber, @RequestParam int amount) {
		return fundTransferService.checkAmount(accountNumber,amount);
		
	}

}
