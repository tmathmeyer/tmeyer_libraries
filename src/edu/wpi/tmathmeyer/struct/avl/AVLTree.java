package edu.wpi.tmathmeyer.struct.avl;

public class AVLTree<E extends Comparable<E>> implements AVL<E>{
	E node;
	public AVL<E> left = new EmptyAVLTree<E>();
	public AVL<E> right = new EmptyAVLTree<E>();

	public AVLTree(E i){
		this.node = i;
	}

	public E getNode(){
		return node;
	}
	public AVL<E> add(E i){
		if (i.compareTo(this.getNode()) > 0)
			this.right = this.right.add(i);
		else
			this.left = this.left.add(i);
		return this.balance();
	}
	public AVL<E> remove(E i){
		if (this.getNode().equals(i)){
			if (this.getLeft().isEmpty()) return this.getRight();
			if (this.getRight().isEmpty()) return this.getLeft();
			
		}
		if (this.getNode().compareTo(i) > 0)
			this.right = this.getRight().remove(i);
		if (this.getNode().compareTo(i) < 0)
			this.left = this.getLeft().remove(i);
		return this;
	}
	public boolean isEmpty(){
		return false;
	}
	public AVL<E> getLeft(){
		return this.left;
	}
	public AVL<E> getRight(){
		return this.right;
	}
	public boolean areChildrenEmpty(){
		return this.getLeft().isEmpty() && this.getRight().isEmpty();
	}
	public int depth(){
		int a = this.getLeft().depth();
		int b = this.getRight().depth();
		return (a>b?a:b) + 1;
	}
	public int getBalance(){
		int a = this.getLeft().depth();
		int b = this.getRight().depth();
		return a-b;
	}
	public AVL<E> balance(){
		int bal = this.getBalance();
		if (Math.abs(bal) < 2)return this;

		if (bal > 1){
			AVLTree<E> newTop = (AVLTree<E>) this.getLeft();
			this.left = newTop.getRight();
			newTop.right = this;
			return newTop;
		}
		AVLTree<E> newTop = (AVLTree<E>) this.getRight();
		this.right = newTop.getLeft();
		newTop.left = this;
		return newTop;
	}

	public void print(int depth){
		for(int i = 0; i < depth; i++)System.out.print("  ");
		System.out.println(this.getNode().toString());
		this.getLeft().print(depth+1);
		this.getRight().print(depth+1);
	}
}
