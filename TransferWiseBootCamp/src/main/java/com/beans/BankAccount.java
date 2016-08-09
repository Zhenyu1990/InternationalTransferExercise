package com.beans;

public class BankAccount {

	private String id;
	private String accountNumber;
	private String accountName;
	private String bankId;
	private String currency;
	private double balance;
	
	public BankAccount() {}

	public BankAccount(String id, String accountNumber, String accountName, String bankId, String currency,
			double balance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.bankId = bankId;
		this.currency = currency;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", accountNumber=" + accountNumber + ", accountName=" + accountName
				+ ", bankId=" + bankId + ", currency=" + currency + ", balance=" + balance + "]";
	}
	
}
