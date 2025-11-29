package telegrambot.utility;

import java.util.Map;

public class ConstantReward {
    public final static Map<String, Integer> REWARD_FOR_LEVEL_1 = Map.of(
            "Wood", 40,
            "Gold", 500,
            "Stone", 30,
            "Food", 50
    );
    public final static Map<String, Integer> REWARD_FOR_LEVEL_2 = Map.of(
            "Wood", 100,
            "Gold", 1000,
            "Stone", 60,
            "Food", 50
    );
    public final static Map<String, Integer> REWARD_FOR_LEVEL_3 = Map.of(
            "Wood", 150,
            "Gold", 1500,
            "Stone", 90,
            "Food", 50
    );
    public final static Map<String, Integer> REWARD_FOR_LEVEL_4 = Map.of(
            "Wood", 200,
            "Gold", 2200,
            "Stone", 120,
            "Food", 200
    );
    public final static Map<String, Integer> REWARD_FOR_LEVEL_5 = Map.of(
            "Wood", 250,
            "Gold", 2600,
            "Stone", 140,
            "Food", 260
    );
    public final static Map<String, Integer> REWARD_FOR_LEVEL_6 = Map.of(
            "Wood", 300,
            "Gold", 3000,
            "Stone", 160,
            "Food", 300
    );
    public final static Map<String, Integer> REWARD_FOR_LEVEL_7 = Map.of(
            "Wood", 330,
            "Gold", 3400,
            "Stone", 180,
            "Food", 320
    );
    public final static Map<String, Integer> REWARD_FOR_LEVEL_8 = Map.of(
            "Wood", 300,
            "Gold", 4000,
            "Stone", 200,
            "Food", 359
    );
    public final static Map<String, Integer> REWARD_FOR_LEVEL_9 = Map.of(
            "Wood", 350,
            "Gold", 4500,
            "Stone", 250,
            "Food", 400
    );
    public final static Map<String, Integer> REWARD_FOR_LEVEL_10 = Map.of(
            "Wood", 400,
            "Gold", 5000,
            "Stone", 300,
            "Food", 505
    );

    public final static Map<Integer, Map<String, Integer>> REWARD_FOR_VICTORY_ATTACK = Map.of(
            1, REWARD_FOR_LEVEL_1,
            2, REWARD_FOR_LEVEL_2,
            3, REWARD_FOR_LEVEL_3,
            4, REWARD_FOR_LEVEL_4,
            5, REWARD_FOR_LEVEL_5,
            6, REWARD_FOR_LEVEL_6,
            7, REWARD_FOR_LEVEL_7,
            8, REWARD_FOR_LEVEL_8,
            9, REWARD_FOR_LEVEL_9,
            10, REWARD_FOR_LEVEL_10
    );

}
