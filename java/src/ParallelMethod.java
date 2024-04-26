import java.sql.Connection;
import java.util.logging.Logger;

public class ParallelMethod {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final int MAX_THREADS = 3; 
    
    public static void run(Connection connection) {
        long startTime = System.currentTimeMillis();
        logger.info("Starting enhanced method...");

        String query = "SELECT * FROM test_table";

        ParallelQueryExecutor parallelQuery = new ParallelQueryExecutor(MAX_THREADS);
        parallelQuery.execute(connection, query, rs -> {
            DataProcessor.processOneRow(rs);
        });


        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        logger.info("Enhanced method ended : " + timeElapsed + "ms");
    }
}
