package com.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.beans.Quote;
import com.util.HttpRequest;
import com.util.JsonParser;

/**
 * This is only for testing my ideas
 * 
 * @author AbuserBIG
 *
 */
public class MainTest {

	public static void main(String[] args) {
//		String companys = "http://bootcamp-api.transferwise.com/company";
//		String url = "http://bootcamp-api.transferwise.com/hiddenFee/forCompany/OnlineExchange2/100%2E0/EUR/INR/3%2E0";
//		String url = "http://bootcamp-api.transferwise.com/hiddenFee/forCompany/HighStreetBank2/100%2E0/EUR/INR/2%2E0";
//		String url = "http://bootcamp-api.transferwise.com/hiddenFee/forCompany/HighStreetBank1/100%2E0/EUR/INR/4%2E0";
//		String url = "http://bootcamp-api.transferwise.com/hiddenFee/forCompany/OnlineExchange1/100%2E0/EUR/INR/4%2E0";
//		String url = "http://bootcamp-api.transferwise.com/hiddenFee/forCompany/HighStreetBank3/100%2E0/EUR/INR/7%2E0";
//		String url = "http://bootcamp-api.transferwise.com/hiddenFee/forCompany/CornerStore2/100%2E0/EUR/INR/2%2E0";
//		String url = "http://bootcamp-api.transferwise.com/hiddenFee/forCompany/CornerStore1/100%2E0/EUR/INR/10%2E0"; //?
//		String url = "http://bootcamp-api.transferwise.com/task/5/start";
		String url = "http://bootcamp-api.transferwise.com/task/finish";
//		String quote = "http://bootcamp-api.transferwise.com/quote/100/EUR/INR";
		String param = "token=f47ed87bc2d0d09874819d368c5156c3f1f6cd99";
		String cont, cont1, cont2;
//		cont1 = HttpRequest.sendGet(companys, param);
//		cont2 = HttpRequest.sendGet(quote, param);
//		cont = HttpRequest.sendGet(url, param);
		cont = HttpRequest.sendPost(url, param);
//		System.out.println(cont1);
//		System.out.println(cont2);
		System.out.println(cont);
		
//		String delUrl = "http://bootcamp-api.transferwise.com/payment/57309909e4b089259cf84c05/aml";
//		String cont3 = HttpRequest.sendDelete(delUrl, param);
//		System.out.println(cont3);
//		String PutUrl = "http://bootcamp-api.transferwise.com/payment/5730990ae4b089259cf84c9d/aml";
//		String cont4 = HttpRequest.sendDelete(PutUrl, param);
//		System.out.println(cont4);

	}

}
