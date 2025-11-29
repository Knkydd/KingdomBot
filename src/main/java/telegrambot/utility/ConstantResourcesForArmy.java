package telegrambot.utility;

import telegrambot.data.ConstantDB;

import java.util.Map;

public class ConstantResourcesForArmy {
    public final static Map<String, Integer> WARRIOR_GOLD_RECRUIT = Map.of(
            "Wood", 0,
            "Gold", 250,
            "Stone", 0,
            "Food", 0
    );
    public final static Map<String, Integer> MAGE_GOLD_RECRUIT = Map.of(
            "Wood", 0,
            "Gold", 300,
            "Stone", 0,
            "Food", 0
    );
    public final static Map<String, Integer> ARCHER_GOLD_RECRUIT = Map.of(
            "Wood", 0,
            "Gold", 400,
            "Stone", 0,
            "Food", 0
    );
    public final static Map<String, Integer> PALADIN_GOLD_RECRUIT = Map.of(
            "Wood", 0,
            "Gold", 500,
            "Stone", 0,
            "Food", 0
    );

    public final static Map<String, Integer> HEALER_GOLD_RECRUIT = Map.of(
            "Wood", 0,
            "Gold", 600,
            "Stone", 0,
            "Food", 0
    );

    public final static Map<String, Map<String, Integer>> LIST_GOLD_FOR_ARMY = Map.of(
            ConstantDB.USER_WARRIOR_UNIT, WARRIOR_GOLD_RECRUIT,
            ConstantDB.USER_MAGE_UNIT, MAGE_GOLD_RECRUIT,
            ConstantDB.USER_ARCHER_UNIT, ARCHER_GOLD_RECRUIT,
            ConstantDB.USER_PALADIN_UNIT, PALADIN_GOLD_RECRUIT,
            ConstantDB.USER_HEALER_UNIT, HEALER_GOLD_RECRUIT
    );
}
