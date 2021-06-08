package com.bank.service;

import com.bank.dto.FundRequestDto;
import com.bank.dto.FundTransferResponseDto;

public interface FundTransferService {

	FundTransferResponseDto fundTransfer(FundRequestDto request);

	boolean checkAmount(Long accountNumber, int amount);

}
