package TelegramBot.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConnection {
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
        ArrayList<String> dbConfig = DatabaseConfigReader.read();
        String connection = "jdbc:postgresql://" + dbConfig.get(0) + ":" + dbConfig.get(1) + "/" + dbConfig.get(2);
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(connection, dbConfig.get(3), dbConfig.get(4));
        return dbConnection;
    }

    public DatabaseTools getDatabaseTools() {
        return databaseTools;
    }

}
