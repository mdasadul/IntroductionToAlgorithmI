import edu.princeton.cs.algs4.*;
import java.lang.*;
public class PercolationStats {
	private double[] threshold;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
	   if(n<=0 || trials <=0)
		   throw new IllegalArgumentException();
	   threshold = new double[trials+1];
	   for(int i =1;i<=trials;i++)
	   {
		   Percolation percolObj = new Percolation(n);
		   while(percolObj.percolates() ==false) {
			   int row = StdRandom.uniform(1,n+1);
			   int col = StdRandom.uniform(1,n+1);
			   percolObj.open(row, col);
		   }
		   threshold[i] = (double)percolObj.numberOfOpenSites()/(n*n);
	   }
		

	   
   }
   public double mean()                          // sample mean of percolation threshold
   {
	   return StdStats.mean(threshold);
   }
   public double stddev()                        // sample standard deviation of percolation threshold
   {
	   return StdStats.stddev(threshold);
   }
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
	  return mean()-1.96*stddev()/Math.sqrt(threshold.length);
   }
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
	   return mean() + 1.96*stddev()/Math.sqrt(threshold.length);

   }

   public static void main(String[] args)        // test client (described below)
   {
	   int N,T;
	   N=Integer.parseInt(args[0]);//stdIN.readINT();
	   System.out.println(N);
	   T=Integer.parseInt(args[1]);//stdIN.readINT();
	   
	   PercolationStats PS = new PercolationStats(N,T);
	   System.out.println(PS.mean());
	   System.out.println(PS.stddev());
	   System.out.println(PS.confidenceLo());
	   System.out.println( PS.confidenceHi());
	   
   }
}