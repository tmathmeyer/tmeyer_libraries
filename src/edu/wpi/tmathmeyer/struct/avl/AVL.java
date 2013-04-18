package edu.wpi.tmathmeyer.struct.avl;

public interface AVL<E extends Comparable<E>>{
	public E getNode();
	public AVL<E> add(E i);
	public AVL<E> remove(E i);
	public boolean isEmpty();
	public AVL<E> getLeft();
	public AVL<E> getRight();
	public boolean areChildrenEmpty();
	public int depth();
	public int getBalance();
	public AVL<E> balance();
	public void print(int depth);
}
