package com.sidausc.ds.queue;

import com.sidausc.ds.linkedlist.Node;

public class NodeQueue<E> implements Queue<E> {
	protected int size = 0;
	Node head;
	Node tail;
	 public void enqueue(E elem) {
		    Node<E> node = new Node<E>();
		    node.setElement(elem);
		    node.setNext(null); // node will be new tail node
		    if (size == 0)
		      head = node; // special case of a previously empty queue
		    else
		      tail.setNext(node); // add node at the tail of the list
		    tail = node; // update the reference to the tail node
		    size++;
	 }
	public E dequeue() throws EmptyQueueException {
		    if (size == 0)
		      throw new EmptyQueueException("Queue is empty.");
		    E tmp = (E) head.getElement();
		    head = head.getNext();
		    size--;
		    if (size == 0)
		      tail = null; // the queue is now empty
		    return tmp;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size==0)return true;
		else return false;
	}
	@Override
	public E front() throws EmptyQueueException {
		// TODO Auto-generated method stub
		return (E) head;
	
	}
}
