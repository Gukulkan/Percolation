import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] x;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        int allSites = N * N;
        x = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);

            int openSites = 0;
            while (!p.percolates()) {
                int xIdx = StdRandom.uniform(N) + 1;
                int yIdx = StdRandom.uniform(N) + 1;
                if (p.isOpen(xIdx, yIdx)) {
                    continue;
                }
                p.open(xIdx, yIdx);
                openSites++;
            }
            x[i] = (openSites * 1.0) / allSites;
        }
    }

    public double mean() {
        return StdStats.mean(x);
    }

    public double stddev() {
        return StdStats.stddev(x);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(x.length);
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(x.length);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(N, T);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", "
                + ps.confidenceHi());
    }
}