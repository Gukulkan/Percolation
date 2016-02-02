import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation  {

    enum Status {
        CLOSE,
        OPEN,
        FULL
    }

    private Status[][] percolationArray;

    private WeightedQuickUnionUF weightedQuickUnionUF;

    private int lastIndex;

    public Percolation(int N){               // create N-by-N grid, with all sites blocked
        percolationArray = new Status[N][N];
        Arrays.fill(percolationArray,Status.CLOSE);
        weightedQuickUnionUF = new WeightedQuickUnionUF(N*N);
        lastIndex = N*N;
    }
    public void open(int i, int j){          // open site (row i, column j) if it is not open already
        percolationArray[i][j] = Status.OPEN;
    }
    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
        return percolationArray[i][j] == Status.OPEN;
    }
    public boolean isFull(int i, int j) {    // is site (row i, column j) full?
        return percolationArray[i][j] == Status.FULL;
    }
    public boolean percolates() {     // does the system percolate?
        return weightedQuickUnionUF.connected(0,lastIndex);
    }


    public static void main(String[] args) {
	// write your code here
    }
}
