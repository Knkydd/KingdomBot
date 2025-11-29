package telegrambot.utility;

import telegrambot.data.ConstantDB;

import java.util.Map;

public class ConstantResourcesForBuilds {
    public final static Map<String, Integer> LIST_LIMITS = Map.of(
            ConstantDB.USER_TOWN_HALL, 5,
            ConstantDB.USER_QUARRY, 6,
            ConstantDB.USER_SAWMILL, 4,
            ConstantDB.USER_FARM, 5,
            ConstantDB.USER_TRADE_BUILD, 5,
            ConstantDB.USER_BARRACKS, 4,
            ConstantDB.USER_MAGE_TOWER, 4,
            ConstantDB.USER_SHOOTING_RANGE, 4,
            ConstantDB.USER_CHAPEL_OF_LAST_HOPE, 4,
            ConstantDB.USER_CHURCH, 4
    );

    private final static Map<String, Integer> RESOURCES_FOR_TOWN_HALL_UPGRADE_TO_2 = Map.of(
            "Wood", 50,
            "Gold", 500,
            "Stone", 30,
            "Food", 0
    );
    private final static Map<String, Integer> RESOURCES_FOR_TOWN_HALL_UPGRADE_TO_3 = Map.of(
            "Wood", 100,
            "Gold", 700,
            "Stone", 70,
            "Food", 0
    );
    private final static Map<String, Integer> RESOURCES_FOR_TOWN_HALL_UPGRADE_TO_4 = Map.of(
            "Wood", 150,
            "Gold", 1000,
            "Stone", 120,
            "Food", 0
    );
    private final static Map<String, Integer> RESOURCES_FOR_TOWN_HALL_UPGRADE_TO_5 = Map.of(
            "Wood", 250,
            "Gold", 1500,
            "Stone", 200,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_QUARRY_BUILD = Map.of(
            "Wood", 30,
            "Gold", 500,
            "Stone", 20,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_TRADE_BUILD_BUILD = Map.of(   //калабур build_build (постройка_построить)
            "Wood", 20,
            "Gold", 500,
            "Stone", 10,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_MAGE_TOWN_BUILD = Map.of(
            "Wood", 100,
            "Gold", 1000,
            "Stone", 150,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_SHOOTING_RANGE_BUILD = Map.of(
            "Wood", 30,
            "Gold", 30,
            "Stone", 30,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_CHAPEL_OF_LAST_HOPE_BUILD = Map.of(
            "Wood", 40,
            "Gold", 400,
            "Stone", 25,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_CHURCH_BUILD = Map.of(
            "Wood", 40,
            "Gold", 800,
            "Stone", 100,
            "Food", 0
    );


    public final static Map<String, Integer> RESOURCES_FOR_QUARRY_UPGRADE_TO_2 = Map.of(
            "Wood", 60,
            "Gold", 1500,
            "Stone", 150,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_QUARRY_UPGRADE_TO_3 = Map.of(
            "Wood", 100,
            "Gold", 2000,
            "Stone", 200,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_QUARRY_UPGRADE_TO_4 = Map.of(
            "Wood", 120,
            "Gold", 2250,
            "Stone", 220,
            "Food", 0
    );

    public final static Map<String, Integer> RESOURCES_FOR_QUARRY_UPGRADE_TO_5 = Map.of(
            "Wood", 175,
            "Gold", 2500,
            "Stone", 250,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_QUARRY_UPGRADE_TO_6 = Map.of(
            "Wood", 200,
            "Gold", 3000,
            "Stone", 300,
            "Food", 0
    );

    public final static Map<String, Integer> RESOURCES_FOR_SAWMILL_UPGRADE_TO_2 = Map.of(
            "Wood", 80,
            "Gold", 1500,
            "Stone", 100,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_SAWMILL_UPGRADE_TO_3 = Map.of(
            "Wood", 110,
            "Gold", 1750,
            "Stone", 125,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_SAWMILL_UPGRADE_TO_4 = Map.of(
            "Wood", 140,
            "Gold", 2250,
            "Stone", 160,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_SAWMILL_UPGRADE_TO_5 = Map.of(
            "Wood", 170,
            "Gold", 2750,
            "Stone", 200,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_SAWMILL_UPGRADE_TO_6 = Map.of(
            "Wood", 225,
            "Gold", 3500,
            "Stone", 250,
            "Food", 0
    );

    public final static Map<String, Integer> RESOURCES_FOR_FARM_UPGRADE_TO_2 = Map.of(
            "Wood", 100,
            "Gold", 1200,
            "Stone", 80,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_FARM_UPGRADE_TO_3 = Map.of(
            "Wood", 125,
            "Gold", 1600,
            "Stone", 120,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_FARM_UPGRADE_TO_4 = Map.of(
            "Wood", 170,
            "Gold", 2000,
            "Stone", 150,
            "Food", 0
    );

    public final static Map<String, Integer> RESOURCES_FOR_FARM_UPGRADE_TO_5 = Map.of(
            "Wood", 225,
            "Gold", 3000,
            "Stone", 250,
            "Food", 0
    );

    public final static Map<String, Integer> RESOURCES_FOR_TRADE_BUILD_UPGRADE_TO_2 = Map.of(
            "Wood", 90,
            "Gold", 1000,
            "Stone", 75,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_TRADE_BUILD_UPGRADE_TO_3 = Map.of(
            "Wood", 130,
            "Gold", 1500,
            "Stone", 150,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_TRADE_BUILD_UPGRADE_TO_4 = Map.of(
            "Wood", 170,
            "Gold", 2000,
            "Stone", 180,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_TRADE_BUILD_UPGRADE_TO_5 = Map.of(
            "Wood", 200,
            "Gold", 3000,
            "Stone", 210,
            "Food", 0
    );

    public final static Map<String, Integer> RESOURCES_FOR_BARRACKS_UPGRADE_TO_2 = Map.of(
            "Wood", 110,
            "Gold", 1100,
            "Stone", 100,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_BARRACKS_UPGRADE_TO_3 = Map.of(
            "Wood", 150,
            "Gold", 1800,
            "Stone", 130,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_BARRACKS_UPGRADE_TO_4 = Map.of(
            "Wood", 200,
            "Gold", 2800,
            "Stone", 180,
            "Food", 0
    );

    public final static Map<String, Integer> RESOURCES_FOR_MAGE_TOWN_UPGRADE_TO_2 = Map.of(
            "Wood", 110,
            "Gold", 1100,
            "Stone", 100,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_MAGE_TOWN_UPGRADE_TO_3 = Map.of(
            "Wood", 150,
            "Gold", 1700,
            "Stone", 120,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_MAGE_TOWN_UPGRADE_TO_4 = Map.of(
            "Wood", 200,
            "Gold", 2500,
            "Stone", 180,
            "Food", 0
    );


    public final static Map<String, Integer> RESOURCES_FOR_SHOOTING_RANGE_UPGRADE_TO_2 = Map.of(
            "Wood", 110,
            "Gold", 1200,
            "Stone", 130,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_SHOOTING_RANGE_UPGRADE_TO_3 = Map.of(
            "Wood", 160,
            "Gold", 2200,
            "Stone", 150,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_SHOOTING_RANGE_UPGRADE_TO_4 = Map.of(
            "Wood", 225,
            "Gold", 3200,
            "Stone", 200,
            "Food", 0
    );

    public final static Map<String, Integer> RESOURCES_FOR_CHAPEL_OF_LAST_HOPE_UPGRADE_TO_2 = Map.of(
            "Wood", 100,
            "Gold", 1500,
            "Stone", 90,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_CHAPEL_OF_LAST_HOPE_UPGRADE_TO_3 = Map.of(
            "Wood", 150,
            "Gold", 2300,
            "Stone", 140,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_CHAPEL_OF_LAST_HOPE_UPGRADE_TO_4 = Map.of(
            "Wood", 220,
            "Gold", 3200,
            "Stone", 200,
            "Food", 0
    );

    public final static Map<String, Integer> RESOURCES_FOR_CHURCH_UPGRADE_TO_2 = Map.of(
            "Wood", 120,
            "Gold", 1400,
            "Stone", 100,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_CHURCH_UPGRADE_TO_3 = Map.of(
            "Wood", 160,
            "Gold", 2500,
            "Stone", 120,
            "Food", 0
    );
    public final static Map<String, Integer> RESOURCES_FOR_CHURCH_UPGRADE_TO_4 = Map.of(
            "Wood", 250,
            "Gold", 3500,
            "Stone", 200,
            "Food", 0
    );
    private final static Map<Integer, Map<String, Integer>> RESOURCES_FOR_QUARRY_UPGRADE = Map.of(
            1, RESOURCES_FOR_QUARRY_UPGRADE_TO_2,
            2, RESOURCES_FOR_QUARRY_UPGRADE_TO_3,
            3, RESOURCES_FOR_QUARRY_UPGRADE_TO_4,
            4, RESOURCES_FOR_QUARRY_UPGRADE_TO_5,
            5, RESOURCES_FOR_QUARRY_UPGRADE_TO_6
    );

    private final static Map<Integer, Map<String, Integer>> RESOURCES_FOR_TRADE_BUILD_UPGRADE = Map.of(
            1, RESOURCES_FOR_TRADE_BUILD_UPGRADE_TO_2,
            2, RESOURCES_FOR_TRADE_BUILD_UPGRADE_TO_3,
            3, RESOURCES_FOR_TRADE_BUILD_UPGRADE_TO_4,
            4, RESOURCES_FOR_TRADE_BUILD_UPGRADE_TO_5
    );

    private final static Map<Integer, Map<String, Integer>> RESOURCES_FOR_TOWN_HALL_UPGRADE = Map.of(
            1, RESOURCES_FOR_TOWN_HALL_UPGRADE_TO_2,
            2, RESOURCES_FOR_TOWN_HALL_UPGRADE_TO_3,
            3, RESOURCES_FOR_TOWN_HALL_UPGRADE_TO_4,
            4, RESOURCES_FOR_TOWN_HALL_UPGRADE_TO_5
    );

    private final static Map<Integer, Map<String, Integer>> RESOURCES_FOR_SAWMILL_UPGRADE = Map.of(
            1, RESOURCES_FOR_SAWMILL_UPGRADE_TO_2,
            2, RESOURCES_FOR_SAWMILL_UPGRADE_TO_3,
            3, RESOURCES_FOR_SAWMILL_UPGRADE_TO_4,
            4, RESOURCES_FOR_SAWMILL_UPGRADE_TO_5,
            5, RESOURCES_FOR_SAWMILL_UPGRADE_TO_6
    );
    private final static Map<Integer, Map<String, Integer>> RESOURCES_FOR_FARM_UPGRADE = Map.of(
            1, RESOURCES_FOR_FARM_UPGRADE_TO_2,
            2, RESOURCES_FOR_FARM_UPGRADE_TO_3,
            3, RESOURCES_FOR_FARM_UPGRADE_TO_4,
            4, RESOURCES_FOR_FARM_UPGRADE_TO_5
    );
    private final static Map<Integer, Map<String, Integer>> RESOURCES_FOR_BARRACKS_UPGRADE = Map.of(
            1, RESOURCES_FOR_BARRACKS_UPGRADE_TO_2,
            2, RESOURCES_FOR_BARRACKS_UPGRADE_TO_3,
            3, RESOURCES_FOR_BARRACKS_UPGRADE_TO_4
    );
    private final static Map<Integer, Map<String, Integer>> RESOURCES_FOR_MAGE_TOWER_UPGRADE = Map.of(
            1, RESOURCES_FOR_MAGE_TOWN_UPGRADE_TO_2,
            2, RESOURCES_FOR_MAGE_TOWN_UPGRADE_TO_3,
            3, RESOURCES_FOR_MAGE_TOWN_UPGRADE_TO_4
    );
    private final static Map<Integer, Map<String, Integer>> RESOURCES_FOR_SHOOTING_RANGE_UPGRADE = Map.of(
            1, RESOURCES_FOR_SHOOTING_RANGE_UPGRADE_TO_2,
            2, RESOURCES_FOR_SHOOTING_RANGE_UPGRADE_TO_3,
            3, RESOURCES_FOR_SHOOTING_RANGE_UPGRADE_TO_4
    );
    private final static Map<Integer, Map<String, Integer>> RESOURCES_FOR_CHAPEL_OF_LAST_HOPE_UPGRADE = Map.of(
            1, RESOURCES_FOR_CHAPEL_OF_LAST_HOPE_UPGRADE_TO_2,
            2, RESOURCES_FOR_CHAPEL_OF_LAST_HOPE_UPGRADE_TO_3,
            3, RESOURCES_FOR_CHAPEL_OF_LAST_HOPE_UPGRADE_TO_4
    );
    private final static Map<Integer, Map<String, Integer>> RESOURCES_FOR_CHURCH_UPGRADE = Map.of(
            1, RESOURCES_FOR_CHURCH_UPGRADE_TO_2,
            2, RESOURCES_FOR_CHURCH_UPGRADE_TO_3,
            3, RESOURCES_FOR_CHURCH_UPGRADE_TO_4
    );
    public final static Map<String, Map<String, Integer>> RESOURCES_FOR_BUILD = Map.of(
            ConstantDB.USER_QUARRY, RESOURCES_FOR_QUARRY_BUILD,
            ConstantDB.USER_TRADE_BUILD, RESOURCES_FOR_TRADE_BUILD_BUILD,
            ConstantDB.USER_MAGE_TOWER, RESOURCES_FOR_MAGE_TOWN_BUILD,
            ConstantDB.USER_CHURCH, RESOURCES_FOR_CHURCH_BUILD,
            ConstantDB.USER_SHOOTING_RANGE, RESOURCES_FOR_SHOOTING_RANGE_BUILD,
            ConstantDB.USER_CHAPEL_OF_LAST_HOPE, RESOURCES_FOR_CHAPEL_OF_LAST_HOPE_BUILD
    );

    public final static Map<String, Map<Integer, Map<String, Integer>>> RESOURCES_FOR_UPGRADE = Map.of(
            ConstantDB.USER_TOWN_HALL, RESOURCES_FOR_TOWN_HALL_UPGRADE,
            ConstantDB.USER_SAWMILL, RESOURCES_FOR_SAWMILL_UPGRADE,
            ConstantDB.USER_QUARRY, RESOURCES_FOR_QUARRY_UPGRADE,
            ConstantDB.USER_FARM, RESOURCES_FOR_FARM_UPGRADE,
            ConstantDB.USER_TRADE_BUILD, RESOURCES_FOR_TRADE_BUILD_UPGRADE,
            ConstantDB.USER_BARRACKS, RESOURCES_FOR_BARRACKS_UPGRADE,
            ConstantDB.USER_MAGE_TOWER, RESOURCES_FOR_MAGE_TOWER_UPGRADE,
            ConstantDB.USER_SHOOTING_RANGE, RESOURCES_FOR_SHOOTING_RANGE_UPGRADE,
            ConstantDB.USER_CHAPEL_OF_LAST_HOPE, RESOURCES_FOR_CHAPEL_OF_LAST_HOPE_UPGRADE,
            ConstantDB.USER_CHURCH, RESOURCES_FOR_CHURCH_UPGRADE
    );
}