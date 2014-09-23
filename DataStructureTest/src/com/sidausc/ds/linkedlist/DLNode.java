package com.sidausc.ds.linkedlist;

public class DLNode<E> {
	protected E element;
	protected DLNode<E> prev, next;
	public DLNode(){
		
	}
	public DLNode(E e, DLNode<E> p, DLNode<E> n){
		element = e;
		prev = p;
		next = n;	
	}
	
	public E getElement() {
		return element;
	}
	public void setElement(E element) {
		this.element = element;
	}
	public DLNode<E> getPrev() {
		return prev;
	}
	public void setPrev(DLNode<E> prev) {
		this.prev = prev;
	}
	public DLNode<E> getNext() {
		return next;
	}
	public void setNext(DLNode<E> next) {
		this.next = next;
	}
	
	
}
