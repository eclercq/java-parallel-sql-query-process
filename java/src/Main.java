import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Thread;
import java.util.logging.Logger;

class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws SQLException {
        Connection connection = getDatabaseConnection();
        logger.info("Wait for database initialization...");
        boolean dbInitialized = false;
        while (!dbInitialized) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT COUNT(*) FROM test_table";
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    int rowCount = resultSet.getInt(1);
                    if (rowCount > 0) {
                        dbInitialized = true;
                        logger.info("Database initialized! (" + rowCount + " lines)");
                    } else {
                        Thread.sleep(2000);
                    }
                }
                resultSet.close();
                statement.close();
            } catch (Exception e) {
                logger.severe(e.getMessage());;
            }
        }

        SequentialMethod.run(connection);
        
        ParallelMethod.run(connection);

        connection.close();
    }

    public static Connection getDatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.severe("Issue loading JDBC driver : " + e.getMessage());
        }

        String url = "jdbc:postgresql://parallel-data-process-database:5432/db";
        String user = "postgres";
        String password = "password";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            logger.info("Successful database connection.");
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }

        return connection;
    }
}