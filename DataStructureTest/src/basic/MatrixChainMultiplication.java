package basic;

public class MatrixChainMultiplication {
	static int MatrixChainOrder(int p[], int i, int j)
	{
	    if(i == j)
	        return 0;
	    int k;
	    int min = Integer.MAX_VALUE;
	    int count;
	 
	    // place parenthesis at different places between first and last matrix,
	    // recursively calculate count of multiplcations for each parenthesis 
	    // placement and return the minimum count
	    for (k = i; k <j; k++)
	    {
	        count = MatrixChainOrder(p, i, k) +
	                MatrixChainOrder(p, k+1, j) +
	                p[i-1]*p[k]*p[j];
	 
	        if (count < min)
	            min = count;
	    }
	 
	    // Return minimum count
	    return min;
	}
	public static void main(String[] args) {
		//int arr[] = {1, 2, 3, 4, 3};
		int [] arr = {10,1, 2,1,20};
		System.out.println(MatrixChainOrder(arr, 1, 4));
	}
}
