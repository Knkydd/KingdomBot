package telegrambot.data;

import java.util.Map;

public class ConstantDB {

    public final static String TABLE_USERS = "users";
    public final static String TABLE_BUILDS = "builds";
    public final static String TABLE_RESOURCES = "resources";
    public final static String TABLE_ARMY = "army";

    public final static String USER_ID = "chatID";
    public final static String USER_NAME = "username";
    public final static String USER_LEVEL_ATTACK = "attackLevel";

    public final static String USER_WOOD = "Wood";
    public final static String USER_GOLD = "Gold";
    public final static String USER_FOOD = "Food";
    public final static String USER_STONE = "Stone";

    public final static String USER_TOWN_HALL = "townHall";
    public final static String USER_SAWMILL = "sawmill";
    public final static String USER_QUARRY = "quarry";
    public final static String USER_FARM = "farm";
    public final static String USER_TRADE_BUILD = "tradeBuild";
    public final static String USER_BARRACKS = "barracks";
    public final static String USER_MAGE_TOWER = "mageTower";
    public final static String USER_SHOOTING_RANGE = "shootingRange";
    public final static String USER_CHAPEL_OF_LAST_HOPE = "chapelOfLastHope";
    public final static String USER_CHURCH = "church";

    public final static String USER_ARMY_POWER = "armyPower";
    public final static String USER_WARRIOR_UNIT = "warriorUnit";
    public final static String USER_MAGE_UNIT = "mageUnit";
    public final static String USER_ARCHER_UNIT = "archerUnit";
    public final static String USER_PALADIN_UNIT = "paladinUnit";
    public final static String USER_HEALER_UNIT = "healerUnit";

    public final static String TOWN_HALL = "Ратуша";
    public final static String SAWMILL = "Лесопилка";
    public final static String QUARRY = "Карьер";
    public final static String FARM = "Ферма";
    public final static String TRADE_BUILD = "Торговый дом";
    public final static String BARRACKS = "Барраки";
    public final static String MAGE_TOWER = "Башня магов";
    public final static String SHOOTING_RANGE = "Стрельбище";
    public final static String CHAPEL_OF_LAST_HOPE = "Часовня последней надежды";
    public final static String CHURCH = "Церковь";

    public final static String WARRIOR_UNIT = "Воин";
    public final static String MAGE_UNIT = "Маг";
    public final static String ARCHER_UNIT = "Лучник";
    public final static String PALADIN_UNIT = "Паладин";
    public final static String HEALER_UNIT = "Целитель";

    public final static Map<String, String> accordanceListOfBuilds = Map.of(
            USER_TOWN_HALL, TOWN_HALL,
            USER_SAWMILL, SAWMILL,
            USER_QUARRY, QUARRY,
            USER_FARM, FARM,
            USER_TRADE_BUILD, TRADE_BUILD,
            USER_BARRACKS, BARRACKS,
            USER_MAGE_TOWER, MAGE_TOWER,
            USER_SHOOTING_RANGE, SHOOTING_RANGE,
            USER_CHAPEL_OF_LAST_HOPE, CHAPEL_OF_LAST_HOPE,
            USER_CHURCH, CHURCH
    );
    public final static Map<String, String> accordanceListOfArmy = Map.of(
            USER_WARRIOR_UNIT, WARRIOR_UNIT,
            USER_MAGE_UNIT, MAGE_UNIT,
            USER_ARCHER_UNIT, ARCHER_UNIT,
            USER_PALADIN_UNIT, PALADIN_UNIT,
            USER_HEALER_UNIT, HEALER_UNIT
    );
}
