package edu.wpi.tmathmeyer.struct.avl;

public class AVLTree<E extends Comparable<E>> implements AVL<E>{
	E node;
	public AVL<E> left = new EmptyAVLTree<E>();
	public AVL<E> right = new EmptyAVLTree<E>();

	public AVLTree(E i){
		this.node = i;
	}
	@Override
	public E getNode(){
		return node;
	}
	@Override
	public AVL<E> add(E i){
		if (i.compareTo(this.getNode()) > 0)
			this.right = this.right.add(i);
		else
			this.left = this.left.add(i);
		return this.balance();
	}
	@Override
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
	@Override
	public boolean isEmpty(){
		return false;
	}
	@Override
	public AVL<E> getLeft(){
		return this.left;
	}
	@Override
	public AVL<E> getRight(){
		return this.right;
	}
	@Override
	public boolean areChildrenEmpty(){
		return this.getLeft().isEmpty() && this.getRight().isEmpty();
	}
	@Override
	public int depth(){
		int a = this.getLeft().depth();
		int b = this.getRight().depth();
		return (a>b?a:b) + 1;
	}
	@Override
	public int getBalance(){
		int a = this.getLeft().depth();
		int b = this.getRight().depth();
		return a-b;
	}
	@Override
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
	@Override
	public void print(int depth){
		for(int i = 0; i < depth; i++)System.out.print("  ");
		System.out.println(this.getNode().toString());
		this.getLeft().print(depth+1);
		this.getRight().print(depth+1);
	}

	@Override
	public boolean hasElement(E e) {
		if (this.getNode().equals(e)) return true;
		if (this.getNode().compareTo(e) > 0) return this.getLeft().hasElement(e);
		return this.getRight().hasElement(e);
	}
}
