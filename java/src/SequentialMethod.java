import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class SequentialMethod {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    
    public static void run(Connection connection) {
        long startTime = System.currentTimeMillis();
        logger.info("Starting basic method...");

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            stmt.setFetchSize(1000);

            String query = "SELECT * FROM test_table";

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                DataProcessor.processOneRow(rs);
            }
        } catch (SQLException e) {
           logger.severe(e.getMessage());;
        }

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        logger.info("Basic method ended : " + timeElapsed + "ms");
    }
}
