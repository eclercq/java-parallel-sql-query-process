import java.sql.ResultSet;
import java.util.logging.Logger;

public class DataProcessor {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void processOneRow(ResultSet rs) {
        try {
            // Let's imagine processing one row lasts 100000ns = 0.1ms
            Thread.sleep(0, 100000);
        } catch (InterruptedException e) {
            logger.severe(e.getMessage());
        }
    }
}
