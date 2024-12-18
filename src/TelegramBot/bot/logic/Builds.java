package TelegramBot.bot.logic;

import TelegramBot.bot.BotUtils;
import TelegramBot.data.ConstantDB;
import TelegramBot.data.DatabaseConnection;
import TelegramBot.data.DatabaseTools;
import TelegramBot.utility.ConstantMessages;
import TelegramBot.utility.ConstantResourcesForBuilds;
import TelegramBot.utility.EditMessage;
import TelegramBot.utility.MessageSender;
import TelegramBot.utility.keyboard.ConstantKB;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Builds {
    private DatabaseTools databaseTools;
    private MessageSender messageSender;
    private UserStateRepository userStateRepository;
    private EditMessage editMessage;

    public Builds(BotUtils botUtils){
        this.messageSender = botUtils.getMessageSender();
        this.databaseTools = botUtils.getDatabaseTools();
        this.userStateRepository = botUtils.getUserStateRepository();
        this.editMessage = botUtils.getEditMessage();
    }

    public static String buildsMessage(Map<String, Integer> builds) {
        String message = "Постройки, которые у вас есть: \n";
        Set<String> buildsKeys = builds.keySet();
        Iterator iterator = buildsKeys.iterator();
        while (iterator.hasNext()) {
            String temp = (String) iterator.next();
            String tempMessage = "";
            if (!builds.get(temp).equals(0)) {
                tempMessage = String.format("%s:    Уровень %s\n", ConstantDB.accordanceListOfBuilds.get(temp), builds.get(temp));
                message = message + tempMessage;
            }
        }
        return message;
    }

    public static String upgradeBuildsMessage(Map<String, Integer> builds) {
        String message = "Постройки, которые вы можете улучшить: \n";
        Set<String> buildsKeys = builds.keySet();
        String tempMessage = "";
        Iterator iteratorBuildsKeys = buildsKeys.iterator();
        int i = 1;
        while (iteratorBuildsKeys.hasNext()) {
            String temp = (String) iteratorBuildsKeys.next();
            if (builds.get(temp) + 1 > ConstantResourcesForBuilds.LIST_LIMITS.get(temp)) {
                tempMessage = String.format("%s:      %s(Максимум)\n", i, ConstantDB.accordanceListOfBuilds.get(temp));
            } else if (builds.get(temp).equals(0)) {
                continue;
            } else {
                tempMessage = String.format("%s:     %s     Уровень %s -> Уровень %s \n",
                        i, ConstantDB.accordanceListOfBuilds.get(temp), builds.get(temp), builds.get(temp) + 1);

            }
            message += tempMessage;
            i++;
        }
        return message;
    }

    public static boolean checkUpgradeBuilds(Map<String, Integer> builds, String build) {
        if (builds.get(build) >= ConstantResourcesForBuilds.LIST_LIMITS.get(build)) {
            return false;
        }
        return true;
    }

    public static Map<String, Integer> upgradeBuilds(Map<String, Integer> builds, String build) {
        builds.put(build, builds.get(build) + 1);
        return builds;
    }

    public static String upbuildBuildsMessage(Map<String, Integer> builds) {
        String message = "Постройки, которые вы можете построить:\n";
        String tempMessage = "";
        Set<String> buildsKeys = builds.keySet();
        buildsKeys.remove(ConstantDB.USER_TOWN_HALL);
        buildsKeys.remove(ConstantDB.USER_BARRACKS);
        buildsKeys.remove(ConstantDB.USER_FARM);
        buildsKeys.remove(ConstantDB.USER_SAWMILL);
        int i = 1;
        Iterator iteratorBuildsKeys = buildsKeys.iterator();
        while (iteratorBuildsKeys.hasNext()) {
            String temp = (String) iteratorBuildsKeys.next();
            tempMessage = String.format("%s  %s  \n", i, ConstantDB.accordanceListOfBuilds.get(temp));
            if (!builds.get(temp).equals(0)) {
                tempMessage += "(Построено)\n";
            }
            message += tempMessage;
            i++;
        }
        return message;
    }

    public static boolean checkUpbuildBuilds(Map<String, Integer> builds, String build) {
        if (!builds.get(build).equals(0)) {
            return false;
        }
        return true;
    }

    public static Map<String, Integer> upbuildBuilds(Map<String, Integer> builds, String build) {
        builds.put(build, 1);
        return builds;
    }

    public void buildsHandler(long chatID, String callbackData, Integer messageID) {
        switch (callbackData) {
            case ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON:
                messageSender.send(chatID, editMessage.messageEdit(chatID, messageID, callbackData, Builds.upbuildBuildsMessage(databaseTools.getBuilds(chatID))));
                userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON);
                break;

            case ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON:
                messageSender.send(chatID, editMessage.messageEdit(chatID, messageID, callbackData, Builds.upgradeBuildsMessage(databaseTools.getBuilds(chatID))));
                userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON);
                break;
        }
    }

    public void buildsHandlerUpbuild(long chatID, String callbackData, Integer messageID){
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON);
        callbackData = callbackData.substring(0, callbackData.length() - 7);
        if (Builds.checkUpbuildBuilds(databaseTools.getBuilds(chatID), callbackData)) {
            if (Resources.checkResourcesOnSpending(databaseTools.getResources(chatID), ConstantResourcesForBuilds.RESOURCES_FOR_BUILD.get(callbackData))) {
                databaseTools.setResources(chatID, Resources.updateResources(databaseTools.getResources(chatID), ConstantResourcesForBuilds.RESOURCES_FOR_BUILD.get(callbackData)));
                databaseTools.setBuilds(chatID, Builds.upbuildBuilds(databaseTools.getBuilds(chatID), callbackData));
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.BUILD_SUCCESSFUL));

            } else {
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.BUILD_FAILED_RESOURCES));
            }

        } else {

            messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.BUILD_FAILED));

        }
    }

    public void buildsHandlerUpgrade(long chatID, String callbackData, Integer messageID){
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON);
        callbackData = callbackData.substring(0, callbackData.length() - 7);
        if (Builds.checkUpgradeBuilds(databaseTools.getBuilds(chatID), callbackData)) {
            if (Resources.checkResourcesOnSpending(databaseTools.getResources(chatID), ConstantResourcesForBuilds.RESOURCES_FOR_UPGRADE.get(callbackData).get(databaseTools.getBuilds(chatID).get(callbackData)))) {
                databaseTools.setResources(chatID, Resources.updateResources(databaseTools.getResources(chatID), ConstantResourcesForBuilds.RESOURCES_FOR_UPGRADE.get(callbackData).get(databaseTools.getBuilds(chatID).get(callbackData))));
                databaseTools.setBuilds(chatID, Builds.upgradeBuilds(databaseTools.getBuilds(chatID), callbackData));
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.UPGRADE_BUILD_SUCCESSFUL));
            } else {
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.BUILD_FAILED_RESOURCES));
            }
        } else {
            messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.UPGRADE_BUILD_FAILED));
        }
    }
}