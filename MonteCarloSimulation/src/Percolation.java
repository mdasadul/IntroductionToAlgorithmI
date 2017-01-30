
import edu.princeton.cs.algs4.*;
import java.lang.*;

public class Percolation {
	private boolean[][] site;
	private int top = 0;
	private int down;
	private int N;
	WeightedQuickUnionUF qwuf;

	public Percolation(int n) // create n-by-n grid, with all sites blocked
	{
		if (n <= 0)
			throw new IllegalArgumentException();

		N = n;
		top = 0;
		down = n * n + 1;
		qwuf = new WeightedQuickUnionUF(n * n + 2);

		site = new boolean[n][n];

	}

	private int xyToN(int row, int col) {
		return (row - 1) * N + col;
	}

	public void open(int row, int col) // open site (row, col) if it is not open
										// already
	{
		

		site[row-1][col-1] = true;
		if(row == 1){
			qwuf.union(xyToN(row,col), top);
		}
		
		if(row == N){
			qwuf.union(xyToN(row,col), down);
		}
		
		if (col<N && isOpen(row, col+1)) {
			
			qwuf.union(xyToN(row,col+1), xyToN(row,col));
		}
		if (col>1 && isOpen(row,col-1)) {

			qwuf.union(xyToN(row,col), xyToN(row,col-1));
		}
		if (row>1 && isOpen(row-1,col)) {
			qwuf.union(xyToN(row,col),xyToN(row-1,col) );
		}
		if (row<N && isOpen(row+1,col)) {
			qwuf.union(xyToN(row,col),xyToN(row+1,col) );
		}
	}

	public boolean isOpen(int row, int col) // is site (row, col) open?
	{
		
		return site[row-1][col-1];
		
	}

	public boolean isFull(int row, int col) // is site (row, col) full?
	{
		int node = xyToN(row, col);

		if (0 >= node || node > N * N) {
			throw new IndexOutOfBoundsException();
		}
		return qwuf.connected(node, top);
	}

	public int numberOfOpenSites() // number of open sites
	{
		int numOpenSites = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (site[i][j])
					numOpenSites++;
			}
		}
		return numOpenSites;
	}

	public void printOpenSites() // number of open sites
	{
		int numOpenSites = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.println(site[i][j]);
				// numOpenSites++;
			}
		}
		// return numOpenSites;
	}

	public boolean percolates() // does the system percolate?
	{
		return qwuf.connected(top, down);

	}

	public static void main(String[] args) {
		int n = StdIn.readInt();
		int p = 200;
		Percolation perCol = new Percolation(n);
		while (true && p > 0) {
			int item = StdRandom.uniform(n * n);
			System.out.println("Item " + item);
			perCol.open(item / n, item % n);
			perCol.printOpenSites();
			p--;
			if (perCol.percolates()) {
				System.out.println("NumberOf Node " + perCol.numberOfOpenSites());
				break;
			}
		}
	}

}
