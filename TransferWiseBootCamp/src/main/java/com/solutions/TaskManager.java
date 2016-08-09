package com.solutions;

import com.util.HttpRequest;
/**
 * This class is used to manage task, including get task, start task and finish
 * task. Since task1 and task2 is very simple, I send the answer in this class
 * directly(I know it's not good, but it's just for saving time, so forgive me)
 * task3 - task6 will finished automatically.
 * @author Zhenyu Wu
 *
 */
public class TaskManager {
	// my token
	private static final String TOKEN = "token=f47ed87bc2d0d09874819d368c5156c3f1f6cd99";

	public static void main(String[] args) {
		// task1
		// String task1Url = "http://bootcamp-api.transferwise.com/name/Wu%20Zhenyu";
		// task2
		// String task2Url = "http://bootcamp-api.transferwise.com/survivor/31";
		// String cont = HttpRequest.sendPost(task2Url, TOKEN);
		// System.out.println(cont);

		TaskManager tm = new TaskManager();
		tm.getTask(1);
//		tm.startTask(6);
//		tm.finishTask();

	}

	public void getTask(int id) {
		String url = "http://bootcamp-api.transferwise.com/task/" + id;
		String cont = HttpRequest.sendGet(url, TOKEN);
		System.out.println(cont);
	}

	public void startTask(int id) {
		String url = "http://bootcamp-api.transferwise.com/task/" + id + "/start";
		String cont = HttpRequest.sendPost(url, TOKEN);
		System.out.println(cont);
	}

	public void finishTask() {
		String url = "http://bootcamp-api.transferwise.com/task/finish";
		String cont = HttpRequest.sendPost(url, TOKEN);
		System.out.println(cont);
	}

}
