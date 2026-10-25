public class SensorGridMonitorApp {
    public static void main(String[] args) throws InterruptedException {
        final int n = 50000, workers = 4;
        double[] data = new double[n];
        for (int i = 0; i < n; i++) data[i] = (i * 17 % 10000) / 100.0;

        ParallelMinSearch[] mins = new ParallelMinSearch[workers];
        ParallelMaxSearch[] maxs = new ParallelMaxSearch[workers];
        Thread[] threads = new Thread[workers * 2];

        long startTime = System.nanoTime();
        int chunk = n / workers;
        for (int w = 0; w < workers; w++) {
            int start = w * chunk;
            int end = (w == workers - 1) ? n : start + chunk;
            mins[w] = new ParallelMinSearch(data, start, end);
            maxs[w] = new ParallelMaxSearch(data, start, end);
            threads[w] = new Thread(mins[w], "Min-Worker-" + w);
            threads[w + workers] = new Thread(maxs[w], "Max-Worker-" + w);
            threads[w].start();
            threads[w + workers].start();
        }
        for (Thread thread : threads) thread.join();

        double min = Double.POSITIVE_INFINITY, max = Double.NEGATIVE_INFINITY;
        for (int w = 0; w < workers; w++) {
            min = Math.min(min, mins[w].processGrid());
            max = Math.max(max, maxs[w].processGrid());
        }
        long elapsed = System.nanoTime() - startTime;
        System.out.println("Global minimum temperature: " + min);
        System.out.println("Global maximum vibration value: " + max);
        System.out.printf("Parallel min/max elapsed: %.3f ms%n", elapsed / 1_000_000.0);
    }
}
