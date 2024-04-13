public class DataProcessor {

    public static void processOneRow() {
        try {
            // Let's imagine processing one row lasts 10000ns = 0.01ms
            Thread.sleep(0, 10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
