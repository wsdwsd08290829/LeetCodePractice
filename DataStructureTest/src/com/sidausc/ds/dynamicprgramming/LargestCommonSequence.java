package com.sidausc.ds.dynamicprgramming;

public class LargestCommonSequence {
	public static int lcs(String x, String y){
		int m = y.length(), n = x.length();
		int[][] l = new int[n+1][m+1];
		for(int i=-1; i<n; i++){
			l[i+1][-1+1] = 0;   //  L(x, -1) = 0;
		}
		for(int i=-1; i<m; i++){
			l[-1+1][i+1] = 0;
		}
		for(int i=0; i<n; i++){
			for(int j=0;j<m;j++){
				if(x.charAt(i)==y.charAt(j))
					l[i+1][j+1] = l[i][j]+1;
				else
					l[i+1][j+1] =Math.max(l[i][j+1], l[i+1][j]);
			}
		}
		for(int j=0;j<m+1;j++){
			for(int i=0; i<n+1; i++){
				System.out.print(l[i][j]);
			}
			System.out.println();
		}
		return l[n][m];
	}
	public static void main(String[] args) {
		String x = "CGATAATTGAGA";
		String y = "GTTCCTAATA";  //ans: 6
		
		System.out.println(lcs(x, y));
	}
}
