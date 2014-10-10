
public final class Constanct {
	public static void call(){
		System.out.println("abcd");
	}
	void callit(){
		System.out.println("abcd");
	}
	public static void main(String[] args) {
		call();
		new Constanct().callit();
	}
}
