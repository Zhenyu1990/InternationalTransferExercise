package com.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.beans.Quote;
import com.util.FormatString;
import com.util.HttpRequest;
import com.util.JsonParser;

public class TaskFour {

	private static final String TOKEN = "token=f47ed87bc2d0d09874819d368c5156c3f1f6cd99";

	public static void main(String[] args) {
		// get currency - Currency: EUR USD GBP INR MXN
		ArrayList<String> currencies = TaskFour.getCurrency();
		System.out.print("Currency: ");
		for (int i = 0; i < currencies.size(); i++) {
			System.out.print(currencies.get(i) + " ");
		}
		System.out.println();
		System.out.println();

		/*
		 * in this program 336 out 420 results are always right( which I marked
		 * "OK" below) and 84 out 420 results(which I marked
		 * "sometimes is wrong" below) sometimes get wrong and sometimes get
		 * right) I am trying to figure out where the problem is
		 */
		TaskFour.doPostHiddenFee("EUR", "USD"); // OK
		TaskFour.doPostHiddenFee("EUR", "GBP"); // OK
		TaskFour.doPostHiddenFee("EUR", "INR"); // sometimes is wrong
		TaskFour.doPostHiddenFee("EUR", "MXN"); // OK
		TaskFour.doPostHiddenFee("USD", "EUR"); // OK
		TaskFour.doPostHiddenFee("GBP", "EUR"); // OK
		TaskFour.doPostHiddenFee("INR", "EUR"); // sometimes is wrong
		TaskFour.doPostHiddenFee("MXN", "EUR"); // OK
		TaskFour.doPostHiddenFee("USD", "GBP"); // OK
		TaskFour.doPostHiddenFee("USD", "INR"); // sometimes is wrong
		TaskFour.doPostHiddenFee("USD", "MXN"); // OK
		TaskFour.doPostHiddenFee("GBP", "USD"); // OK
		TaskFour.doPostHiddenFee("INR", "USD"); // sometimes is wrong
		TaskFour.doPostHiddenFee("MXN", "USD"); // OK
		TaskFour.doPostHiddenFee("GBP", "INR"); // OK
		TaskFour.doPostHiddenFee("GBP", "MXN"); // OK
		TaskFour.doPostHiddenFee("INR", "GBP"); // OK
		TaskFour.doPostHiddenFee("MXN", "GBP"); // OK
		TaskFour.doPostHiddenFee("INR", "MXN"); // OK
		TaskFour.doPostHiddenFee("MXN", "INR"); // OK
	}

	// post the Hidden Fee with the amounts of 100, 1000, and 10000
	public static void doPostHiddenFee(String sourceCurrency, String targetCurrency) {
		System.out.println(sourceCurrency + " --> " + targetCurrency);
		Map<String, Quote> quotes1 = TaskFour.getCompanyQuotes(100, sourceCurrency, targetCurrency);
		Map<String, Quote> quotes2 = TaskFour.getCompanyQuotes(1000, sourceCurrency, targetCurrency);
		Map<String, Quote> quotes3 = TaskFour.getCompanyQuotes(10000, sourceCurrency, targetCurrency);
		double midMarketRate = TaskFour.getRate(sourceCurrency, targetCurrency);
		// calculate hidden fee and generate hidden fee list
		ArrayList<String> hiddenFeeList1 = TaskFour.gethiddenFeeStringList(quotes1, midMarketRate);
		ArrayList<String> hiddenFeeList2 = TaskFour.gethiddenFeeStringList(quotes2, midMarketRate);
		ArrayList<String> hiddenFeeList3 = TaskFour.gethiddenFeeStringList(quotes3, midMarketRate);
		// post hidden fee
		for (int i = 0; i < hiddenFeeList1.size(); i++) {
			String cont = HttpRequest.sendPost(hiddenFeeList1.get(i), TOKEN);
			System.out.println(cont);
			// System.out.println(hiddenFeeList1.get(i)); // test
		}
		for (int i = 0; i < hiddenFeeList2.size(); i++) {
			String cont = HttpRequest.sendPost(hiddenFeeList2.get(i), TOKEN);
			System.out.println(cont);
		}
		for (int i = 0; i < hiddenFeeList3.size(); i++) {
			String cont = HttpRequest.sendPost(hiddenFeeList3.get(i), TOKEN);
			System.out.println(cont);
		}

	}

	// get currency
	public static ArrayList<String> getCurrency() {
		String currency = "http://bootcamp-api.transferwise.com/currency";
		String currencyJson = HttpRequest.sendGet(currency, TOKEN);
		JsonParser jp = new JsonParser();
		ArrayList<String> currencies = jp.ParseCurrencyJson(currencyJson);
		return currencies;
	}

	// get company and quote
	public static Map<String, Quote> getCompanyQuotes(double amount, String sourceCurrency, String targetCurrency) {
		Map<String, Quote> companyQuotes = new HashMap<String, Quote>();
		String company = "http://bootcamp-api.transferwise.com/company";
		String amountString = (amount + "").replace(".", "%2E");
		String quotes = "http://bootcamp-api.transferwise.com/quote/" + amountString + "/" + sourceCurrency + "/"
				+ targetCurrency;
		String companyJson = HttpRequest.sendGet(company, TOKEN);
		String quoteJson = HttpRequest.sendGet(quotes, TOKEN);
		// System.out.println(quoteJson); //test
		JsonParser jp = new JsonParser();
		companyQuotes = jp.ParseQuoteJson(companyJson, quoteJson);
		return companyQuotes;
	}

	// get real mid-market rate
	public static double getRate(String sourceCurrency, String targetCurrency) {
		String midMarketRate = "http://bootcamp-api.transferwise.com/rate/midMarket/" + sourceCurrency + "/"
				+ targetCurrency;
		String rateJson = HttpRequest.sendGet(midMarketRate, TOKEN);
		JsonParser jp = new JsonParser();
		double rate = jp.ParseMidMarketRate(rateJson);
		// System.out.println("Mid-market Rate " + sourceCurrency + " --> " +
		// targetCurrency + ": " + rate); // test
		return rate;
	}

	// calculate hidden fee percentage and return a string which can be used to
	// post
	public static ArrayList<String> gethiddenFeeStringList(Map<String, Quote> quotes, double midMarketRate) {
		ArrayList<String> hiddenFeeStringList = new ArrayList<String>();

		Iterator<Entry<String, Quote>> it = quotes.entrySet().iterator();
		while (it.hasNext()) {
			StringBuilder hiddenFeeSB = new StringBuilder();
			Map.Entry<String, Quote> entry = (Entry<String, Quote>) it.next();
			hiddenFeeSB.append(entry.getKey() + "/");
			Quote quote = entry.getValue();
			/*
			 * calculate hidden fee percentage: my expression is (midMarketRate
			 * - offeredRate) / midMarketRate * 100
			 */
			hiddenFeeSB.append(quote.getSourceAmount() + "/").append(quote.getSourceCurrency() + "/")
					.append(quote.getTargetCurrency() + "/");
			double hiddenFeePercentage = (midMarketRate - quote.getOfferRate()) / midMarketRate * 100;
			// System.out.println(hiddenFeePercentage + 0.0001); // test
			// hiddenFeeSB.append(Math.floor(hiddenFeePercentage) + "");
			hiddenFeeSB.append(Math.floor(hiddenFeePercentage + 0.0001) + "");
			String hiddenFeeString = "http://bootcamp-api.transferwise.com/hiddenFee/forCompany/"
					+ FormatString.format(hiddenFeeSB.toString());
			// System.out.println(hiddenFeeString); // test
			hiddenFeeStringList.add(hiddenFeeString);
		}
		return hiddenFeeStringList;
	}
}
