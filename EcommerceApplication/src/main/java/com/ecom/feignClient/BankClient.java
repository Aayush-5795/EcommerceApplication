package com.ecom.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.dto.FundRequestDto;
import com.ecom.dto.FundTransferResponseDto;

@FeignClient("http://BANK-SERVICE/bank/banks")
public interface BankClient {
	
	@PostMapping("/fundTransfer")
	public FundTransferResponseDto fundTransfer(@RequestBody FundRequestDto request);
	
	@GetMapping("/checkamount")
	public boolean checkAmount(@RequestParam Long accountNumber, @RequestParam int amount);

}
