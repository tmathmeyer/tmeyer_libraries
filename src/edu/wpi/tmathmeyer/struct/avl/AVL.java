package edu.wpi.tmathmeyer.struct.avl;

public interface AVL<E extends Comparable<E>>{
	/**
	 * 
	 * @return the top level node
	 */
	public E getNode();
	
	/**
	 * 
	 * @return whether the tree has the element in ANY branch
	 */
	public boolean hasElement(E e);
	
	/**
	 * 
	 * @param i the node to be added
	 * @return the AVL tree with the added node
	 */
	public AVL<E> add(E i);
	
	/**
	 * 
	 * @param i the node to be removed
	 * @return the AVL tree sans node
	 */
	public AVL<E> remove(E i);
	
	/**
	 * 
	 * @return whether the tree is empty
	 */
	public boolean isEmpty();
	
	/**
	 * 
	 * @return the left branch of this AVL
	 */
	public AVL<E> getLeft();
	
	/**
	 * 
	 * @return the right branch of this AVL
	 */
	public AVL<E> getRight();
	
	/**
	 * 
	 * @return whether both of the children are empty
	 */
	public boolean areChildrenEmpty();
	
	/**
	 * 
	 * @return the maximum depth of this tree
	 */
	public int depth();
	
	/**
	 * 
	 * @return whether the tree is balanced
	 */
	public int getBalance();
	
	/**
	 * this method is not recomended for use
	 * 
	 * @return force the tree to balance
	 */
	public AVL<E> balance();
	
	/**
	 * for testing purposes only
	 * 
	 * @param depth the depth of this branch in the tree
	 */
	public void print(int depth);
}
