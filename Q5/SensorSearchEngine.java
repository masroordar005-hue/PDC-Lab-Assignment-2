public abstract class SensorSearchEngine {
    protected double[] sensorData;
    protected int startIdx, endIdx;

    public SensorSearchEngine(double[] sensorData, int startIdx, int endIdx) {
        this.sensorData = sensorData;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    public abstract double processGrid();
}
