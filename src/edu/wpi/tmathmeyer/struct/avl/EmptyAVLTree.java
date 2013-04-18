package edu.wpi.tmathmeyer.struct.avl;

public class EmptyAVLTree<E extends Comparable<E>> implements AVL<E>{
	public E getNode(){
		return null;
	}
	public AVL<E> add(E i){
		return new AVLTree<E>(i);
	}
	public AVL<E> remove(E i){
		return this;
	}

	public boolean isEmpty(){
		return true;
	}
	public AVL<E> getLeft(){
		return this;
	}
	public AVL<E> getRight(){
		return this;
	}
	public boolean areChildrenEmpty(){
		return true;
	}
	public int depth(){
		return 0;
	}
	public int getBalance(){
		return 0;
	}
	public AVL<E> balance(){
		return this;
	}
	public void print(int depth){

	}
}
