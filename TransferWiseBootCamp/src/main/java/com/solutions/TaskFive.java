package com.solutions;

import java.util.ArrayList;

import com.beans.Task5Payment;
import com.util.HttpRequest;
import com.util.JsonParser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * The file encoding should be utf-8
 * 
 * @author Zhenyu Wu
 *
 */
public class TaskFive {
	private static final String TOKEN = "token=f47ed87bc2d0d09874819d368c5156c3f1f6cd99";

	public static void main(String[] args) {
		ArrayList<String[]> pepList = getPepList();
		ArrayList<Task5Payment> paymentList = getPaymentList();
		ArrayList<Task5Payment> pepPaymentList = new ArrayList<Task5Payment>();
		
		for (String[] peps : pepList) {
			for(Task5Payment payment : paymentList) {
				if(payment.getRecipientName().equals((peps)[0]) && payment.getRecipientCountry().equals(peps[1])) {
					pepPaymentList.add(payment);
				}
			}
		}
		paymentList.removeAll(pepPaymentList);
		
		ArrayList<String> pepUrlList = getUrl(pepPaymentList);
		ArrayList<String> nonPepUrlList = getUrl(paymentList);
		System.out.println("------------------Peps:-------------------");
		for (String pepUrl : pepUrlList) {
			String cont = HttpRequest.sendPut(pepUrl, TOKEN);
			System.out.println(cont);
		}
		System.out.println("------------------Non-Peps:-------------------");
		for (String nonPepUrl : nonPepUrlList) {
			String cont = HttpRequest.sendDelete(nonPepUrl, TOKEN);
			System.out.println(cont);
		}
		
	}
	
	public static ArrayList<Task5Payment> getPaymentList() {
		String url = "http://bootcamp-api.transferwise.com/payment";
		String cont = HttpRequest.sendGet(url, TOKEN);
		JsonParser jp = new JsonParser();
		ArrayList<Task5Payment> paymentList = jp.ParseTask5PaymentJson(cont);
		return paymentList;
	}
	
	public static ArrayList<String[]> getPepList() {
		String url = "http://bootcamp-api.transferwise.com/task/5";
		ArrayList<String[]> pepList = new ArrayList<String[]>();
		String cont = HttpRequest.sendGet(url, TOKEN);
		JSONObject jo = JSONObject.fromObject(cont);
		JSONArray pepJArr = jo.getJSONArray("peps");
		for(int i = 0; i < pepJArr.size(); i++) {
			String[] pepArr = pepJArr.getString(i).split(" - ");
			pepList.add(pepArr);
		}
		return pepList;
	}
	
	// generate url for payment
	public static ArrayList<String> getUrl(ArrayList<Task5Payment> paymentList) {
		ArrayList<String> urlList = new ArrayList<String>();
		for (Task5Payment payment : paymentList) {
			String url = "http://bootcamp-api.transferwise.com/payment/" + payment.getId() + "/aml";
			urlList.add(url);
		}
		return urlList;
	}
}
