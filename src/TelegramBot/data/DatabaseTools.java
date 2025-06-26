package TelegramBot.data;

import java.sql.*;
import java.util.*;

public class DatabaseTools extends Config {
    private final Connection dbConnection;

    public DatabaseTools(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void registrationUser(long chatID) {
        String insertRegistration = "BEGIN;" +
                "INSERT INTO " + ConstantDB.TABLE_USERS + "(" +
                ConstantDB.USER_ID + ", " + ConstantDB.USER_NAME +
                ") VALUES(" + chatID + "');" +
                "INSERT INTO " + ConstantDB.TABLE_ARMY + "(" +
                ConstantDB.USER_ID + ") VALUES(" + chatID + ");" +
                "INSERT INTO " + ConstantDB.TABLE_RESOURCES + "(" +
                ConstantDB.USER_ID + ") VALUES(" + chatID + ");" +
                "INSERT INTO " + ConstantDB.TABLE_BUILDS + "(" +
                ConstantDB.USER_ID + ") VALUES(" + chatID + ");" +
                "COMMIT;";
        try (Statement statement = dbConnection.createStatement()) {
            statement.executeUpdate(insertRegistration);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isRegistered(long chatID) {
        ResultSet result = null;
        String regString = String.format("SELECT %s FROM %s where %s=%s", ConstantDB.USER_ID, ConstantDB.TABLE_USERS, ConstantDB.USER_ID, chatID);
        try (Statement statement = dbConnection.createStatement()) {
            result = statement.executeQuery(regString);
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<String, Integer> getResources(long chatID) {
        Map<String, Integer> resources = new HashMap<>();
        ResultSet resultSet = null;
        String getterResources = "SELECT * FROM " + ConstantDB.TABLE_RESOURCES + " WHERE " + ConstantDB.USER_ID + "=" + chatID;
        try (Statement statement = dbConnection.createStatement()) {
            resultSet = statement.executeQuery(getterResources);
            while (resultSet.next()) {
                resources.put("Wood", resultSet.getInt("Wood"));
                resources.put("Gold", resultSet.getInt("Gold"));
                resources.put("Food", resultSet.getInt("Food"));
                resources.put("Stone", resultSet.getInt("Stone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resources;
    }

    public void setResources(long chatID, Map<String, Integer> resources) {
        String insertResources = "UPDATE " + ConstantDB.TABLE_RESOURCES +
                " SET " +
                ConstantDB.USER_GOLD + "=" + resources.get("Gold") + ", " +
                ConstantDB.USER_WOOD + "=" + resources.get("Wood") + ", " +
                ConstantDB.USER_FOOD + "=" + resources.get("Food") + ", " +
                ConstantDB.USER_STONE + "=" + resources.get("Stone") +
                " WHERE " + ConstantDB.USER_ID + "=" + chatID;
        try (Statement statement = dbConnection.createStatement()) {
            statement.executeUpdate(insertResources);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getLeaderboard() {
        ResultSet resultSet = null;
        String getterUsername = "SELECT * FROM " +
                ConstantDB.TABLE_USERS +
                " ORDER BY " +
                ConstantDB.USER_ARMY_POWER +
                " DESC";
        int i = 0;
        Map<String, Integer> leaderboard = new LinkedHashMap<>();
        try (Statement statement = dbConnection.createStatement()) {
            resultSet = statement.executeQuery(getterUsername);
            while (resultSet.next() && i != 6) {
                leaderboard.put(resultSet.getString(ConstantDB.USER_NAME), resultSet.getInt(ConstantDB.USER_ARMY_POWER));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }

    public Map<String, Integer> getArmy(long chatID) {
        Map<String, Integer> army = new HashMap<>();
        ResultSet resultSet = null;
        String getterArmy = "SELECT * FROM " +
                ConstantDB.TABLE_ARMY +
                " WHERE " +
                ConstantDB.USER_ID +
                "=" +
                chatID;
        try (Statement statement = dbConnection.createStatement()) {
            resultSet = statement.executeQuery(getterArmy);
            while (resultSet.next()) {
                army.put(ConstantDB.USER_WARRIOR_UNIT, resultSet.getInt(ConstantDB.USER_WARRIOR_UNIT));
                army.put(ConstantDB.USER_MAGE_UNIT, resultSet.getInt(ConstantDB.USER_MAGE_UNIT));
                army.put(ConstantDB.USER_ARCHER_UNIT, resultSet.getInt(ConstantDB.USER_ARCHER_UNIT));
                army.put(ConstantDB.USER_PALADIN_UNIT, resultSet.getInt(ConstantDB.USER_PALADIN_UNIT));
                army.put(ConstantDB.USER_HEALER_UNIT, resultSet.getInt(ConstantDB.USER_HEALER_UNIT));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return army;
    }

    public void setArmy(long chatID, Map<String, Integer> army) {
        String insertArmy = "UPDATE " + ConstantDB.TABLE_ARMY +
                " SET " +
                ConstantDB.USER_WARRIOR_UNIT + "=" + army.get("warriorUnit") + ", " +
                ConstantDB.USER_MAGE_UNIT + "=" + army.get("mageUnit") + ", " +
                ConstantDB.USER_ARCHER_UNIT + "=" + army.get("archerUnit") + ", " +
                ConstantDB.USER_PALADIN_UNIT + "=" + army.get("paladinUnit") + ", " +
                ConstantDB.USER_HEALER_UNIT + "=" + army.get("healerUnit") +
                " WHERE " + ConstantDB.USER_ID + "=" + chatID;
        try (Statement statement = dbConnection.createStatement()) {
            statement.executeUpdate(insertArmy);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setArmyPower(long chatID, Integer armyPower) {
        String insertArmyPower = "UPDATE " +
                ConstantDB.TABLE_USERS +
                " SET " +
                ConstantDB.USER_ARMY_POWER +
                "=" +
                armyPower +
                " WHERE " +
                ConstantDB.USER_ID +
                "=" +
                chatID;
        try (Statement statement = dbConnection.createStatement()) {
            statement.executeUpdate(insertArmyPower);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getCurrentAttackLevel(long chatID) {
        Integer currentAttackLevel = 1;
        ResultSet resultSet = null;
        String getterAttackLevel = "SELECT " +
                ConstantDB.USER_LEVEL_ATTACK +
                " FROM " +
                ConstantDB.TABLE_ARMY +
                " WHERE " +
                ConstantDB.USER_ID +
                "=" +
                chatID;
        try (Statement statement = dbConnection.createStatement()) {
            resultSet = statement.executeQuery(getterAttackLevel);
            while (resultSet.next()) {
                currentAttackLevel = resultSet.getInt(ConstantDB.USER_LEVEL_ATTACK);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentAttackLevel;
    }

    public void setCurrentLevelAttack(long chatID, Integer currentLevel) {
        String insertLevelAttack = "UPDATE " +
                ConstantDB.TABLE_ARMY +
                " SET " +
                ConstantDB.USER_LEVEL_ATTACK +
                "=" +
                currentLevel +
                " WHERE " +
                ConstantDB.USER_ID +
                "=" +
                chatID;
        try (Statement statement = dbConnection.createStatement()) {
            statement.executeUpdate(insertLevelAttack);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getArmyPower(long chatID) {
        Integer armyPower = 0;
        ResultSet resultSet = null;
        String getterArmyPower = "SELECT " +
                ConstantDB.USER_ARMY_POWER +
                " FROM " +
                ConstantDB.TABLE_USERS +
                " WHERE " +
                ConstantDB.USER_ID +
                "=" +
                chatID;
        try (Statement statement = dbConnection.createStatement()) {
            resultSet = statement.executeQuery(getterArmyPower);
            if (resultSet.next()) {
                armyPower = resultSet.getInt(ConstantDB.USER_ARMY_POWER);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return armyPower;
    }

    public Map<String, Integer> getBuilds(long chatID) {
        Map<String, Integer> builds = new HashMap<>();
        ResultSet resultSet = null;
        String getterBuilds = "SELECT * FROM " +
                ConstantDB.TABLE_BUILDS +
                " WHERE " +
                ConstantDB.USER_ID +
                "=" +
                chatID;
        try (Statement statement = dbConnection.createStatement()) {
            resultSet = statement.executeQuery(getterBuilds);
            while (resultSet.next()) {
                builds.put(ConstantDB.USER_TOWN_HALL, resultSet.getInt(ConstantDB.USER_TOWN_HALL));
                builds.put(ConstantDB.USER_SAWMILL, resultSet.getInt(ConstantDB.USER_SAWMILL));
                builds.put(ConstantDB.USER_QUARRY, resultSet.getInt(ConstantDB.USER_QUARRY));
                builds.put(ConstantDB.USER_FARM, resultSet.getInt(ConstantDB.USER_FARM));
                builds.put(ConstantDB.USER_TRADE_BUILD, resultSet.getInt(ConstantDB.USER_TRADE_BUILD));
                builds.put(ConstantDB.USER_BARRACKS, resultSet.getInt(ConstantDB.USER_BARRACKS));
                builds.put(ConstantDB.USER_MAGE_TOWER, resultSet.getInt(ConstantDB.USER_MAGE_TOWER));
                builds.put(ConstantDB.USER_SHOOTING_RANGE, resultSet.getInt(ConstantDB.USER_SHOOTING_RANGE));
                builds.put(ConstantDB.USER_CHAPEL_OF_LAST_HOPE, resultSet.getInt(ConstantDB.USER_CHAPEL_OF_LAST_HOPE));
                builds.put(ConstantDB.USER_CHURCH, resultSet.getInt(ConstantDB.USER_CHURCH));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return builds;
    }

    public void setBuilds(long chatID, Map<String, Integer> builds) {
        String insertBuilds = "UPDATE " +
                ConstantDB.TABLE_BUILDS +
                " SET " +
                ConstantDB.USER_QUARRY + "=" + builds.get(ConstantDB.USER_QUARRY) + ", " +
                ConstantDB.USER_TOWN_HALL + "=" + builds.get(ConstantDB.USER_TOWN_HALL) + ", " +
                ConstantDB.USER_FARM + "=" + builds.get(ConstantDB.USER_FARM) + ", " +
                ConstantDB.USER_SAWMILL + "=" + builds.get(ConstantDB.USER_SAWMILL) + ", " +
                ConstantDB.USER_TRADE_BUILD + "=" + builds.get(ConstantDB.USER_TRADE_BUILD) + ", " +
                ConstantDB.USER_BARRACKS + "=" + builds.get(ConstantDB.USER_BARRACKS) + ", " +
                ConstantDB.USER_MAGE_TOWER + "=" + builds.get(ConstantDB.USER_MAGE_TOWER) + ", " +
                ConstantDB.USER_SHOOTING_RANGE + "=" + builds.get(ConstantDB.USER_SHOOTING_RANGE) + ", " +
                ConstantDB.USER_CHAPEL_OF_LAST_HOPE + "=" + builds.get(ConstantDB.USER_CHAPEL_OF_LAST_HOPE) + ", " +
                ConstantDB.USER_CHURCH + "=" + builds.get(ConstantDB.USER_CHURCH) +
                " WHERE " +
                ConstantDB.USER_ID + "=" + chatID;

        try (Statement statement = dbConnection.createStatement()) {
            statement.executeUpdate(insertBuilds);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
