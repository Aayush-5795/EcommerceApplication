package com.bank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.dto.FundRequestDto;
import com.bank.dto.FundTransferResponseDto;
import com.bank.service.FundTransferService;

@ExtendWith(MockitoExtension.class)
public class FundTransferControllerTest {
	
	@Mock
	FundTransferService fundTransferService;
	
	@InjectMocks
	FundTransferController fundTransferController;
	
	static FundTransferResponseDto fundTransferResponseDto;
	
	static FundRequestDto fundRequestDto;
	
	@BeforeAll
	public static void setUp() {
		fundTransferResponseDto=new FundTransferResponseDto();
		fundTransferResponseDto.setStatusCode(200);
		fundTransferResponseDto.setStatusMessage("Fund Transfer Successfully..");
		
		fundRequestDto=new FundRequestDto();
		fundRequestDto.setFromAccount((long) 51);
		fundRequestDto.setToAccount((long) 52);
		fundRequestDto.setAmount(2000);
		
	}
	
	
	@Test
	@DisplayName("Fund Transfer")
	public void fundTransferTest() {
		
		when(fundTransferService.fundTransfer(fundRequestDto)).thenReturn(fundTransferResponseDto);
		
		FundTransferResponseDto response=fundTransferController.fundTransfer(fundRequestDto);
		
		assertEquals(fundTransferResponseDto, response);
		
	}
	
	@Test
	@DisplayName("Check Amount ")
	public void checkAmountTest() {
		
		when(fundTransferService.checkAmount((long)51, 5000)).thenReturn(true);
		
		Boolean result=fundTransferController.checkAmount((long) 51, 5000);
		
		assertEquals(true, result);
		
	}
	

}
