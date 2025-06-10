package TelegramBot.utility.keyboard;

import TelegramBot.data.ConstantDB;
import TelegramBot.utility.ConstantBuildUp;

import java.util.LinkedHashMap;
import java.util.Map;

public class ButtonsMaps {
    public final static Map<String, String> startButtons = new LinkedHashMap<>();
    public final static Map<String, String> gameButtons = new LinkedHashMap<>();
    public final static Map<String, String> actionsButtons = new LinkedHashMap<>();
    public final static Map<String, String> oneBackButton = new LinkedHashMap<>();
    public final static Map<String, String> buildMenuButtons = new LinkedHashMap<>();
    public final static Map<String, String> upgradeButtons = new LinkedHashMap<>();
    public final static Map<String, String> upbuildButtons = new LinkedHashMap<>();
    public final static Map<String, String> armyButtons = new LinkedHashMap<>();
    public final static Map<String, String> recruitingButtons = new LinkedHashMap<>();
    public final static Map<String, String> attackButtons = new LinkedHashMap<>();

    static {
        //Кнопки для главного меню
        startButtons.put(ConstantKB.PLAY_BUTTON, ConstantKB.CALLBACK_PLAY_BUTTON);
        startButtons.put(ConstantKB.LEADERBOARD_BUTTON, ConstantKB.CALLBACK_LEADERBOARD_BUTTON);

        //Кнопки для игрового меню
        gameButtons.put(ConstantKB.BUILDS_BUTTON, ConstantKB.CALLBACK_BUILDS_BUTTON);
        gameButtons.put(ConstantKB.ACTIONS_BUTTON, ConstantKB.CALLBACK_ACTION_BUTTON);
        gameButtons.put(ConstantKB.ARMY_BUTTON, ConstantKB.CALLBACK_ARMY_BUTTON);
        gameButtons.put(ConstantKB.BACK_BUTTON, ConstantKB.CALLBACK_BACK_BUTTON);

        //Кнопки для меню строительства
        buildMenuButtons.put(ConstantKB.UPBUILD_BUILD_BUTTON, ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON);
        buildMenuButtons.put(ConstantKB.UPGRADE_BUILD_BUTTON, ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON);
        buildMenuButtons.put(ConstantKB.BACK_BUTTON, ConstantKB.CALLBACK_BACK_BUTTON);

        //Кнопка для меню с кнопкой "Назад"
        oneBackButton.put(ConstantKB.BACK_BUTTON, ConstantKB.CALLBACK_BACK_BUTTON);

        //Кнопки для меню атаки
        attackButtons.put(ConstantKB.ATTACK_ENEMY_BUTTON, ConstantKB.CALLBACK_ATTACK_ENEMY_BUTTON);
        attackButtons.put(ConstantKB.BACK_BUTTON, ConstantKB.CALLBACK_BACK_BUTTON);

        //Кнопки для меню действий
        actionsButtons.put(ConstantKB.NEXT_MOVE_BUTTON, ConstantKB.CALLBACK_NEXT_MOVE_BUTTON);
        actionsButtons.put(ConstantKB.MOVE_CHOP, ConstantKB.CALLBACK_MOVE_CHOP);
        actionsButtons.put(ConstantKB.MOVE_DIG, ConstantKB.CALLBACK_MOVE_DIG);
        actionsButtons.put(ConstantKB.BACK_BUTTON, ConstantKB.CALLBACK_BACK_BUTTON);
        actionsButtons.put(ConstantKB.MOVE_TRADE, ConstantKB.CALLBACK_MOVE_TRADE);
        actionsButtons.put(ConstantKB.MOVE_WORK_ON_FARM, ConstantKB.CALLBACK_MOVE_WORK_ON_FARM);
        actionsButtons.put(ConstantKB.ATTACK_BUTTON, ConstantKB.CALLBACK_ATTACK_BUTTON);

        //Кнопка для меню улучшения зданий
        upgradeButtons.put(ConstantDB.SHOOTING_RANGE, ConstantBuildUp.SHOOTING_RANGE_UPGRADE);
        upgradeButtons.put(ConstantDB.MAGE_TOWER, ConstantBuildUp.MAGE_TOWER_UPGRADE);
        upgradeButtons.put(ConstantDB.CHAPEL_OF_LAST_HOPE, ConstantBuildUp.CHAPEL_OF_LAST_HOPE_UPGRADE);
        upgradeButtons.put(ConstantDB.BARRACKS, ConstantBuildUp.BARRACKS_UPGRADE);
        upgradeButtons.put(ConstantDB.TOWN_HALL, ConstantBuildUp.TOWN_HALL_UPGRADE);
        upgradeButtons.put(ConstantDB.SAWMILL, ConstantBuildUp.SAWMILL_UPGRADE);
        upgradeButtons.put(ConstantDB.CHURCH, ConstantBuildUp.CHURCH_UPGRADE);
        upgradeButtons.put(ConstantDB.FARM, ConstantBuildUp.FARM_UPGRADE);
        upgradeButtons.put(ConstantDB.QUARRY, ConstantBuildUp.QUARRY_UPGRADE);
        upgradeButtons.put(ConstantDB.TRADE_BUILD, ConstantBuildUp.TRADE_BUILD_UPGRADE);
        upgradeButtons.put(ConstantKB.BACK_BUTTON, ConstantKB.CALLBACK_BACK_BUTTON);

        //Кнопки для меню постройки зданий
        upbuildButtons.put(ConstantDB.SHOOTING_RANGE, ConstantBuildUp.SHOOTING_RANGE_UPBUILD);
        upbuildButtons.put(ConstantDB.MAGE_TOWER, ConstantBuildUp.MAGE_TOWER_UPBUILD);
        upbuildButtons.put(ConstantDB.CHAPEL_OF_LAST_HOPE, ConstantBuildUp.CHAPEL_OF_LAST_HOPE_UPBUILD);
        upbuildButtons.put(ConstantDB.CHURCH, ConstantBuildUp.CHURCH_UPBUILD);
        upbuildButtons.put(ConstantDB.TRADE_BUILD, ConstantBuildUp.TRADE_BUILD_UPBUILD);
        upbuildButtons.put(ConstantDB.QUARRY, ConstantBuildUp.QUARRY_UPBUILD);
        upbuildButtons.put(ConstantKB.BACK_BUTTON, ConstantKB.CALLBACK_BACK_BUTTON);

        //Кнопки для меню армии
        armyButtons.put(ConstantKB.RECRUITING_BUTTON, ConstantKB.CALLBACK_RECRUITING_BUTTON);
        armyButtons.put(ConstantKB.BACK_BUTTON, ConstantKB.CALLBACK_BACK_BUTTON);

        //Кнопки для найма юнитов
        recruitingButtons.put(ConstantDB.WARRIOR_UNIT, ConstantDB.USER_WARRIOR_UNIT);
        recruitingButtons.put(ConstantDB.MAGE_UNIT, ConstantDB.USER_MAGE_UNIT);
        recruitingButtons.put(ConstantDB.ARCHER_UNIT, ConstantDB.USER_ARCHER_UNIT);
        recruitingButtons.put(ConstantDB.PALADIN_UNIT, ConstantDB.USER_PALADIN_UNIT);
        recruitingButtons.put(ConstantDB.HEALER_UNIT, ConstantDB.USER_HEALER_UNIT);
        recruitingButtons.put(ConstantKB.BACK_BUTTON, ConstantKB.CALLBACK_BACK_BUTTON);
    }
}
