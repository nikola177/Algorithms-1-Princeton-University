import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int N;
	private int count;
	private Item[] a;
	
	// construct an empty randomized queue
	
    public RandomizedQueue() {
    	
    	N = 1;
    	count = 0;
    	a = (Item[])new Object[N];
    	
    }

    // is the randomized queue empty?
    
    public boolean isEmpty() {
    	return count==0;
    }
    
    // return the number of items on the randomized queue
    
    public int size() {
    	return count;
    }

    // add the item
    
    public void enqueue(Item item) {
    	
    	if(item==null) throw new IllegalArgumentException();
    	
    	if(count==N) {
    		resize(2*N);
    		N = 2*N;
    	}
    	
    	a[count++] = item; 	
    }

    // remove and return a random item
    
    public Item dequeue() {
    	
    	if(isEmpty()) throw new NoSuchElementException();
    	
    	int randomIndex = StdRandom.uniform(count);
    	
    	swap(a,randomIndex,count-1);
    	
    	Item returnItem = a[count-1];
    	
    	a[count-1] = null;
    	count--;
    	
    	if(count==N/4) {
    		
    		resize(N/2);
    		N = N/2;
    		
    	}

    	return returnItem;
    }

    private void swap(Item[] a, int i, int j) {
    	
    	Item t = a[i];
    	a[i] = a[j];
    	a[j] = t;
    	
    }
    
    // return a random item (but do not remove it)
    
    public Item sample() {
    	
    	if(isEmpty()) throw new NoSuchElementException();
    	
    	int randomIndex = StdRandom.uniform(count);
    	
    	swap(a,randomIndex,count-1);
    	
    	return a[count-1];
    }
	
	private void resize(int capacity) {
		
		Item[] copy = (Item[])new Object[capacity];
		
		for(int i=0; i<count; i++) copy[i] = a[i];
		
		a = copy;
	}
    
	@Override
	public Iterator<Item> iterator() {
	
		return new RandomizedQueueIterator();
	
	}
	
	private class RandomizedQueueIterator implements Iterator<Item>{

		private int current = 0;
		private int[] shuffleArrayIndexes = new int[count];
		
		@Override
		public boolean hasNext() {
	
			return current<count;
		}

		@Override
		public Item next() {
			
			if(current==0) {
				
				for(int i=0; i<count; i++) shuffleArrayIndexes[i] = i;
				
				StdRandom.shuffle(shuffleArrayIndexes);
			}
			
			if(current>count || size()==0) throw new NoSuchElementException();
			
			return a[shuffleArrayIndexes[current++]];
		}
		
		public void remove(){
    		
    			throw new UnsupportedOperationException();
    		
    		} 
		
	}

	//Unit tests
	
	public static void main(String[] args) {
			
		RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
		randomQueue.enqueue("First");
		randomQueue.enqueue("Second");
		randomQueue.enqueue("Third");
		randomQueue.enqueue("Fourth");
		randomQueue.enqueue("Fifth");

		StdOut.println("Queue empty: " + randomQueue.isEmpty());
		StdOut.println("Size of a queue: " + randomQueue.size());
		StdOut.println("Sample of random element from queue: " + randomQueue.sample());
		StdOut.println("Size of a queue : " + randomQueue.size());

		for (String s : randomQueue) {
			StdOut.println(s);
		}

		while (!randomQueue.isEmpty()) {
			StdOut.println(randomQueue.dequeue());
		}
		
		StdOut.println("Queue empty: " + randomQueue.isEmpty());
		
	}

}
