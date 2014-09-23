package com.sidausc.ds.priorityqueue;

import com.sidausc.ds.listanditerators.Position;
import com.sidausc.ds.tree.BinaryTree;

public interface CompleteBinaryTree<E> extends BinaryTree<E> {
	  public Position<E> add(E elem);
	  public E remove();
}	