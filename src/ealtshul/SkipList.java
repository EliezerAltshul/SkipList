package ealtshul;

import java.util.*;

public class SkipList<T> {
	private Node<T> head = new Node<T>(-1, null);
	private int size = 0;
	private int height = 0;
	private Random coinToss = new Random();
	
	private class Node <E>{
		public int index;
		public E data;
	    public Node<E> nextNode;
	    public Node<E> belowNode;
	    
	    public Node(int index, E data) {
	    	this.index = index;
			this.data = data;
	    }
	    
	    public String toString() {
			return "Index: " + index
				 + " Data: " + data;
	    }
	}
	
	//a constructor for simple instantiation
	public SkipList() {}
	
	public void insert(int index, T data) throws NegativeIndexException{
		if(index<0) throw new NegativeIndexException();
		//toss coin to determine whether the new data should go on a higher level
		int level = 0; 
		while (coinToss.nextBoolean() && level<height) 
			level++;
		
		//if the determined level is equal to the number of levels
		//in other words, if the determined level is the head level
		//then add a new level
		Node<T> curr = head;
		Node<T> aboveNode = null;
		boolean increaseSize = true;
		if(level == height) {
			Node<T> headLevel =  new Node<T>(-1, null);
			headLevel.belowNode = head.belowNode;
			head.belowNode = headLevel;
			Node<T> insert = new Node<T>(index, data);
			headLevel.nextNode = insert;
			curr = headLevel.belowNode;
			height++;
		}
		else 
			//reach the node that is on the determined level
			for(int i = 0; i<height-level; i++) 
	    		curr = curr.belowNode;
			
		while (curr != null) {
		    Node<T> insert = new Node<T>(index, data);
		    Node<T> node = curr;
		    Node<T> prev = null;
		    while(node != null){
		    	//as long as the insert node's index is larger than the current node
		    	//have the current node point next to the insert node
		    	//and have the insert point next to the current's next node
		    	if(insert.index > node.index) {
		    		if(prev != null) prev.nextNode = node;
		    		Node<T> temp = node.nextNode;
		    		node.nextNode = insert;
		    		insert.nextNode = temp;
		    		prev = node;
		    		node = temp;
		    	}
		    	else {
		    		if(insert.index == node.index) {
		    			insert.nextNode = node.nextNode;
		    			increaseSize = false;
		    		}
		    		else insert.nextNode = node;
		    		if(aboveNode != null) 
		    			aboveNode.belowNode = insert;
		    		break;
		    	}
		    	
		    }
		    	
		    aboveNode = insert;
		    curr = curr.belowNode;
		}
		if(increaseSize) size++;
	}
	
	public T search(int searchIndex) {
		Node<T> curr = head;
		
		while(curr != null){
		    Node<T> node = curr;
		    
		    while(node != null) {
		    	if(node.index == searchIndex)
		    		return node.data;
		    	else node = node.nextNode;
		    }
		    
		    curr = curr.belowNode;
		}
		
		return null;
	}
	
    
    public int size() {
    	return size;
    }
    
    public int height() {
    	return height;
    }

    public String toString() {
    	StringBuilder str = new StringBuilder();
    	Node<T> currLevel = head;
    	
    	while(currLevel != null) {
    		Node<T> curr = currLevel;
    		while(curr != null) {
    			if(curr.index == -1) str.append("HEAD");
    			else str.append("[" + curr + "]");
    			curr = curr.nextNode;
    			if(curr != null) str.append(" -> ");
    		}
    		currLevel = currLevel.belowNode;
    		str.append("\n");
    	}
		return str.toString();
    }
    
}
