package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.beans.Bank;
import com.beans.BankAccount;
import com.beans.Payment;
import com.beans.PaymentHistory;
import com.beans.Quote;
import com.beans.Task5Payment;
import com.beans.Task6Payment;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonParser {

	// general
	public void ParseTaskJson(String jsonString) {
		JSONObject jo = JSONObject.fromObject(jsonString);
        String name = jo.getString("name");
        String description = jo.getString("description");
        String hint = jo.getString("hint");
        JSONArray apis = jo.getJSONArray("helpfulApiCalls");
        System.out.println("Task name: ");
        System.out.println("    " + name);
        System.out.println("Description: ");
        System.out.println("    " + description);
        System.out.println("APIs: ");
        for(int i = 0; i < apis.size(); i++) {
        	System.out.println("    " + apis.getString(i));
        }
        System.out.println("Hint: ");
        System.out.println("    " + hint);
	}
	
	// task3
	public ArrayList<Bank> ParseBankJson(String bankJson) {
		ArrayList<Bank> banks = new ArrayList<Bank>();
		JSONArray ja = JSONArray.fromObject(bankJson);
		for(int i = 0; i < ja.size(); i++) {
			Bank bank = (Bank) JSONObject.toBean(ja.getJSONObject(i), Bank.class);
			banks.add(bank);
		}
		return banks;
	} 
	
	// task3
	public ArrayList<BankAccount> ParseBankAccountJson(String bankAccountJson) {
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		JSONArray ja = JSONArray.fromObject(bankAccountJson);
		for(int i = 0; i < ja.size(); i++) {
			BankAccount bankAccount = (BankAccount) JSONObject.toBean(ja.getJSONObject(i), BankAccount.class);
			bankAccounts.add(bankAccount);
		}
		return bankAccounts;
	}
	
	// task3
	public ArrayList<Payment> ParsePaymentJson(String paymentJson) {
		ArrayList<Payment> payments = new ArrayList<Payment>();
		JSONArray ja = JSONArray.fromObject(paymentJson);
		for(int i = 0; i < ja.size(); i++) {
			Payment payment = (Payment) JSONObject.toBean(ja.getJSONObject(i), Payment.class);
			payments.add(payment);
		}
		return payments;
	}
	
	// task4
	public Map<String, Quote> ParseQuoteJson(String companyJson, String quoteJson) {
		Map<String, Quote> quotes = new HashMap<String, Quote>();
		JSONObject companysJO = JSONObject.fromObject(companyJson);
		
		// get companys' names
		String companysString = companysJO.getString("companies");
		String[] companys = companysString.replace("[", "").replace("]", "").split(", ");
		
		// get quotes
		JSONObject quotesJO = JSONObject.fromObject(quoteJson);
		for (String string : companys) {
			JSONObject company = quotesJO.getJSONObject(string);
			Quote quote = (Quote) JSONObject.toBean(company, Quote.class);
			quotes.put(string, quote);
		}
		return quotes;
	}
	
	// task4
	public ArrayList<String> ParseCurrencyJson(String currencyJson) {
		JSONArray currencyArr = JSONArray.fromObject(currencyJson);
		ArrayList<String> currencies = new ArrayList<String>();
		for(int i = 0; i < currencyArr.size(); i++) {
			currencies.add(currencyArr.getString(i));
		}
		return currencies;
	}
	
	// task4
	public double ParseMidMarketRate(String rateJson) {
		double midMarketRate = 0.0;
		JSONObject jo = JSONObject.fromObject(rateJson);
		midMarketRate = jo.getDouble("rate");
		return midMarketRate;
	}
	
	// task5
	public ArrayList<Task5Payment> ParseTask5PaymentJson(String paymentJson) {
		ArrayList<Task5Payment> paymentList = new ArrayList<Task5Payment>();
		JSONArray ja = JSONArray.fromObject(paymentJson);
		for(int i = 0; i < ja.size(); i++) {
			Task5Payment payment = (Task5Payment) JSONObject.toBean(ja.getJSONObject(i), Task5Payment.class);
			paymentList.add(payment);
		}
		return paymentList;
	}
	
	// task6
	public ArrayList<Task6Payment> ParseTask6PaymentJson(String paymentJson) {
		ArrayList<Task6Payment> paymentList = new ArrayList<Task6Payment>();
		JSONArray ja = JSONArray.fromObject(paymentJson);
		for(int i = 0; i < ja.size(); i++) {
			Task6Payment payment = (Task6Payment) JSONObject.toBean(ja.getJSONObject(i), Task6Payment.class);
			paymentList.add(payment);
		}
		return paymentList;
	}
	
	// task6
	public ArrayList<PaymentHistory> ParsePaymentHistoryJson(String paymentHistoryJson) {
		ArrayList<PaymentHistory> paymentHistoryList = new ArrayList<PaymentHistory>();
		JSONArray ja = JSONArray.fromObject(paymentHistoryJson);
		for(int i = 0; i < ja.size(); i++) {
			PaymentHistory paymentHistory = (PaymentHistory) JSONObject.toBean(ja.getJSONObject(i), PaymentHistory.class);
			paymentHistoryList.add(paymentHistory);
		}
		return paymentHistoryList;
	}
}
