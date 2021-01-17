import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {

		RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
		
				/*Course style for testing:
				 
				 *  int k = Integer.parseInt(args[0]);
				 
				    while (!StdIn.isEmpty()) {
					String s = StdIn.readString();
					randomizedQueue.enqueue(s);
				}
				---------------------------------------
				
				   Simpler way for testing:
				  
				  	Try with different values for k and array of String
				  
				    int k = 4;
				   
					randomizedQueue.enqueue("AA");
					randomizedQueue.enqueue("BB");
					randomizedQueue.enqueue("CC");
					randomizedQueue.enqueue("DD");
					randomizedQueue.enqueue("EE");
					randomizedQueue.enqueue("FF");
					randomizedQueue.enqueue("GG");
					randomizedQueue.enqueue("HH");
				  */
				int k = 4;
		
				randomizedQueue.enqueue("AA");
				randomizedQueue.enqueue("BB");
				randomizedQueue.enqueue("CC");
				randomizedQueue.enqueue("DD");
				randomizedQueue.enqueue("EE");
				randomizedQueue.enqueue("FF");
				randomizedQueue.enqueue("GG");
				randomizedQueue.enqueue("HH");

				for (int i = 0; i < k; i++) {
					StdOut.println(randomizedQueue.dequeue());
				}
		
		

	}

}
