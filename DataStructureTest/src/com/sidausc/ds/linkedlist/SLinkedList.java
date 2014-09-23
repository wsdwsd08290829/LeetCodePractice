package com.sidausc.ds.linkedlist;

public class SLinkedList {
	private Node head;
	private Node tail;
	private int size;
	public Node getHead() {
		return head;
	}
	public void setHead(Node head) {
		this.head = head;
	}
	public Node getTail() {
		return tail;
	}
	public void setTail(Node tail) {
		this.tail = tail;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public SLinkedList(){
		
	}
	public SLinkedList(Node n){
		head = n;
		tail = n;
		size = 1;
	}
	public void addFirst(Node n){
		if(size == 0){
			head = tail = n;
		}else{
			n.setNext(head);
			head = n;
		}
		size++;
	}
	public void addLast(Node n){
		if(tail == null){head = tail = n;}
		else{
			tail.setNext(n);;
			tail = n;
			n.setNext(null);;
		}
		size++;
	}
	//insert n after v
	public void insertAfter(Node n, Node v){
		Node temp = v.getNext();
		v.setNext(n);
		n.setNext(temp);
		size++;
	}
	public void removeFirst(){
		if(size == 0)return;
		else if(size==1){head=tail = null; size=0;}
		else{
			head = head.getNext();
			size--;
		}
	}
	public String toString(){
		String sb = "";
		Node temp = null;
		if(size == 0)sb+="list is empty";
		else{
			sb += (String)head.getElement();
			temp = head;
			for(int i = 1; i<size; i++){
				temp = temp.getNext();
				sb+= (String)temp.getElement();
			}
		}
		return sb.toString();
	}
	public static void main(String[] args){
		SLinkedList sl = new SLinkedList();
		Node n1 = new Node("str1");
		Node n2 = new Node("str2");
		Node n3 = new Node("str3");
		sl.addFirst(n1);
		sl.addLast(n3);
		sl.insertAfter(n2, n1);
		System.out.println(sl);
		System.out.println(sl.getSize());
		sl.removeFirst();
		System.out.println(sl);
		System.out.println(sl.getSize());
	}
}
