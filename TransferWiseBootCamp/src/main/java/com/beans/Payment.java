package com.beans;

public class Payment {
	private String id;
	private double amount;
	private String sourceCurrency;
	private String targetCurrency;
	private String recipientBankId;
	private String iban;
	private String created;
	
	public Payment() {}

	public Payment(String id, double amount, String sourceCurrency, String targetCurrency, String recipientBankId,
			String iban, String created) {
		super();
		this.id = id;
		this.amount = amount;
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.recipientBankId = recipientBankId;
		this.iban = iban;
		this.created = created;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRecipientBankId() {
		return recipientBankId;
	}

	public void setRecipientBankId(String recipientBankId) {
		this.recipientBankId = recipientBankId;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", sourceCurrency=" + sourceCurrency + ", targetCurrency="
				+ targetCurrency + ", recipientBankId=" + recipientBankId + ", iban=" + iban + ", created=" + created
				+ "]";
	}
	
}
