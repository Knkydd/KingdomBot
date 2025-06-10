package TelegramBot.bot.logic;

import TelegramBot.bot.BotUtils;
import TelegramBot.data.ConstantDB;
import TelegramBot.data.DatabaseTools;
import TelegramBot.utility.ConstantMessages;
import TelegramBot.utility.ConstantResourcesForArmy;
import TelegramBot.utility.EditMessage;
import TelegramBot.utility.MessageSender;
import TelegramBot.utility.keyboard.ConstantKB;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Army {
    private final DatabaseTools databaseTools;
    private final MessageSender messageSender;
    private final UserStateRepository userStateRepository;
    private final EditMessage editMessage;

    public Army(BotUtils botUtils) {
        this.databaseTools = botUtils.getDatabaseTools();
        this.messageSender = botUtils.getMessageSender();
        this.userStateRepository = botUtils.getUserStateRepository();
        this.editMessage = botUtils.getEditMessage();
    }

    public static Integer calculatingArmyPower(Map<String, Integer> builds, Map<String, Integer> army) {
        Integer warriorPower = army.get(ConstantDB.USER_WARRIOR_UNIT) * builds.get(ConstantDB.USER_BARRACKS) * 100;
        Integer magePower = army.get(ConstantDB.USER_MAGE_UNIT) * builds.get(ConstantDB.USER_MAGE_TOWER) * 200;
        Integer archerPower = army.get(ConstantDB.USER_ARCHER_UNIT) * builds.get(ConstantDB.USER_SHOOTING_RANGE) * 300;
        Integer paladinPower = army.get(ConstantDB.USER_PALADIN_UNIT) * builds.get(ConstantDB.USER_CHAPEL_OF_LAST_HOPE) * 500;
        Integer healerPower = army.get(ConstantDB.USER_HEALER_UNIT) * builds.get(ConstantDB.USER_CHURCH) * 700;
        Integer armyPower = warriorPower + magePower + archerPower + paladinPower + healerPower;
        return armyPower;
    }

    public static String armyMessage(Map<String, Integer> army, Integer armyPower) {
        StringBuilder message = new StringBuilder(ConstantMessages.ARMY_MESSAGE);
        String tempMessage = "";
        Set<String> armyKeys = army.keySet();
        Iterator iteratorArmyKeys = armyKeys.iterator();
        Integer i = 1;
        while (iteratorArmyKeys.hasNext()) {
            String temp = (String) iteratorArmyKeys.next();
            tempMessage = i + "          " + ConstantDB.accordanceListOfArmy.get(temp) +
                    "         Юнитов: " + army.get(temp) + "\n";
            message.append(tempMessage);
            i++;
        }
        message.append("\n");
        tempMessage = "Мощь вашей армии составляет:   " + armyPower;
        message.append(tempMessage);
        return message.toString();
    }

    public static Map<String, Integer> recruitingArmy(Map<String, Integer> army, String unit) {
        army.put(unit, army.get(unit) + 1);
        return army;
    }

    public static String recruitingMessage(Map<String, Integer> army, Integer Gold) {
        String message = ConstantMessages.QUESTION_ABOUT_RECRUITING;
        String tempMessage = "";
        Set<String> armyKeys = army.keySet();
        Integer i = 1;
        Iterator iteratorArmyKeys = armyKeys.iterator();
        while (iteratorArmyKeys.hasNext()) {
            String temp = (String) iteratorArmyKeys.next();
            tempMessage = String.format("%s     %s      %s\n", i, ConstantDB.accordanceListOfArmy.get(temp), ConstantResourcesForArmy.LIST_GOLD_FOR_ARMY.get(temp).get("Gold"));
            message += tempMessage;
            i++;
        }
        message += "\n";
        tempMessage = "Ваше золото:       " + Gold;
        message += tempMessage;
        return message;
    }

    public void armyRecruitingHandler(long chatID, String callbackData, Integer messageID) {

        userStateRepository.setState(chatID, ConstantKB.CALLBACK_RECRUITING_BUTTON);
        messageSender.send(chatID, editMessage.messageEdit(chatID, messageID, callbackData, recruitingMessage(databaseTools.getArmy(chatID), databaseTools.getResources(chatID).get(ConstantDB.USER_GOLD))));

    }

    public boolean checkRecruitingArmyOnBuilds(Map<String, Integer> builds, String unit) {
        if (unit.equals(ConstantDB.USER_WARRIOR_UNIT)) {
            if (builds.get(ConstantDB.USER_BARRACKS).equals(0)) {
                return false;
            }
        }
        if (unit.equals(ConstantDB.USER_MAGE_UNIT)) {
            if (builds.get(ConstantDB.USER_MAGE_TOWER).equals(0)) {
                return false;
            }
        }
        if (unit.equals(ConstantDB.USER_ARCHER_UNIT)) {
            if (builds.get(ConstantDB.USER_SHOOTING_RANGE).equals(0)) {
                return false;
            }
        }
        if (unit.equals(ConstantDB.USER_PALADIN_UNIT)) {
            if (builds.get(ConstantDB.USER_CHAPEL_OF_LAST_HOPE).equals(0)) {
                return false;
            }
        }
        if (unit.equals(ConstantDB.USER_HEALER_UNIT)) {
            if (builds.get(ConstantDB.USER_CHURCH).equals(0)) {
                return false;
            }
        }
        return true;
    }

    public void recruitingHandler(long chatID, String callbackData, Integer messageID) {
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_ARMY_BUTTON);

        Map<String, Integer> army = databaseTools.getArmy(chatID);
        Map<String, Integer> resources = databaseTools.getResources(chatID);
        Map<String, Integer> builds = databaseTools.getBuilds(chatID);

        if (checkRecruitingArmyOnBuilds(builds, callbackData)) {
            if (Resources.checkResourcesOnSpending(resources, ConstantResourcesForArmy.LIST_GOLD_FOR_ARMY.get(callbackData))) {

                Map<String, Integer> calculatingArmy = recruitingArmy(army, callbackData);
                Integer calculatingArmyPower = calculatingArmyPower(builds, calculatingArmy);
                databaseTools.setResources(chatID, Resources.updateResources(resources, ConstantResourcesForArmy.LIST_GOLD_FOR_ARMY.get(callbackData), 0));
                databaseTools.setArmy(chatID, calculatingArmy);
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.RECRUITING_UNIT_SUCCESSFUL));
                databaseTools.setArmyPower(chatID, calculatingArmyPower);

            } else {

                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.RECRUITING_UNIT_FAILED));

            }
        } else {
            messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.UNIT_BUILD_NOT_BUILT));
        }
    }
}
