package com.ecom.controller.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.ecom.controller.SearchProductController;
import com.ecom.dto.SearchProductRequestDto;
import com.ecom.dto.SearchResponse;
import com.ecom.service.SearchProductService;

@SpringBootTest
public class SearchProductControllerTest {

	@Mock
	SearchProductService searchProductService;
	
	@InjectMocks
	SearchProductController searchProductController;
	
	@Test
	void testGetProduct() {
		SearchProductRequestDto requestDto=new SearchProductRequestDto();
		SearchResponse responsee=new SearchResponse();
		responsee.setStatusCode(700);
		Mockito.when(searchProductService.getProduct(requestDto)).thenReturn(responsee);
		ResponseEntity<SearchResponse> response=searchProductController.getProduct(requestDto);
		Assertions.assertEquals(700, response.getBody().getStatusCode());
		
	}
}
