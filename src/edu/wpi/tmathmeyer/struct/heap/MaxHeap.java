package edu.wpi.tmathmeyer.struct.heap;

import java.io.PrintStream;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
	
	private transient Object[] heap;
	private int quarantine;
	
	public MaxHeap(int size){
		this.heap = new Object[size];
		quarantine = 0;
	}
	
	public MaxHeap(E[] values){
		this.heap = values;
		quarantine = heap.length;
	}
	
	@Override
	public void setMaxHeapSize(int size) throws HeapSizeException {
		if (size < getHeapSize()) throw new HeapSizeException();
		Object[] newHeap = new Object[size];
		for(int i=0;i<newHeap.length;i++)
			newHeap[i] = heap[i];
		heap = newHeap;
	}

	@Override
	public int getMaxHeapSize() {
		return heap.length;
	}

	@Override
	public int getHeapSize() {
		return quarantine;
	}

	@Override
	public void trim() throws HeapSizeException{
		this.setMaxHeapSize(this.getHeapSize());
	}

	@Override
	public boolean isMaxHeap() {
		return false;
	}

	@Override
	public E getMinimumElement() throws BadHeapTypeException {
		throw new BadHeapTypeException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getMaximumElement() throws BadHeapTypeException {
		return (E)heap[0];
	}

	@Override
	public boolean addElement(E addition) throws HeapSizeException {
		if (quarantine >= heap.length)throw new HeapSizeException();
		heap[quarantine] = addition;
		quarantine++;
		heapify();
		return true;
	}
	
	
	@SuppressWarnings("unchecked")
	private void heapify(){
		for(int lhi = 0; lhi < quarantine-1; lhi++){
			int add = lhi + 1;
			int parent = lhi/2;
			while(((Comparable<E>) heap[parent]).compareTo((E) heap[add]) < 0){
				swap(parent, add);
				add = parent; 
				parent = (parent-1)/2;
			}
		}
	}
	
	private void swap(int px, int py){
		Object temp = heap[px];
		heap[px] = heap[py];
		heap[py] = temp;
	}

	@Override
	public void removeTopElement() {
		quarantine--;
		swap(0, quarantine);
		heapify();
	}

	@Override
	public void print(PrintStream ps) {
		this.print(0, ps);
	}
	
	private void print(int j, PrintStream ps){
		if (j < quarantine){
			for(int i = 0; i < j; i++)ps.print(" ");
			ps.println(heap[j].toString());
			print(j*2+1, ps);
			print(j*2+2, ps);
		}
	}
	
}
