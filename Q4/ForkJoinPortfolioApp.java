import java.util.concurrent.ForkJoinPool;

public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        double[] assets = new double[10000];
        for (int i = 0; i < assets.length; i++) assets[i] = 10 + (i % 100);

        ForkJoinPool pool = new ForkJoinPool();
        long parallelStart = System.nanoTime();
        double totalBeforeStress = pool.invoke(
                new ParallelRiskCalculator(assets, 0, assets.length));
        pool.invoke(new PortfolioThresholdTask(assets, 0, assets.length, 75.0));
        double totalAfterStress = pool.invoke(
                new ParallelRiskCalculator(assets, 0, assets.length));
        long parallelTime = System.nanoTime() - parallelStart;

        long serialStart = System.nanoTime();
        double serialTotal = 0;
        for (double value : assets) serialTotal += value;
        long serialTime = System.nanoTime() - serialStart;

        System.out.printf("Risk total before stress: %.2f%n", totalBeforeStress);
        System.out.printf("Risk total after stress: %.2f%n", totalAfterStress);
        System.out.printf("Serial verification total: %.2f%n", serialTotal);
        System.out.printf("Parallel workflow time: %.3f ms%n", parallelTime / 1_000_000.0);
        System.out.printf("Serial sum time: %.3f ms%n", serialTime / 1_000_000.0);
        pool.shutdown();
    }
}
