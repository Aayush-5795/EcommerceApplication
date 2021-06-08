package com.bank.serviceImpl;

import java.time.LocalDate;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.FundRequestDto;
import com.bank.dto.FundTransferResponseDto;
import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.exception.AccountBalanceNotSuficientFound;
import com.bank.exception.AccountNotFound;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.repository.UserRepository;
import com.bank.service.FundTransferService;

@Service
public class FundTransferServiceImpl implements FundTransferService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public FundTransferResponseDto fundTransfer(FundRequestDto request) {
		
		FundTransferResponseDto response=new FundTransferResponseDto();
		
		Optional<Account> source=accountRepository.findByAccountNumber(request.getFromAccount());
		if(!source.isPresent()) {
			throw new AccountNotFound("Source Account Not Found");
		}
		
		Optional<Account> to=accountRepository.findByAccountNumber(request.getToAccount());
		if(!to.isPresent()) {
			throw new AccountNotFound("To Account Not Found");
		}
		
		if((source.get().getBalance()<request.getAmount())) {
			throw new AccountBalanceNotSuficientFound("Account Balance Not Sufficient in Source Account..");
		}
		
		int sourceBalance=source.get().getBalance()-request.getAmount();
		int toBalance=to.get().getBalance()+request.getAmount();
		Transaction transaction=new Transaction();
		transaction.setFromAccount(source.get().getAccountNumber());
		transaction.setToAccount(to.get().getAccountNumber());
		transaction.setAmount(request.getAmount());
		transaction.setRemark("Fund Transfer Successfully..");
		transaction.setTransactionDate(LocalDate.now());
		transactionRepository.save(transaction);
		
		source.get().setBalance(sourceBalance);
		accountRepository.save(source.get());
		
		to.get().setBalance(toBalance);
		accountRepository.save(to.get());
		
		response.setStatusCode(200);
		response.setStatusMessage("Fund Transfer Successfully..");
		
		return response;
	}

	@Override
	public boolean checkAmount(Long accountNumber, int amount) {
		
		Optional<Account> source=accountRepository.findByAccountNumber(accountNumber);
		if(!source.isPresent()) {
			throw new AccountNotFound("Source Account Not Found");
		}
		
		if(!(source.get().getBalance()>=amount)) {
			return false;
		}
		return true;
	}

}
