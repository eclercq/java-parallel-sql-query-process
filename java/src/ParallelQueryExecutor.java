import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ParallelQueryExecutor {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private final int DEFAULT_FETCH_SIZE = 1000;

    private int maxThreads;

    public ParallelQueryExecutor(int _maxThreads) {
        this.maxThreads = _maxThreads;
    }

    public void execute(Connection connection, String query, RowProcessFunction rowProcessFunction, int fetchSize) {
        Statement stmt = null;
        final ResultSet[] rs = {null};

        List<Callable<Void>> tasks = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(this.maxThreads);

        try {
            stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            stmt.setFetchSize(fetchSize);

            rs[0] = stmt.executeQuery(query);
            while (rs[0].next()) {
                Callable<Void> task = () -> {
                    rowProcessFunction.execute(rs[0]);
                    return null;
                };
                tasks.add(task);
            }
            executorService.invokeAll(tasks);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        } catch (InterruptedException e) {
            logger.severe(e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            try { if (rs[0] != null) rs[0].close(); } catch (SQLException e) {logger.severe(e.getMessage());};
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {logger.severe(e.getMessage());};
            executorService.shutdown();
        }
    }
    
    public void execute(Connection connection, String query, RowProcessFunction rowProcessFunction) {
        execute(connection, query, rowProcessFunction, DEFAULT_FETCH_SIZE);
    }
}

@FunctionalInterface
interface RowProcessFunction {
    void execute(ResultSet rs) throws SQLException;
}
