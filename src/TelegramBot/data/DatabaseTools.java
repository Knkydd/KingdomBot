package TelegramBot.data;

import java.sql.*;
import java.util.*;

public class DatabaseTools {
    private final Connection dbConnection;

    public DatabaseTools(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    //Регистрация пользователя
    public void registrationUser(long chatID) {
        String insertRegistration = """
                Begin;
                Insert Into users(chatID) values(?);
                Insert into builds(chatID) values(?);
                Insert into resources(chatID) values(?);
                Insert into army(chatID) values(?);
                Commit;
                """;
        try (PreparedStatement statement = dbConnection.prepareStatement(insertRegistration)) {

            statement.setLong(1, chatID);
            statement.setLong(2, chatID);
            statement.setLong(3, chatID);
            statement.setLong(4, chatID);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Проверка, зарегистрирован ли пользователь
    public boolean isRegistered(long chatID) {
        String insertRegister = "Select chatID from users where chatID=?";
        try (PreparedStatement statement = dbConnection.prepareStatement(insertRegister)) {

            statement.setLong(1, chatID);

            if (statement.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Метод получения ресурсов
    public Map<String, Integer> getResources(long chatID) {
        String getterResources = "Select Food, Gold, Stone, Wood from resources where chatID=?";
        HashMap<String, Integer> resources = new HashMap<>(4);

        try (PreparedStatement statement = dbConnection.prepareStatement(getterResources)) {

            statement.setLong(1, chatID);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    resources.put("Wood", result.getInt("Wood"));
                    resources.put("Gold", result.getInt("Gold"));
                    resources.put("Food", result.getInt("Food"));
                    resources.put("Stone", result.getInt("Stone"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resources;
    }

    //Метод обновления ресурсов
    public void setResources(long chatID, Map<String, Integer> resources) {
        String insertResources = """
                Update resources Set Wood = ?, 
                Gold = ?, 
                Food = ?, 
                Stone = ? where chatID = ?
                """;
        try (PreparedStatement statement = dbConnection.prepareStatement(insertResources)) {
            statement.setInt(1, resources.get("Wood"));
            statement.setInt(2, resources.get("Gold"));
            statement.setInt(3, resources.get("Food"));
            statement.setInt(4, resources.get("Stone"));
            statement.setLong(5, chatID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Пока что не нужно, но это метод получения таблицы лидеров
//    public Map<String, Integer> getLeaderboard() {
//        ResultSet resultSet = null;
//        String getterUsername = "SELECT * FROM " +
//                ConstantDB.TABLE_USERS +
//                " ORDER BY " +
//                ConstantDB.USER_ARMY_POWER +
//                " DESC";
//        int i = 0;
//        Map<String, Integer> leaderboard = new LinkedHashMap<>();
//        try (Statement statement = dbConnection.createStatement()) {
//            resultSet = statement.executeQuery(getterUsername);
//            while (resultSet.next() && i != 6) {
//                leaderboard.put(resultSet.getString(ConstantDB.USER_NAME), resultSet.getInt(ConstantDB.USER_ARMY_POWER));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return leaderboard;
//    }

    //Получение армии(количества воинов)
    public Map<String, Integer> getArmy(long chatID) {
        String getterArmy = "Select * from army where chatID = ?";
        HashMap<String, Integer> army = new HashMap<>();
        try (PreparedStatement statement = dbConnection.prepareStatement(getterArmy)) {
            statement.setLong(1, chatID);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    army.put(ConstantDB.USER_WARRIOR_UNIT, result.getInt(ConstantDB.USER_WARRIOR_UNIT));
                    army.put(ConstantDB.USER_MAGE_UNIT, result.getInt(ConstantDB.USER_MAGE_UNIT));
                    army.put(ConstantDB.USER_ARCHER_UNIT, result.getInt(ConstantDB.USER_ARCHER_UNIT));
                    army.put(ConstantDB.USER_PALADIN_UNIT, result.getInt(ConstantDB.USER_PALADIN_UNIT));
                    army.put(ConstantDB.USER_HEALER_UNIT, result.getInt(ConstantDB.USER_HEALER_UNIT));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return army;
    }

    //Передает изменения юнита
    public void setArmy(long chatID, String unit, Integer newCountUnit) {
        String insertArmy = "Update army set ? = ? where chatID = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(insertArmy)) {
            statement.setString(1, unit);
            statement.setInt(2, newCountUnit);
            statement.setLong(3, chatID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Обновление силы армии
    public void setArmyPower(long chatID, Integer armyPower) {
        String insertArmyPower = "Update army set armyPower = ? where chatID = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(insertArmyPower)) {
            statement.setInt(1, armyPower);
            statement.setLong(2, chatID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Получение силы армии
    public Integer getArmyPower(long chatID) {
        String getterArmyPower = "Select armyPower from army where chatID = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(getterArmyPower)) {
            statement.setLong(1, chatID);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("armyPower");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //Получение уровня атаки, на котором находится пользователь
    public Integer getCurrentAttackLevel(long chatID) {
        String getterAttackLevel = "Select attackLevel from users where chatID = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(getterAttackLevel)) {
            statement.setLong(1, chatID);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("attackLevel");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Обновление уровня атаки, на котором находится пользователь
    public void setLevelAttack(long chatID, Integer currentLevel) {
        String insertAttackLevel = "Update users set attackLevel = ? where chatID = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(insertAttackLevel)) {
            statement.setInt(1, currentLevel);
            statement.setLong(2, chatID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Получение уровней построек
    public Map<String, Integer> getBuilds(long chatID) {
        String getterBuilds = "Select * from builds where chatID = ?";
        HashMap<String, Integer> builds = new HashMap<>();
        try (PreparedStatement statement = dbConnection.prepareStatement(getterBuilds)) {
            statement.setLong(1, chatID);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    builds.put(ConstantDB.USER_TOWN_HALL, result.getInt(ConstantDB.USER_TOWN_HALL));
                    builds.put(ConstantDB.USER_SAWMILL, result.getInt(ConstantDB.USER_SAWMILL));
                    builds.put(ConstantDB.USER_QUARRY, result.getInt(ConstantDB.USER_QUARRY));
                    builds.put(ConstantDB.USER_FARM, result.getInt(ConstantDB.USER_FARM));
                    builds.put(ConstantDB.USER_TRADE_BUILD, result.getInt(ConstantDB.USER_TRADE_BUILD));
                    builds.put(ConstantDB.USER_BARRACKS, result.getInt(ConstantDB.USER_BARRACKS));
                    builds.put(ConstantDB.USER_MAGE_TOWER, result.getInt(ConstantDB.USER_MAGE_TOWER));
                    builds.put(ConstantDB.USER_SHOOTING_RANGE, result.getInt(ConstantDB.USER_SHOOTING_RANGE));
                    builds.put(ConstantDB.USER_CHAPEL_OF_LAST_HOPE, result.getInt(ConstantDB.USER_CHAPEL_OF_LAST_HOPE));
                    builds.put(ConstantDB.USER_CHURCH, result.getInt(ConstantDB.USER_CHURCH));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return builds;
    }

    //Передает новый уровень здания
    public void setBuilds(long chatID, String build, Integer newBuildLevel) {
        String insertBuild = "Update builds set ? = ? where chatID = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(insertBuild)){
            statement.setString(1,build);
            statement.setInt(2, newBuildLevel);
            statement.setLong(3, chatID);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
