import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 * The Percolation class is used to model a percolation system
 * */

public class Percolation {

	private int n;
	private WeightedQuickUnionUF uf;
	private int virtualTopSite;
	private int virtualBotSite;
	private byte[] site; //0 close; 1 open;
	private int numberOfOpenSites;
	
	// Creates n-by-n grid, with all sites initially blocked
	
    public Percolation(int n) {
    	
    	if(n<=0) throw new IllegalArgumentException();
    	
    	this.n = n;
    	uf = new WeightedQuickUnionUF(n*n+2);
    	site = new byte[n*n];
    	virtualTopSite = n*n;
    	virtualBotSite = n*n+1;
    	numberOfOpenSites=0;
    }

    //Opens the site (row, col) if it is not open already
    
    public void open(int row, int col) {
    	
    	isInBound(row,col);
    	
    	if(isOpen(row,col)) {
    		return;
    	}
    	
    	int x = convert2CoordinateTo1Coordinate(row,col);
		site[x] = 1;
		
		incrementNumberOfOpenSites();
		
		//union with top site
		
		if(row>1) {
			if(isOpen(row-1,col)) {
				uf.union(x, convert2CoordinateTo1Coordinate(row-1,col));
			}
		}
		
		//union with bottom site
		
		if(row<n) {
			if(isOpen(row+1,col)) {
				uf.union(x, convert2CoordinateTo1Coordinate(row+1,col));
			}
		}
		
		//union with virtual top site
		
		if(row==1) {
			uf.union(x, virtualTopSite);
		}
		
		//union with virtual bottom site
		
		if(row==n) {
			uf.union(x, virtualBotSite);
		}
		
		//union with left site
		
		if(col>1) {
			if(isOpen(row,col-1)) {
				uf.union(x, convert2CoordinateTo1Coordinate(row,col-1));
			}
		}
		//union with right site
		
		if(col<n){
			if(isOpen(row,col+1)) {
						uf.union(x, convert2CoordinateTo1Coordinate(row,col+1));
			}
		}
    }

    // Returns the number of open sites
    
    public int numberOfOpenSites() {
    	
    	return numberOfOpenSites;
    }
    
    //Checks if the site with given row and column is full (connected with top)
    
    public boolean isFull(int row, int col) {
    	
    	isInBound(row,col);
    	
    	int x = convert2CoordinateTo1Coordinate(row,col);
    	
    	return uf.find(x)==uf.find(virtualTopSite);   	
    }

    //Checks if the system percolate
    
    public boolean percolates() {
    	
    	return uf.find(virtualTopSite)==uf.find(virtualBotSite);
    }

    private void incrementNumberOfOpenSites() {
    	numberOfOpenSites++;
    }
    
    //Converting matrix coordinates to coordinate for array uf.parent
    
    private int convert2CoordinateTo1Coordinate(int row, int col) {
    	
    	return (row-1)*n+col-1;
    }
    
    //Is the site correct
    
   private boolean isInBound(int row, int col) {
    	
    	if(row>n || row<1 || col>n || col<1) throw new IndexOutOfBoundsException();
    	
    	return true;
    }
   
   	//is the site (row, col) open?
   
    public boolean isOpen(int row, int col) {
    	
    	isInBound(row,col);
    	
    	if(site[convert2CoordinateTo1Coordinate(row,col)]==1) return true;
    	
    	return false;
    }
    
}
