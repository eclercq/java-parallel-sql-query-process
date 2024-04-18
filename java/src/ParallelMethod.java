import java.sql.Connection;

public class ParallelMethod {

    private static final int MAX_THREADS = 3; 
    
    public static void run(Connection connection) {
        long startTime = System.currentTimeMillis();
        System.out.println("Starting enhanced method...");

        String query = "SELECT * FROM test_table";

        ParallelQueryExecutor parallelQuery = new ParallelQueryExecutor(MAX_THREADS);
        parallelQuery.execute(connection, query, rs -> {
            DataProcessor.processOneRow(rs);
        });


        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println("Enhanced method ended : " + timeElapsed + "ms");
    }
}
