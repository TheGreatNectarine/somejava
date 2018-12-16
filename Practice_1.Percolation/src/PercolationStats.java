public class PercolationStats {

    public static void main(String[] args) {
        double sum = 0;
        for (int i = 0; i < Percolation.iterations; i++) {
            sum += test();
            if (i % (Percolation.iterations / 100) == 0) {
                System.out.printf("%d iterations out of %d\n", i, Percolation.iterations);
            }
        }
        double result = sum / Percolation.iterations / (Percolation.field_dim * Percolation.field_dim);
        System.out.printf("Result: %.4f\nPrecision: %.1f%s", result, result / 0.593 * 100, "%");

    }

    private static int test() {
        Percolation percolation = new Percolation(Percolation.field_dim);
        while (!percolation.percolates()) {
            percolation.open_and_connect_randomly();
        }
        return percolation.get_open_count();
    }

}