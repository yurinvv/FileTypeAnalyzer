package service.timer;

public class Timer {

    private long startTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public String stop() {
        long time = System.nanoTime() - startTime;
        double dtime = (double) time / 1_000_000_000;
        String val = String.format("%f", dtime).replace(",", ".");
        return String.format("It took %s seconds", val);
    }
}
