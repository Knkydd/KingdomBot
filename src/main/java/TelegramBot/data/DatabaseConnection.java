package TelegramBot.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseConnection {
    private Connection dbConnection;
    private DatabaseTools databaseTools;

    public DatabaseConnection() {
        try {
            dbConnection = getDbConnection();
            databaseTools = new DatabaseTools(dbConnection);
        } catch (ClassNotFoundException | SQLException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    private Connection getDbConnection() throws ClassNotFoundException, SQLException, ClassCastException {
        HashMap<String, Object> dbConfig = DatabaseConfigReader.readingConfigDB();
        String connection = "jdbc:postgresql://" + dbConfig.get("host") + ":" + dbConfig.get("port") + "/" + dbConfig.get("name_db");
        Class.forName("org.postgresql.Driver");
        String user = (String) dbConfig.get("user");
        String password = (String) dbConfig.get("password");
        dbConnection = DriverManager.getConnection(connection, user, password);
        return dbConnection;
    }

    public DatabaseTools getDatabaseTools() {
        return databaseTools;
    }
}