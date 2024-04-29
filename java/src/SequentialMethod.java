import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SequentialMethod implements Method {

    private String query;

    public SequentialMethod(String _query) {
        this.query = _query;
    }
    
    @Override
    public void run(Connection connection) {
        System.out.println("Starting basic method...");

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            stmt.setFetchSize(1000);

            rs = stmt.executeQuery(this.query);
            while (rs.next()) {
                DataProcessor.processOneRow(rs);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

        System.out.println("Basic method ended.");
    }
}
