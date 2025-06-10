package TelegramBot.bot.logic;

import TelegramBot.bot.BotUtils;
import TelegramBot.data.ConstantDB;
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

    public Builds(BotUtils botUtils) {
        this.messageSender = botUtils.getMessageSender();
        this.databaseTools = botUtils.getDatabaseTools();
        this.userStateRepository = botUtils.getUserStateRepository();
        this.editMessage = botUtils.getEditMessage();
    }

    public static String buildsMessage(Map<String, Integer> builds) {
        String message = ConstantMessages.BUILDS_MESSAGE;
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

    public String upgradeBuildsMessage(Map<String, Integer> resources, Map<String, Integer> builds) {
        String message = ConstantMessages.BUILDS_MESSAGE_UPGRADE;
        Set<String> buildsKeys = builds.keySet();
        String tempMessage = "";
        Iterator iteratorBuildsKeys = buildsKeys.iterator();
        int i = 1;
        while (iteratorBuildsKeys.hasNext()) {
            String temp = (String) iteratorBuildsKeys.next();
            Map<String, Integer> resourcesNeed = ConstantResourcesForBuilds.RESOURCES_FOR_UPGRADE.get(temp).get(builds.get(temp));
            if (builds.get(temp) + 1 > ConstantResourcesForBuilds.LIST_LIMITS.get(temp)) {
                tempMessage = String.format("%s:      %s(Максимум)\n\n", i, ConstantDB.accordanceListOfBuilds.get(temp));
            } else if (builds.get(temp).equals(0)) {
                continue;
            } else {
                tempMessage = String.format("%s:     %s     Уровень %s -> Уровень %s\n",
                        i, ConstantDB.accordanceListOfBuilds.get(temp), builds.get(temp), builds.get(temp) + 1);
                tempMessage += String.format("Нужно: %s дерева, %s золота и %s камня\n\n", resourcesNeed.get("Wood"), resourcesNeed.get("Gold"), resourcesNeed.get("Stone"));
            }
            message += tempMessage;
            i++;
        }
        message += Resources.resourceMessage(resources);
        return message;
    }

    public boolean checkUpgradeBuilds(Map<String, Integer> builds, String build) {
        if (builds.get(build) >= ConstantResourcesForBuilds.LIST_LIMITS.get(build)) {
            return false;
        }
        return true;
    }

    public boolean checkBuildBeforeUpgrade(Map<String, Integer> builds, String build) {
        if (builds.get(build).equals(0)) {
            return false;
        }
        return true;
    }

    public Map<String, Integer> upgradeBuilds(Map<String, Integer> builds, String build) {
        builds.put(build, builds.get(build) + 1);
        return builds;
    }

    public String upbuildBuildsMessage(Map<String, Integer> resources, Map<String, Integer> builds) {
        String message = ConstantMessages.BUILDS_MESSAGE_UPBUILD;
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
            Map<String, Integer> resourcesNeed = ConstantResourcesForBuilds.RESOURCES_FOR_BUILD.get(temp);
            tempMessage = String.format("%s  %s  \n", i, ConstantDB.accordanceListOfBuilds.get(temp));
            if (!builds.get(temp).equals(0)) {
                tempMessage += "(Построено)\n\n";
            } else {
                tempMessage += String.format("Нужно: %s дерева, %s золота и %s камня\n\n", resourcesNeed.get("Wood"), resourcesNeed.get("Gold"), resourcesNeed.get("Stone"));
            }
            message += tempMessage;
            i++;
        }
        message += Resources.resourceMessage(resources);
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
        Map<String, Integer> builds = databaseTools.getBuilds(chatID);
        Map<String, Integer> resources = databaseTools.getResources(chatID);
        switch (callbackData) {
            case ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON:
                userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON);
                messageSender.send(chatID, editMessage.messageEdit(chatID, messageID, callbackData, upbuildBuildsMessage(resources, builds)));
                break;

            case ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON:
                userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON);
                messageSender.send(chatID, editMessage.messageEdit(chatID, messageID, callbackData, upgradeBuildsMessage(resources, builds)));
                break;
        }
    }

    public void buildsHandlerUpbuild(long chatID, String callbackData, Integer messageID) {
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON);
        callbackData = callbackData.substring(0, callbackData.length() - 7);

        Map<String, Integer> builds = databaseTools.getBuilds(chatID);
        Map<String, Integer> resources = databaseTools.getResources(chatID);

        if (Builds.checkUpbuildBuilds(builds, callbackData)) {

            if (Resources.checkResourcesOnSpending(resources, ConstantResourcesForBuilds.RESOURCES_FOR_BUILD.get(callbackData))) {
                databaseTools.setResources(chatID, Resources.updateResources(resources, ConstantResourcesForBuilds.RESOURCES_FOR_BUILD.get(callbackData), 0));
                databaseTools.setBuilds(chatID, Builds.upbuildBuilds(builds, callbackData));
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.BUILD_SUCCESSFUL));

            } else {

                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.BUILD_FAILED_RESOURCES));
            }

        } else {

            messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.BUILD_FAILED));

        }
    }

    public void buildsHandlerUpgrade(long chatID, String callbackData, Integer messageID) {
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON);
        callbackData = callbackData.substring(0, callbackData.length() - 7);

        Map<String, Integer> builds = databaseTools.getBuilds(chatID);
        Map<String, Integer> resources = databaseTools.getResources(chatID);

        if (checkBuildBeforeUpgrade(builds, callbackData)) {

            if (checkUpgradeBuilds(builds, callbackData)) {

                if (Resources.checkResourcesOnSpending(resources, ConstantResourcesForBuilds.RESOURCES_FOR_UPGRADE.get(callbackData).get(databaseTools.getBuilds(chatID).get(callbackData)))) {
                    Map<String, Integer> updatedResources = Resources.updateResources(resources, ConstantResourcesForBuilds.RESOURCES_FOR_UPGRADE.get(callbackData).get(builds.get(callbackData)), 0);
                    databaseTools.setResources(chatID, updatedResources);
                    databaseTools.setBuilds(chatID, upgradeBuilds(builds, callbackData));
                    messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.UPGRADE_BUILD_SUCCESSFUL));

                } else {

                    messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.UPGRADE_BUILD_FAILED_RESOURCES));

                }
            } else {

                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.UPGRADE_BUILD_FAILED));

            }
        } else {
            messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.BUILD_NOT_BUILT_MESSAGE));
        }
    }
}
