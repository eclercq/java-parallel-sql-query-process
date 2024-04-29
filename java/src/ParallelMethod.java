import java.sql.Connection;

public class ParallelMethod implements Method {

    private final int MAX_THREADS = 3;
    private String query;

    public ParallelMethod(String _query) {
        this.query = _query;
    }
    
    @Override
    public void run(Connection connection) {
        System.out.println("Starting enhanced method...");

        ParallelQueryExecutor parallelQuery = new ParallelQueryExecutor(MAX_THREADS);
        parallelQuery.execute(connection, this.query, rs -> {
            DataProcessor.processOneRow(rs);
        });

        System.out.println("Enhanced method ended.");
    }
}
