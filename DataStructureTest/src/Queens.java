public  class Queens {
	static boolean flag;
	static boolean getqueens(int col, int[] result){ //input columen		
		for(int i=0;i<4;i++){  //for each row of that columns
			if(check(result, col, i)){
				result[col] = i;
				if(col==3){
					flag = true;
					System.out.println("???");
					printArr(result);
					//	return true;
				}
				getqueens(col+1, result);
			}
		}
		return flag;
	}
	static boolean check(int[] result, int col, int row){
		for(int i=0;i<col;i++){
			if(result[i] == row){
			//	print(result);
				return false;
			}
		} //in same row
		for(int i=0;i<col;i++){
			if(result[i]-row == (col-i) || result[i]-row == (i-col)){
				return false;
			}
		}
		//print(result);
		return true;
	}
	static void printArr(int[] result){
		for(int i :result){
			System.out.print(i+ " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		
		int[] result = new int[4];
		for(int i=0;i<result.length;i++){
			result[i] = -1;
		}
		System.out.println(getqueens(0, result));
	}
}
