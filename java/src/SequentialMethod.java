import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SequentialMethod {
    
    public static void run(Connection connection) {
        long startTime = System.currentTimeMillis();
        System.out.println("Starting basic method...");

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
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println("Basic method ended : " + timeElapsed + "ms");
    }
}
