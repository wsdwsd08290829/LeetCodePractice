package basic;

import com.sidausc.helper.Helper;

public class NumOfDistinctSubsequence {
	public static int numDistincts(String S, String T) {
		int[][] table = new int[S.length() + 1][T.length() + 1];
	 
		for (int i = 0; i < S.length(); i++)
			table[i][0] = 1;
	 
		for (int i = 1; i <= S.length(); i++) {
			for (int j = 1; j <= T.length(); j++) {
				if (S.charAt(i - 1) == T.charAt(j - 1)) {
					table[i][j] += table[i - 1][j] + table[i - 1][j - 1];
				} else {
					table[i][j] += table[i - 1][j];
				}
			}
		}
		Helper.printMatrix(table);
		return table[S.length()][T.length()];
	}
	public static void main(String[] args) {
		System.out.println(numDistincts("aaa", "aa"));
		System.out.println(numberOfSubsequence("aaa", "aa"));
	}
	public static int numberOfSubsequence(String s, String t){
		if(t.length()>s.length())return 0;
		int m = s.length(), n = t.length();
		int[][] table = new int[m+1][n+1];
		//init table
		for(int i=0;i<m;i++)table[i][0] = 1;
		//dynamic prgramming
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
				//if(j>i){table[i][j]=0;break;}
				//if last equal
				if(s.charAt(i-1)==t.charAt(j-1)){
					table[i][j] = table[i-1][j-1] + table[i-1][j]; 
				}else{
					table[i][j] = table[i-1][j];
				}
			}
		}
		Helper.printMatrix(table);
		return table[m][n];
	}
}
