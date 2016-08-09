package com.beans;

public class PaymentHistory {

	private boolean fraud;
	private String email;
	private double amount;
	private String sourceCurrency;
	private String targetCurrency;
	private String recipientName;
	private String type;
	private String iban;
	private String ip;
	private String created;
	
	public PaymentHistory() {}

	public PaymentHistory(boolean fraud, String email, double amount, String sourceCurrency, String targetCurrency,
			String recipientName, String type, String iban, String ip, String created) {
		super();
		this.fraud = fraud;
		this.email = email;
		this.amount = amount;
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.recipientName = recipientName;
		this.type = type;
		this.iban = iban;
		this.ip = ip;
		this.created = created;
	}

	public boolean isFraud() {
		return fraud;
	}

	public void setFraud(boolean fraud) {
		this.fraud = fraud;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "PaymentHistory [fraud=" + fraud + ", email=" + email + ", amount=" + amount + ", sourceCurrency="
				+ sourceCurrency + ", targetCurrency=" + targetCurrency + ", recipientName=" + recipientName + ", type="
				+ type + ", iban=" + iban + ", ip=" + ip + ", created=" + created + "]";
	}
	
}
