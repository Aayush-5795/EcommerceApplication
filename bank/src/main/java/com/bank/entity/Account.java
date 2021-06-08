package com.bank.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNumber;
	private String branch;
	private int balance;
	private LocalDate accountOpeningDate;
	@OneToOne
	private User user;
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public LocalDate getAccountOpeningDate() {
		return accountOpeningDate;
	}
	public void setAccountOpeningDate(LocalDate accountOpeningDate) {
		this.accountOpeningDate = accountOpeningDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Account(Long accountNumber, String branch, int balance, LocalDate accountOpeningDate, User user) {
		super();
		this.accountNumber = accountNumber;
		this.branch = branch;
		this.balance = balance;
		this.accountOpeningDate = accountOpeningDate;
		this.user = user;
	}
	public Account() {
		
	}
	
	
	

}
