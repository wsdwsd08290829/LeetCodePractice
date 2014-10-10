
public class Power {
	public static double power(int x, int n){
		if(x == 0)return 0;
		if(n==0) return 1;
		if(n%2 == 0)return power(x, n/2)* power(x, n/2);
		else return	power(x, n/2)* power(x, n/2)*x;
	}
	public static double pow(int x, int n){
		if(n>=0)return power(x, n);
		else return 1/power(x, n);
	}
	public static void main(String[] args) {
		System.out.println(pow(2,3));
		System.out.println(pow(2,-2 ));
	}
}
