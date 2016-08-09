package com.solutions;

import java.util.ArrayList;

public class TaskTwo {

	public static void main(String[] args) {
		TaskTwo t = new TaskTwo();
		int surviver = t.selectNumber(100);
		System.out.println("The surviver is: " + surviver);
	}
	
	public int selectNumber(int n) {
		
		int result = -1;
		ArrayList<Integer> nums = new ArrayList<Integer>();
		int index = 0;	 // this variable is used to remove numbers from list 
		int count = 0;	 // count is used to count which numbers should be remove
		int pIndex = 0;  // this is used to record previous index
		
		// generate a list contains numbers from 1 to n
		for(int i = 1; i <= n; i++) {
			nums.add(i);
		}
		
		while(nums.size() > 1) {
			
			while(index < nums.size()) {
				nums.remove(index); 	
				count++;
				pIndex = index;
				index += count;
			}
			
			index = count - (nums.size() - pIndex);
			
			while(index > nums.size() - 1) {
				index = index - nums.size();
			}
		}
		
		result = nums.get(0);
		return result;
	}
	
}
