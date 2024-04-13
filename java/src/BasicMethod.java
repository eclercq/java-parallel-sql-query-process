import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicMethod {
    
    public static void run(Connection connection) {
        long startTime = System.currentTimeMillis();
        System.out.println("Starting basic method...");

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();

            String query = "SELECT * FROM test_table";

            System.out.println("Executing SELECT query...");
            rs = stmt.executeQuery(query);
            
            System.out.println("SELECT query executed.");
            System.out.println("Processing results...");
            while (rs.next()) {
                DataProcessor.processOneRow();
            }
            System.out.println("Results processed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println("Basic method ended : " + timeElapsed + "ms");
    }
}
