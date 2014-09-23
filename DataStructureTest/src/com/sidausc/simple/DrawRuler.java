package com.sidausc.simple;

public class DrawRuler {
	public static void drawRuler(int length, int tick){
		
		for(int i=0; i< length; i++){
			drawRealTicksWithNumber(tick, i);
			if(tick>1){
				drawRecurTicks(tick-1);
			}
		}
		drawRealTicksWithNumber(tick, length);
	}
	public static void drawRecurTicks(int tick){
		if(tick>1){
			drawRecurTicks(tick-1);
			drawRealTicks(tick);
			drawRecurTicks(tick-1);
		}else{
			System.out.println("-");
		}
	}
	public static void drawRealTicks(int tick){
		for(int i=0;i<tick-1;i++){
			System.out.print("-");
		}
		System.out.println("-");
	}
	public static void drawRealTicksWithNumber(int tick, int num){
		for(int i=0;i<tick-1;i++){
			System.out.print("-");
		}
		System.out.println("- "+ num);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		drawRuler(2,4);
	}

}
