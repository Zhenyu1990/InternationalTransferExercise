package com.solutions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.beans.PaymentHistory;
import com.beans.Task6Payment;
import com.util.HttpRequest;
import com.util.JsonParser;

public class TaskSix {

	private static final String TOKEN = "token=f47ed87bc2d0d09874819d368c5156c3f1f6cd99";

	public static void main(String[] args) {
		ArrayList<PaymentHistory> paymentHistoryList = getPaymentHistoryList();
		ArrayList<Task6Payment> paymentList = getPaymentList();
		ArrayList<Task6Payment> fraudPaymentList = new ArrayList<Task6Payment>();
		String ip = "";
		
		// show payment history and get ip from them
		for (PaymentHistory paymentHistory : paymentHistoryList) {
			if(paymentHistory.isFraud()) {
				ip = paymentHistory.getIp();
			}
		}
		
		// the pattern is all fraud payments using the same network segment
		String[] ipArr = ip.split("\\.");
		String pattern = ipArr[0] + "\\." + ipArr[1] + "\\." + ipArr[2] + "\\..*";
		Pattern p = Pattern.compile(pattern);

		for (Task6Payment payment : paymentList) {
			Matcher m = p.matcher(payment.getIp());
			if (m.matches()) {
				fraudPaymentList.add(payment);
			}
		}
		paymentList.removeAll(fraudPaymentList);

		ArrayList<String> fraudUrlList = getUrl(fraudPaymentList);
		ArrayList<String> goodUrlList = getUrl(paymentList);
		System.out.println("---------------------fraudUrlList-------------------------");
		for (String url : fraudUrlList) {
			String cont = HttpRequest.sendPut(url, TOKEN);
			System.out.println(cont);
		}
		System.out.println("---------------------goodUrlList-------------------------");
		for (String url : goodUrlList) {
			String cont = HttpRequest.sendDelete(url, TOKEN);
			System.out.println(cont);
		}

		 
	}

	public static ArrayList<Task6Payment> getPaymentList() {
		String url = "http://bootcamp-api.transferwise.com/payment";
		String cont = HttpRequest.sendGet(url, TOKEN);
		JsonParser jp = new JsonParser();
		ArrayList<Task6Payment> paymentList = jp.ParseTask6PaymentJson(cont);
		return paymentList;
	}

	public static ArrayList<PaymentHistory> getPaymentHistoryList() {
		String url = "http://bootcamp-api.transferwise.com/payment/history";
		String cont = HttpRequest.sendGet(url, TOKEN);
		JsonParser jp = new JsonParser();
		ArrayList<PaymentHistory> paymentHistoryList = jp.ParsePaymentHistoryJson(cont);
		return paymentHistoryList;
	}

	public static ArrayList<String> getUrl(ArrayList<Task6Payment> paymentList) {
		ArrayList<String> urlList = new ArrayList<String>();
		for (Task6Payment payment : paymentList) {
			String url = "http://bootcamp-api.transferwise.com/payment/" + payment.getId() + "/fraud";
			urlList.add(url);
		}
		return urlList;
	}

}
