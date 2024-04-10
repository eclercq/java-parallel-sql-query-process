import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Thread;

class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = getDatabaseConnection();
        System.out.println("Wait for database initialization...");
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
                        System.out.println("Database initialized! (" + rowCount + " lines)");
                    } else {
                        Thread.sleep(5000);
                    }
                }
                resultSet.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        connection.close();

        
    }

    public static Connection getDatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du pilote JDBC : " + e.getMessage());
        }

        String url = "jdbc:postgresql://large-data-process-database:5432/db";
        String user = "postgres";
        String password = "password";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie à la base de données PostgreSQL !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }

        return connection;
    }
}