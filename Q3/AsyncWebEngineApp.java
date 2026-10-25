public class AsyncWebEngineApp {
    public static void main(String[] args) {
        System.out.println("Default JVM thread: " + Thread.currentThread().getName());

        TelemetryThread telemetry = new TelemetryThread();
        telemetry.setName("Telemetry-Worker");
        Thread logs = new Thread(new LogProcessorTask(), "Log-Worker");

        telemetry.start();
        logs.start();

        System.out.println("Telemetry state: " + telemetry.getState());
        System.out.println("Log state: " + logs.getState());
    }
}
