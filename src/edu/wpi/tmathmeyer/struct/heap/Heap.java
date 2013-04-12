package edu.wpi.tmathmeyer.struct.heap;

import java.io.PrintStream;

public interface Heap<E extends Comparable<E>>{

	/**
	 * Sets the new maximum size for this heap.
	 * 
	 * @param size sets the new maximum heap size.
	 * @exception HeapSizeException if the new size is negative, or less than the current number of elements in the array, an exception will be thrown
	 */
	public void setMaxHeapSize(int size) throws HeapSizeException;

	/** 
	 * this gets the MAXIMUM size of the hea p, not the real size
	 * 
	 * @return the current maximum number of elements contained in the heap
	 */
	public int getMaxHeapSize();

	/**
	 * this gets the current real size of the heap, not it's maximum size
	 *
	 * @return the number of elements in the heap
	 */
	public int getHeapSize();

	/**
	 * this trims the maximum size of the heap to the current real size. not recommended for regular use.
	 * @throws HeapSizeException 
	 */
	public void trim() throws HeapSizeException;

	/**
	 * @return whether the heap is a Max-Heap or not (Min-Heap)
	 */
	public boolean isMaxHeap();

	/**
	 *
	 * @return the minimum element in the heap, provided it is a minHeap
	 * @throws BadHeapTypeException an exception is thrown if an attempt is made to get the minimum element from a maxHeap
	 */
	public E getMinimumElement() throws BadHeapTypeException;

	/**
	 *
	 * @return the maximum element in the heap, provided it is a maxHeap
	 * @throws BadHeapTypeException an exception is thrown if an attempt is made to get the maximum element from a minHeap
	 */
	public E getMaximumElement() throws BadHeapTypeException;

	/**
	 * @param addition adds this element to the heap
	 * @throws HeapSizeException an exception is throws if the heap is already at its maimum size
	 */
	public boolean addElement(E addition) throws HeapSizeException;
	
	/**
	 * remove the top element of heap.
	 */
	public void removeTopElement();
	
	/**
	 * 
	 * @param ps the PrintStream to which the heap is printed
	 */
	public void print(PrintStream ps);
}