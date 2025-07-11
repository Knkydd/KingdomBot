package TelegramBot.bot.logic;

import TelegramBot.bot.BotUtils;
import TelegramBot.data.ConstantDB;
import TelegramBot.data.DatabaseTools;
import TelegramBot.utility.ConstantMessages;
import TelegramBot.utility.ConstantResourcesForArmy;
import TelegramBot.utility.EditMessage;
import TelegramBot.utility.MessageSender;
import TelegramBot.utility.keyboard.ConstantKB;

import java.util.Map;

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

    /*
    Подсчет мощи армии по формуле sum(v), где v - мощь юнита

    Подсчет мощи юнита по формуле U * B * Const, где
    U - Количество определенного типа юнита
    B - уровень здания, к которому относится юнит
    Const - константа, у всех по разному
    */
    private Integer calculatingArmyPower(Map<String, Integer> builds, Map<String, Integer> army) {
        Integer warriorPower = army.get(ConstantDB.USER_WARRIOR_UNIT)
                * builds.get(ConstantDB.USER_BARRACKS) * 100;
        Integer magePower = army.get(ConstantDB.USER_MAGE_UNIT)
                * builds.get(ConstantDB.USER_MAGE_TOWER) * 200;
        Integer archerPower = army.get(ConstantDB.USER_ARCHER_UNIT)
                * builds.get(ConstantDB.USER_SHOOTING_RANGE) * 300;
        Integer paladinPower = army.get(ConstantDB.USER_PALADIN_UNIT)
                * builds.get(ConstantDB.USER_CHAPEL_OF_LAST_HOPE) * 500;
        Integer healerPower = army.get(ConstantDB.USER_HEALER_UNIT)
                * builds.get(ConstantDB.USER_CHURCH) * 700;
        return warriorPower + magePower + archerPower + paladinPower + healerPower;
    }

    //Сообщение в меню армии(показывает кто есть)
    public static String armyMessage(Map<String, Integer> army, Integer armyPower) {
        StringBuilder message = new StringBuilder(
                ConstantMessages.ARMY_MESSAGE).append('\n');

        int counter = 1;

        for (Map.Entry<String, Integer> entry : army.entrySet()) {
            message.append(counter++)
                    .append("          ")
                    .append(ConstantDB.accordanceListOfArmy.get(entry.getKey()))
                    .append("         Юнитов: ")
                    .append(entry.getValue())
                    .append('\n');
        }

        return message.append("\nМощь вашей армии составляет:   ")
                .append(armyPower)
                .toString();
    }

    //Рекрутинг войск
    private Map<String, Integer> recruitingArmy(Map<String, Integer> army, String unit) {
        army.put(unit, army.get(unit) + 1);
        return army;
    }

    //Сообщение меню рекрутинга войск
    private String recruitingMessage(Map<String, Integer> army, Integer Gold) {
        StringBuilder message = new StringBuilder(
                ConstantMessages.QUESTION_ABOUT_RECRUITING).append('\n');

        int counter = 1;

        for (Map.Entry<String, Integer> entry : army.entrySet()) {
            String unitName = ConstantDB.accordanceListOfArmy.get(entry.getKey());
            int unitCost = ConstantResourcesForArmy.LIST_GOLD_FOR_ARMY.get(entry.getKey()).get("Gold");

            message.append(String.format("%d     %s      %d%n", counter++, unitName, unitCost));
        }

        return message.append("\nВаше золото:       ")
                .append(Gold)
                .toString();
    }

    public void armyRecruitingHandler(long chatID, String callbackData, Integer messageID) {

        userStateRepository.setState(chatID, ConstantKB.CALLBACK_RECRUITING_BUTTON);
        messageSender.send(chatID, editMessage.messageEdit(chatID, messageID, callbackData, recruitingMessage(databaseTools.getArmy(chatID), databaseTools.getResources(chatID).get(ConstantDB.USER_GOLD))));

    }

    //Проверка наличия здания, к которому привязан воин
    private boolean checkRecruitingArmyOnBuilds(Map<String, Integer> builds, String unit) {
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
            if (Resources.checkResourcesOnSpending(
                    resources, ConstantResourcesForArmy.LIST_GOLD_FOR_ARMY.get(callbackData))) {

                Map<String, Integer> calculatingArmy = recruitingArmy(army, callbackData);
                Integer calculatingArmyPower = calculatingArmyPower(builds, calculatingArmy);
                databaseTools.setResources(chatID, Resources.updateResources(
                        resources, ConstantResourcesForArmy.LIST_GOLD_FOR_ARMY.get(
                                callbackData), 0));
                databaseTools.setArmy(chatID, callbackData, army.get(callbackData) + 1);
                messageSender.send(chatID, editMessage.warningMessage(
                        chatID, messageID, ConstantMessages.RECRUITING_UNIT_SUCCESSFUL));
                databaseTools.setArmyPower(chatID, calculatingArmyPower);

            } else {

                messageSender.send(chatID, editMessage.warningMessage(
                        chatID, messageID, ConstantMessages.RECRUITING_UNIT_FAILED));

            }
        } else {
            messageSender.send(chatID, editMessage.warningMessage(
                    chatID, messageID, ConstantMessages.UNIT_BUILD_NOT_BUILT));
        }
    }
}
