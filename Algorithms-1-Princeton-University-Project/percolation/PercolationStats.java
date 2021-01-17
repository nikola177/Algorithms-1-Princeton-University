import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/* PercolationStats class represents data type used to estimate threshold,
* considering the computational experiment known as Monte Carlo
* simulation.
* 
1. Initialize all sites to be blocked.

2. Repeat the following until the system percolates:

    2.1 Choose a site uniformly at random among all blocked sites.

    2.2 Open the site. 

3. The fraction of sites that are opened when the system percolates provides
an estimate of the percolation threshold. 
* 
* 
* */


public class PercolationStats {

	private double[] thresholds;
	private int T;

	// Perform T independent computational experiments on an N-by-N grid
	
    public PercolationStats(int n, int trials) {
    	
    	if(n<=0 || trials<=0) throw new IllegalArgumentException();
    	
    	T = trials;
    	thresholds = new double[trials];
    	
    	for(int i=0; i<trials; i++) {
    		
    		thresholds[i] = calculateTresholds(n);
    	}
    }

    private double calculateTresholds(int n) {

    		int row,col;

    		Percolation percolation = new Percolation(n);
    		
    		while(!percolation.percolates()) {
    			
    			row = 1 + StdRandom.uniform(n);
    			col = 1 + StdRandom.uniform(n);
    			
    			if(!percolation.isOpen(row,col)){
    				
    				percolation.open(row, col);
    				
    			}
    			
    		}
    	
    		return (double)(percolation.numberOfOpenSites())/(n*n);
    }
    
    // Sample mean of percolation threshold
    
    public double mean() {
    	return StdStats.mean(thresholds);
    }
	
    // Sample standard deviation of percolation threshold
    
    public double stddev() {
    	return StdStats.stddev(thresholds);
    }
    
    // Lower bound of the 95% confidence interval
    
    public double confidenceLo() {
    	
    	return mean()-(1.96*stddev())/Math.sqrt(T);
    }

    // Upper bound of the 95% confidence interval
    
    public double confidenceHi() {
    	return mean()+(1.96*stddev())/Math.sqrt(T);
    }
	
    // Test client
    
	public static void main(String[] args) {
		
		/*Course style for testing:
		 * 
		int n = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		---------------------------------------
		
		Simpler way for testing:
		
		int n = 100;
		int T = 1000;
		
		Try different value for n and T.
		
		*/
		int n = 100;
		int T = 1000;
		
		PercolationStats pS = new PercolationStats(n, T);
		StdOut.println("mean                    = " + pS.mean());
		StdOut.println("stddev                  = " + pS.stddev());
		StdOut.println("95% confidence interval = [" + pS.confidenceLo()+", " + pS.confidenceHi() + "]");
		
	}

}
