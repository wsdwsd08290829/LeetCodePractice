package basic;

public class ReverseNumber {
	public static int reverseNumber(int num){
		int digitCount = 0;
		int temp = num;
		int result = 0;
		while(temp!=0){ //12 1
			temp /=10;
			digitCount++;
		}
		temp = num;
		while(digitCount>0){
			result = result*10 + temp%10;
			temp /= 10;
			digitCount--;
		}
		return result;
	}
	public static void main(String[] args) {
		int num = 12345;
		System.out.println(reverseNumber(num));
	}
}
