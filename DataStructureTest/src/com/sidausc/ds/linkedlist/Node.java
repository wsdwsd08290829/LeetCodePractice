package com.sidausc.ds.linkedlist;

public class Node<T> {
	private T element;
	private Node next;
	public Node(T elem){
		element = elem;
	}
	public Node(){
	}
	public Node(T elem, Node n){
		element = elem;
		next = n;
	}
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
}
