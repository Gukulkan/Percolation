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

    private int size;

    public Percolation(int N){               // create N-by-N grid, with all sites blocked
        percolationArray = new Status[N][N];
        Arrays.fill(percolationArray,Status.CLOSE);
        weightedQuickUnionUF = new WeightedQuickUnionUF(N*N + 2);
        size = N;
    }
    public void open(int i, int j){          // open site (row i, column j) if it is not open already
        validate(i, j);
        percolationArray[i][j] = Status.OPEN;

    }
    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
        validate(i, j);
        return percolationArray[i][j] == Status.OPEN;
    }
    public boolean isFull(int i, int j) {    // is site (row i, column j) full?
        validate(i, j);
        return percolationArray[i][j] == Status.FULL;
    }
    public boolean percolates() {     // does the system percolate?
        return weightedQuickUnionUF.connected(0,percolationArray.length * percolationArray.length);
    }


    private void validate(int i, int j){
        if(i < 1 || i > size || j < 1 || j > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int convert(int i, int j){
        return i*size+j;
    }

    public static void main(String[] args) {
	// write your code here
    }
}
