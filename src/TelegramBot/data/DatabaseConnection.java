package TelegramBot.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection extends Config {
    private Connection dbConnection;
    private DatabaseTools databaseTools;

    public DatabaseConnection() {
        try {
            dbConnection = getDbConnection();
            databaseTools = new DatabaseTools(dbConnection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connection = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(connection, dbUser, dbPassword);
        return dbConnection;
    }

    public DatabaseTools getDatabaseTools() {
        return databaseTools;
    }

}
