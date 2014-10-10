package basic.list;

public class ListNode {
	public ListNode next;
	public int val;
	public ListNode(ListNode next, int val) {
		//super();
		this.next = next;
		this.val = val;
	}
	public ListNode(int val) {
		//super();
		this.val = val;
	}
	@Override
	public String toString() {
		String s = "";
		ListNode temp = this;
		while(temp != null){
			s+= String.valueOf(temp.val+ " ");
		//	System.out.println(temp.val);
			temp = temp.next;
			
		}
		return s;
	}
}
