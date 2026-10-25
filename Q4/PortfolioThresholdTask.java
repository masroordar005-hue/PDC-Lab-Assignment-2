import java.util.concurrent.RecursiveAction;

public class PortfolioThresholdTask extends RecursiveAction {
    private static final int THRESHOLD = 1000;
    private final double[] values;
    private final int start, end;
    private final double riskCap;

    public PortfolioThresholdTask(double[] values, int start, int end, double riskCap) {
        this.values = values;
        this.start = start;
        this.end = end;
        this.riskCap = riskCap;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                if (values[i] > riskCap) values[i] *= 1.05;
            }
            return;
        }
        int middle = start + (end - start) / 2;
        invokeAll(new PortfolioThresholdTask(values, start, middle, riskCap),
                  new PortfolioThresholdTask(values, middle, end, riskCap));
    }
}
