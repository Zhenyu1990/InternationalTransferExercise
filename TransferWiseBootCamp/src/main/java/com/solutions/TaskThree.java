package com.solutions;

import java.util.ArrayList;

import com.beans.Bank;
import com.beans.BankAccount;
import com.beans.Payment;
import com.util.HttpRequest;
import com.util.JsonParser;

public class TaskThree {

	public static void main(String[] args) {
		String param = "token=f47ed87bc2d0d09874819d368c5156c3f1f6cd99";
		TaskThree t = new TaskThree();
		ArrayList<String> transferUrls = t.getTransferUrls();
		for (String string : transferUrls) {
			String cont = HttpRequest.sendPost(string, param);
			System.out.println(cont);
		}

	}

	/**
	 * Once we start the task, use this method to get information(bank, bank
	 * account and payment) and use them to generate the transfer string
	 * 
	 * @return this method will return an ArrayList<String> which contains
	 *         transfer strings, each string corresponds to one payment
	 */
	public ArrayList<String> getTransferUrls() {
		// get information from server
		String url = "http://bootcamp-api.transferwise.com/";
		String param = "token=f47ed87bc2d0d09874819d368c5156c3f1f6cd99";
		String getBank = url + "bank";
		String getBankAccount = url + "bankAccount";
		String getPayment = url + "payment";
		String bankJson = HttpRequest.sendGet(getBank, param);
		String bankAccountJson = HttpRequest.sendGet(getBankAccount, param);
		String paymentJson = HttpRequest.sendGet(getPayment, param);

		ArrayList<String> transferUrls = new ArrayList<String>();

		// parse json string to java beans
		JsonParser jp = new JsonParser();
		// get bank
		ArrayList<Bank> banks = jp.ParseBankJson(bankJson);
		// get bank account
		ArrayList<BankAccount> bankAccounts = jp.ParseBankAccountJson(bankAccountJson);
		// get payment
		ArrayList<Payment> payments = jp.ParsePaymentJson(paymentJson);

		// group TransferWise bank account and customer bank account
		ArrayList<BankAccount> transferwiseAccount = new ArrayList<BankAccount>();
		ArrayList<BankAccount> customerAccount = new ArrayList<BankAccount>();
		for (int i = 0; i < bankAccounts.size(); i++) {
			if (bankAccounts.get(i).getAccountName().equals("TransferWise Ltd")) {
				transferwiseAccount.add(bankAccounts.get(i));
			} else {
				customerAccount.add(bankAccounts.get(i));
			}
		}

		String transferString = "";

		// grab information from java beans and generate the transfer string
		for (int i = 0; i < payments.size(); i++) {

			String bankName = "";
			String bankId = "";
			String sourceAccountNumber = "";
			String targetBankName = "";
			// double sourceBalance = 0.0;

			// get information from payment and customer account
			String targetAccountNumber = customerAccount.get(i).getAccountNumber();
			// double targetBalance = customerAccount.get(i).getBalance();
			double amount = payments.get(i).getAmount();
			String targetCurrency = payments.get(i).getTargetCurrency();
			String recipientBankId = payments.get(i).getRecipientBankId();

			// find the corresponding TransferWise account and get information
			for (int j = 0; j < transferwiseAccount.size(); j++) {
				if (transferwiseAccount.get(j).getCurrency().equals(targetCurrency)) {
					sourceAccountNumber = transferwiseAccount.get(j).getAccountNumber();
					// sourceBalance = transferwiseAccount.get(j).getBalance();
					bankId = transferwiseAccount.get(j).getBankId();
					break;
				}
			}

			// get bank name according to bank id
			for (int j = 0; j < banks.size(); j++) {
				if (banks.get(j).getId().equals(recipientBankId)) {
					targetBankName = banks.get(j).getName();
				}
				if (banks.get(j).getId().equals(bankId)) {
					bankName = banks.get(j).getName();
				}
			}

			// generate transfer string
			String transfer = "bank/" + bankName + "/transfer/" + sourceAccountNumber + "/" + targetBankName + "/"
					+ targetAccountNumber + "/" + amount;

			// format transfer string so that we can post them correctly
			transferString = transfer.replace(".", "%2E");
			transferString = transferString.replace(" ", "%20");

			// generate transfer url which we're going to post
			String transferUrl = url + transferString;
			transferUrls.add(transferUrl);
		}

		return transferUrls;
	}
}
