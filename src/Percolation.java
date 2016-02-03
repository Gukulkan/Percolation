import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation  {

    private enum Status {
        CLOSE,
        OPEN,
        FULL
    }

    private Status[][] percolationArray;

    private WeightedQuickUnionUF weightedQuickUnionUF;

    private int size;

    public Percolation(int N){               // create N-by-N grid, with all sites blocked
        percolationArray = new Status[N][N];
//        Arrays.fill(percolationArray,Status.CLOSE);
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                percolationArray[i][j] = Status.CLOSE;
            }
        }
        weightedQuickUnionUF = new WeightedQuickUnionUF(N*N + 2);
        size = N;
    }
    public void open(int i, int j){          // open site (row i, column j) if it is not open already
        validate(i, j);
        percolationArray[i-1][j-1] = Status.OPEN;

        if(i == 1){
            weightedQuickUnionUF.union(convert(i - 1, j - 1), size * size);
        }

        int leftY = j - 1;
        int rightY = j + 1;
        int upX = i - 1;
        int downX = i + 1;
        if (leftY > 0 && isOpen(i, leftY)) {
            weightedQuickUnionUF.union(convert(i - 1, j - 1), convert(i - 1, leftY - 1));
        }
        if (rightY <= size && isOpen(i, rightY)) {
            weightedQuickUnionUF.union(convert(i - 1, j - 1), convert(i - 1, rightY - 1));
        }
        if (upX > 0 && isOpen(upX, j)) {
            weightedQuickUnionUF.union(convert(i - 1, j - 1), convert(upX - 1, j - 1));
        }
        if (downX <= size && isOpen(downX, j)) {
            weightedQuickUnionUF.union(convert(i -1, j - 1), convert(downX - 1, j - 1));
        }

        if (i == size) {
            weightedQuickUnionUF.union(convert(i-1, j-1), size * size + 1);
        }

    }
    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
        validate(i, j);
        return percolationArray[i-1][j-1] == Status.OPEN;
    }
    public boolean isFull(int i, int j) {    // is site (row i, column j) full?
        validate(i, j);
        return isOpen(i, j) && weightedQuickUnionUF.connected(convert(i - 1, j - 1),size * size);
    }
    public boolean percolates() {     // does the system percolate?
        return weightedQuickUnionUF.connected(size*size, size*size + 1);
    }


    private void validate(int i, int j){
        if(i < 1 || i > size || j < 1 || j > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int convert(int i, int j){
        return i*size+j;
    }

}
