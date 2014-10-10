package com.sidausc.greedy;
public class ActivitySelection {
	public static void main(String[] args) {
		int[] start = { 1, 3, 0, 5, 8, 5 };
		int[] finish = { 2, 4, 6, 7, 9, 9 };
		printMaxActivities(start, finish);
	}
	private static void printMaxActivities(int[] start, int[] finish) {
		System.out.println("0 ");
		int lastFinish = finish[0]; //finish time of previous chosen activity
		for (int i = 1; i < finish.length; i++) {
			if (start[i] >= lastFinish) {
				System.out.println(i);
				lastFinish = finish[i];
			}
		}
	}

}
