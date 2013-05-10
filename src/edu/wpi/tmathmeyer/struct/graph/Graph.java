package edu.wpi.tmathmeyer.struct.graph;

import edu.wpi.tmathmeyer.struct.heap.*;

public class Graph<E extends Node, L extends Link>{
	//private Heap<E> nodes;

	public Graph(int nodeCount, int linkCount){
		//nodes = new MinHeap<E>(nodeCount);
	}

	public Graph(int nodeCount){
		this(nodeCount, nodeCount*nodeCount);
	}

	public Graph(E[] nodes){
		//nodes = new MinHeap<E>(nodes);
	}

	public Graph(E[] nodes, L[] links){

	}
}