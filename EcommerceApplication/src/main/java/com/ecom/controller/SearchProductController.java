package com.ecom.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ecom.dto.SearchProductRequestDto;
import com.ecom.dto.SearchProductResponseDto;
import com.ecom.dto.SearchResponse;
import com.ecom.service.SearchProductService;

@RestController
@RequestMapping("/products")
public class SearchProductController {
	
	@Autowired
	SearchProductService searchProductService;
	
	/**
	 * logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(SearchProductController.class);

	@PostMapping("/search")
	public ResponseEntity<SearchResponse> getProduct(@Valid @RequestBody SearchProductRequestDto searchProductRequestDto){
		logger.info("Inside getProduct() Method.");
		SearchResponse searchResult=searchProductService.getProduct(searchProductRequestDto);
		logger.info("Exiting getProduct() Method.");
		return new ResponseEntity<SearchResponse>(searchResult, HttpStatus.OK);
	}
	
	
	
}
