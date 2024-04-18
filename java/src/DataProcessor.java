import java.sql.ResultSet;

public class DataProcessor {

    public static void processOneRow(ResultSet rs) {
        try {
            // Let's imagine processing one row lasts 100000ns = 0.1ms
            Thread.sleep(0, 100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
