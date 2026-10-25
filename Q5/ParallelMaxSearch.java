public class ParallelMaxSearch extends SensorSearchEngine implements Runnable {
    private double result;

    public ParallelMaxSearch(double[] data, int start, int end) {
        super(data, start, end);
        result = Double.NEGATIVE_INFINITY;
    }

    @Override
    public double processGrid() {
        for (int i = startIdx; i < endIdx; i++)
            if (sensorData[i] > result) result = sensorData[i];
        return result;
    }

    @Override
    public void run() { processGrid(); }
}
