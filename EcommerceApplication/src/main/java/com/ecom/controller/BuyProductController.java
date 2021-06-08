package com.ecom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.BuyProductRequestDto;
import com.ecom.dto.BuyProductResponseDto;
import com.ecom.service.BuyProductService;

@RestController
public class BuyProductController {
	
	@Autowired
	BuyProductService buyProductService;
	
	@PostMapping("/buyProducts")
	public ResponseEntity<BuyProductResponseDto> buyProducts(@RequestBody BuyProductRequestDto requestDto) {
		
		BuyProductResponseDto response=buyProductService.buyProducts(requestDto);
		
		return new ResponseEntity<BuyProductResponseDto> (response,HttpStatus.ACCEPTED);
		
	}

}
