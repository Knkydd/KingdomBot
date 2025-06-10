package TelegramBot.bot;

import TelegramBot.bot.logic.*;
import TelegramBot.data.ConstantDB;
import TelegramBot.utility.ConstantBuildUp;
import TelegramBot.utility.keyboard.ConstantKB;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Commands {
    private final Map<String, BiConsumer<Long, Integer>> commandsMap;
    private final NextMove nextMoveControl;
    private final MainMenu mainMenuControl;
    private final GameMenu gameMenuControl;
    private final Army armyMenuControl;
    private final Builds buildsMenuControl;
    private final AttackMenu attackMenuControl;

    public Commands(BotUtils botUtils) {
        commandsMap = new HashMap<>();
        nextMoveControl = new NextMove(botUtils);
        mainMenuControl = new MainMenu(botUtils);
        gameMenuControl = new GameMenu(botUtils);
        armyMenuControl = new Army(botUtils);
        buildsMenuControl = new Builds(botUtils);
        attackMenuControl = new AttackMenu(botUtils);

        //Начальный вызов начального меню
        commandsMap.put(ConstantKB.MAIN_MENU, mainMenuControl::setMainMenuInMSG);

        //Обработка начального меню
        commandsMap.put(ConstantKB.CALLBACK_PLAY_BUTTON, (chatID, messageID) ->
                mainMenuControl.mainMenuHandler(chatID, ConstantKB.CALLBACK_PLAY_BUTTON, messageID));
        commandsMap.put(ConstantKB.CALLBACK_LEADERBOARD_BUTTON, (chatID, messageID) ->
                mainMenuControl.mainMenuHandler(chatID, ConstantKB.CALLBACK_LEADERBOARD_BUTTON, messageID));

        //Обработка игрового меню
        commandsMap.put(ConstantKB.CALLBACK_BUILDS_BUTTON, (chatID, messageID) ->
                gameMenuControl.gameMenuHandler(chatID, ConstantKB.CALLBACK_BUILDS_BUTTON, messageID));
        commandsMap.put(ConstantKB.CALLBACK_ACTION_BUTTON, (chatID, messageID) ->
                gameMenuControl.gameMenuHandler(chatID, ConstantKB.CALLBACK_ACTION_BUTTON, messageID));
        commandsMap.put(ConstantKB.CALLBACK_ARMY_BUTTON, (chatID, messageID) ->
                gameMenuControl.gameMenuHandler(chatID, ConstantKB.CALLBACK_ARMY_BUTTON, messageID));

        //Обработка Действий
        commandsMap.put(ConstantKB.CALLBACK_NEXT_MOVE_BUTTON, (chatID, messageID) ->
                nextMoveControl.nextMoveHandler(chatID, ConstantKB.CALLBACK_NEXT_MOVE_BUTTON, messageID));
        commandsMap.put(ConstantKB.CALLBACK_MOVE_CHOP, (chatID, messageID) ->
                nextMoveControl.nextMoveHandler(chatID, ConstantKB.CALLBACK_MOVE_CHOP, messageID));
        commandsMap.put(ConstantKB.CALLBACK_MOVE_DIG, (chatID, messageID) ->
                nextMoveControl.nextMoveHandler(chatID, ConstantKB.CALLBACK_MOVE_DIG, messageID));
        commandsMap.put(ConstantKB.CALLBACK_MOVE_TRADE, (chatID, messageID) ->
                nextMoveControl.nextMoveHandler(chatID, ConstantKB.CALLBACK_MOVE_TRADE, messageID));
        commandsMap.put(ConstantKB.CALLBACK_MOVE_WORK_ON_FARM, (chatID, messageID) ->
                nextMoveControl.nextMoveHandler(chatID, ConstantKB.CALLBACK_MOVE_WORK_ON_FARM, messageID));
        commandsMap.put(ConstantKB.CALLBACK_ATTACK_BUTTON, (chatID, messageID) ->
                attackMenuControl.attackMenuHandler(chatID, ConstantKB.CALLBACK_ATTACK_BUTTON, messageID));

        //Обработка кнопки Атака(внутри меню атаки)
        commandsMap.put(ConstantKB.CALLBACK_ATTACK_ENEMY_BUTTON, (chatID, messageID) ->
                attackMenuControl.attackMenuHandler(chatID, ConstantKB.CALLBACK_ATTACK_ENEMY_BUTTON, messageID));

        //Обработка кнопки "Нанять"
        commandsMap.put(ConstantKB.CALLBACK_RECRUITING_BUTTON, (chatID, messageID) ->
                armyMenuControl.armyRecruitingHandler(chatID, ConstantKB.CALLBACK_RECRUITING_BUTTON, messageID));

        //Обработка кнопки найма войск
        commandsMap.put(ConstantDB.USER_WARRIOR_UNIT, (chatID, messageID) ->
                armyMenuControl.recruitingHandler(chatID, ConstantDB.USER_WARRIOR_UNIT, messageID));
        commandsMap.put(ConstantDB.USER_MAGE_UNIT, (chatID, messageID) ->
                armyMenuControl.recruitingHandler(chatID, ConstantDB.USER_MAGE_UNIT, messageID));
        commandsMap.put(ConstantDB.USER_ARCHER_UNIT, (chatID, messageID) ->
                armyMenuControl.recruitingHandler(chatID, ConstantDB.USER_ARCHER_UNIT, messageID));
        commandsMap.put(ConstantDB.USER_PALADIN_UNIT, (chatID, messageID) ->
                armyMenuControl.recruitingHandler(chatID, ConstantDB.USER_PALADIN_UNIT, messageID));
        commandsMap.put(ConstantDB.USER_HEALER_UNIT, (chatID, messageID) ->
                armyMenuControl.recruitingHandler(chatID, ConstantDB.USER_HEALER_UNIT, messageID));

        //Обработка кнопки построек
        commandsMap.put(ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON, (chatID, messageID) ->
                buildsMenuControl.buildsHandler(chatID, ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON, messageID));
        commandsMap.put(ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON, (chatID, messageID) ->
                buildsMenuControl.buildsHandler(chatID, ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON, messageID));

        //Обработка постройки зданий
        commandsMap.put(ConstantBuildUp.QUARRY_UPBUILD, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpbuild(chatID, ConstantBuildUp.QUARRY_UPBUILD, messageID));
        commandsMap.put(ConstantBuildUp.TRADE_BUILD_UPBUILD, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpbuild(chatID, ConstantBuildUp.TRADE_BUILD_UPGRADE, messageID));
        commandsMap.put(ConstantBuildUp.MAGE_TOWER_UPBUILD, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpbuild(chatID, ConstantBuildUp.MAGE_TOWER_UPBUILD, messageID));
        commandsMap.put(ConstantBuildUp.SHOOTING_RANGE_UPBUILD, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpbuild(chatID, ConstantBuildUp.SHOOTING_RANGE_UPBUILD, messageID));
        commandsMap.put(ConstantBuildUp.CHAPEL_OF_LAST_HOPE_UPBUILD, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpbuild(chatID, ConstantBuildUp.CHAPEL_OF_LAST_HOPE_UPBUILD, messageID));
        commandsMap.put(ConstantBuildUp.CHURCH_UPBUILD, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpbuild(chatID, ConstantBuildUp.CHURCH_UPBUILD, messageID));

        //Обработка улучшения зданий
        commandsMap.put(ConstantBuildUp.TOWN_HALL_UPGRADE, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpgrade(chatID, ConstantBuildUp.TOWN_HALL_UPGRADE, messageID));
        commandsMap.put(ConstantBuildUp.SAWMILL_UPGRADE, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpgrade(chatID, ConstantBuildUp.SAWMILL_UPGRADE, messageID));
        commandsMap.put(ConstantBuildUp.QUARRY_UPGRADE, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpgrade(chatID, ConstantBuildUp.QUARRY_UPGRADE, messageID));
        commandsMap.put(ConstantBuildUp.FARM_UPGRADE, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpgrade(chatID, ConstantBuildUp.FARM_UPGRADE, messageID));
        commandsMap.put(ConstantBuildUp.TRADE_BUILD_UPGRADE, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpgrade(chatID, ConstantBuildUp.TRADE_BUILD_UPGRADE, messageID));
        commandsMap.put(ConstantBuildUp.BARRACKS_UPGRADE, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpgrade(chatID, ConstantBuildUp.BARRACKS_UPGRADE, messageID));
        commandsMap.put(ConstantBuildUp.MAGE_TOWER_UPGRADE, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpgrade(chatID, ConstantBuildUp.MAGE_TOWER_UPGRADE, messageID));
        commandsMap.put(ConstantBuildUp.SHOOTING_RANGE_UPGRADE, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpgrade(chatID, ConstantBuildUp.SHOOTING_RANGE_UPGRADE, messageID));
        commandsMap.put(ConstantBuildUp.CHAPEL_OF_LAST_HOPE_UPGRADE, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpgrade(chatID, ConstantBuildUp.CHAPEL_OF_LAST_HOPE_UPGRADE, messageID));
        commandsMap.put(ConstantBuildUp.CHURCH_UPGRADE, (chatID, messageID) ->
                buildsMenuControl.buildsHandlerUpgrade(chatID, ConstantBuildUp.CHURCH_UPGRADE, messageID));

    }

    public BiConsumer<Long, Integer> getCommand(String command) {
        return commandsMap.get(command);
    }
}
