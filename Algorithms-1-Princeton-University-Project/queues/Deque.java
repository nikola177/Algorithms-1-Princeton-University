import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

	private class Node<Item>{
		
		private Item item;
		private Node<Item> previous;
		private Node<Item> next;
		
		
		public Node(Item item, Node<Item> previous, Node<Item> next){
			
			this.item=item;
			this.previous = previous;
			this.next = next;
		}
	}

	private Node<Item> first;
	private Node<Item> last;
	private int n;
	
	public Deque() {
		
		first = null;
		last = null;
		n = 0;
	}
	
	//Is dequeue empty?
	
	public boolean isEmpty() {
		
		return first==null && last==null;
		
	}
	
	//size of dequeue
	
	public int size() {
	
		return n;
		
	}
	
	// add the item to the front
	
	public void addFirst(Item item) {
		
		if(item==null) throw new IllegalArgumentException();
		
		Node<Item> currentNode = new Node<Item>(item, null,null);
		
		if(first==null) {
			
			first = currentNode;
			last = currentNode;
		}
		else{
			
			currentNode.next = first;
			first.previous = currentNode;
			
			first = currentNode;
		}
	
		n++;
		
	}
	
	// add the item to the back
	
    public void addLast(Item item) {
    	
    	if(item==null) throw new IllegalArgumentException();
    	
    	Node<Item> currentNode = new Node<Item>(item, null,null);
    	
    	if(last==null) {
    		
    		first = currentNode;
    		last = currentNode;
    	}
    	else {
    		
    		 last.next = currentNode;
    		 currentNode.previous = last;
    		
    		 last = currentNode;
    	}
  
    	n++;
    
    }
    
    // remove and return the item from the front
    
    public Item removeFirst() {
    	
    	if(isEmpty()) throw new NoSuchElementException();
    	
    	Item returnItem = null;
    	
    	if(first==last) {
    		
    		 returnItem = first.item;
    		 first = null;
    		 last = null;
    	}
    	else {
    		
    		returnItem = first.item;
    		first = first.next;
    		first.previous = null;
    	}
    	
    	n--;
    	return returnItem;
    	
    }
    
    // remove and return the item from the back
    
    public Item removeLast() {
    	
    	if(isEmpty()) throw new NoSuchElementException();
    	
    	Item returnItem = null;
    	
    	if(first==last) {
    		
    		returnItem = first.item;
    		first = null;
    		last = null;
    	}
    	else {
    		
    		returnItem = last.item;
    		last = last.previous;
    		last.next = null;
    	}
    	
    	n--;
    	return returnItem;
    	
    }
 
    @Override
    public Iterator<Item> iterator() {
    	
    	return new Iterator<Item>() {

    		private Node<Item> current = first;
    		
			@Override
			public boolean hasNext() {
				
				return current != null;
			}

			@Override
			public Item next() {
				
				if(current==null) {
					
					throw new NoSuchElementException();
				}
				
				Item item = current.item;         
	    		current   = current.next;
	    		
	    		return item; 
			}
			
			public void remove(){
	    		
	    		throw new UnsupportedOperationException();
	    		
	    		} 
    	};
    }
    
    //Unit tests
    
	public static void main(String[] args) {
		
		Deque<String> deque = new Deque<String>();
		deque.addFirst("First");
		deque.addLast("Last");

		StdOut.println("Show Deque: ");
		for (String string : deque) {
			StdOut.print(string + ", ");
		}

		StdOut.println();

		deque.addFirst("0. Node");
		deque.addLast("Third Node");

		StdOut.println("Show Deque: ");
		for (String string : deque) {
			StdOut.print(string + ", ");
		}

		StdOut.println();

		StdOut.println("Removed: ");
		StdOut.print(deque.removeFirst() + ", ");
		StdOut.print(deque.removeLast());
		
		StdOut.println();
		
		StdOut.println("Empty deque: "+deque.isEmpty());
		
		
		
		StdOut.println("Show Deque: ");
		for (String string : deque) {
			StdOut.print(string + ", ");
		}

		StdOut.println();

		StdOut.println("Removed: ");
		StdOut.print(deque.removeFirst() + ", ");
		StdOut.print(deque.removeFirst());

		StdOut.println();
		StdOut.println("Empty deque: "+deque.isEmpty());
		
		System.out.println();
		try {
			deque.removeLast();
		}
		catch (IllegalArgumentException e) {
			StdOut.println("Catched: " + e);
		}

		StdOut.println("Show Deque: ");
		for (String string : deque) {
			StdOut.print(string + ", ");
		}

		StdOut.println();

		try {
			deque.addFirst(null);
		}
		catch (IllegalArgumentException e) {
			StdOut.println("Catched: " + e);
		}
		
	}

}
