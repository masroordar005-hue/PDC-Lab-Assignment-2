public class ParallelMinSearch extends SensorSearchEngine implements Runnable {
    private double result;

    public ParallelMinSearch(double[] data, int start, int end) {
        super(data, start, end);
        result = Double.POSITIVE_INFINITY;
    }

    @Override
    public double processGrid() {
        for (int i = startIdx; i < endIdx; i++)
            if (sensorData[i] < result) result = sensorData[i];
        return result;
    }

    @Override
    public void run() { processGrid(); }
}
