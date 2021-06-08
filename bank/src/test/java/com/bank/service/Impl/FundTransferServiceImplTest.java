package com.bank.service.Impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.constraints.AssertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Optionals;
import org.junit.jupiter.api.Assertions;
import com.bank.dto.FundRequestDto;
import com.bank.dto.FundTransferResponseDto;
import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.entity.User;
import com.bank.exception.AccountBalanceNotSuficientFound;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.repository.UserRepository;
import com.bank.serviceImpl.FundTransferServiceImpl;

@SpringBootTest
public class FundTransferServiceImplTest {
	
	@Mock
	UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock
	TransactionRepository transactionRepository;
	
	@InjectMocks
	FundTransferServiceImpl fundTransferServiceImpl;
	
	static FundTransferResponseDto fundTransferResponseDto;
	static FundRequestDto fundRequestDto;
	static Account account;
	static Transaction transaction;
	
	@BeforeAll
	public static void setUp() {
		fundTransferResponseDto=new FundTransferResponseDto();
		fundTransferResponseDto.setStatusCode(200);
		fundTransferResponseDto.setStatusMessage("Fund Transfer Successfully..");
		
		fundRequestDto=new FundRequestDto();
		fundRequestDto.setFromAccount((long) 51);
		fundRequestDto.setToAccount((long) 52);
		fundRequestDto.setAmount(2000);
		
		account=new Account();
		account.setAccountNumber((long) 51);
		account.setAccountOpeningDate(LocalDate.now());
		account.setBalance(5000);
		account.setBranch("Ngp");
		account.setUser(new User());
	}
	
	@Test
	public void CheckaccountTest() {
		Assertions.assertThrows(AccountNotFoundException.class,
                () ->  fundTransferServiceImpl.fundTransfer(fundRequestDto));
		
	}
	
	@Test
	public void CheckToaccountTest() {
		Assertions.assertThrows(AccountBalanceNotSuficientFound.class,
                () ->  fundTransferServiceImpl.fundTransfer(fundRequestDto));
		
	}
	
	@Test
	public void checkfundTransfer() {
		Account ac=getAccount(); 
		Account acount1=entityManager.persist(ac);
		Optional<Account> SaveInDB=Optional.of(acount1);
		Optional<Account> getFromDB=accountRepository.findByAccountNumber(acount1.getAccountNumber());
		assertThat(SaveInDB).isEqualTo(getFromDB);
		
	}
	
	private Account getAccount() {
		Account account=new Account();
		account.setAccountNumber((long) 51);
		account.setAccountOpeningDate(LocalDate.now());
		account.setBalance(5000);
		account.setBranch("Ngp");
		account.setUser(new User());
		return account;
	}
	
	/*
	 * @Test public void checkFromAccountTest() { Account account1=new Account();
	 * Account account2=new Account();
	 * 
	 * Mockito.when(accountRepository.findByAccountNumber(Mockito.anyLong())).
	 * thenReturn(Optional.of(account1));
	 * Mockito.when(accountRepository.findByAccountNumber(Mockito.anyLong())).
	 * thenReturn(Optional.of(account2)); Boolean result=true;
	 * Mockito.when(Mockito.anyLong()>Mockito.anyLong()).thenReturn(result);
	 * 
	 * FundTransferResponseDto
	 * response=fundTransferServiceImpl.fundTransfer(fundRequestDto); }
	 */
	
	
	
	/*
	 * @Test
	 * 
	 * @DisplayName("Check fund Transfer : Positive Scenario") public void
	 * fundTransferTest() {
	 * 
	 * 
	 * 
	 * FundTransferResponseDto expected=new FundTransferResponseDto();
	 * expected.setStatusCode(200);
	 * expected.setStatusMessage("Fund Transfer Successfully..");
	 * 
	 * 
	 * assertEquals(expected, result);
	 * 
	 * }
	 */
	
	

}
