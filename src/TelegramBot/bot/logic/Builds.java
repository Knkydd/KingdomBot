package TelegramBot.bot.logic;

import TelegramBot.bot.BotUtils;
import TelegramBot.data.ConstantDB;
import TelegramBot.data.DatabaseTools;
import TelegramBot.utility.ConstantMessages;
import TelegramBot.utility.ConstantResourcesForBuilds;
import TelegramBot.utility.EditMessage;
import TelegramBot.utility.MessageSender;
import TelegramBot.utility.keyboard.ConstantKB;

import java.util.Map;

public class Builds {
    private final DatabaseTools databaseTools;
    private final MessageSender messageSender;
    private final UserStateRepository userStateRepository;
    private final EditMessage editMessage;

    public Builds(BotUtils botUtils) {
        this.messageSender = botUtils.getMessageSender();
        this.databaseTools = botUtils.getDatabaseTools();
        this.userStateRepository = botUtils.getUserStateRepository();
        this.editMessage = botUtils.getEditMessage();
    }

    //Сообщение меню зданий
    public static String buildsMessage(Map<String, Integer> builds) {
        StringBuilder message = new StringBuilder(ConstantMessages.BUILDS_MESSAGE);

        for (Map.Entry<String, Integer> entry : builds.entrySet()) {
            if (entry.getValue() != 0) {
                message.append("\n")
                        .append(ConstantDB.accordanceListOfBuilds.get(entry.getKey()))
                        .append(":    Уровень ")
                        .append(entry.getValue());
            }
        }

        return message.toString();
    }

    //Сообщение об улучшениях зданий
    private String upgradeBuildsMessage(Map<String, Integer> resources, Map<String, Integer> builds) {
        StringBuilder message = new StringBuilder(ConstantMessages.BUILDS_MESSAGE_UPGRADE);
        int counter = 1;

        for (Map.Entry<String, Integer> entry : builds.entrySet()) {
            String buildingKey = entry.getKey();
            int currentLevel = entry.getValue();

            if (currentLevel == 0) {
                continue;
            }

            String buildingName = ConstantDB.accordanceListOfBuilds.get(buildingKey);
            Map<String, Integer> resourcesNeed = ConstantResourcesForBuilds.RESOURCES_FOR_UPGRADE
                    .get(buildingKey)
                    .get(currentLevel);

            int maxLevel = ConstantResourcesForBuilds.LIST_LIMITS.get(buildingKey);

            if (currentLevel + 1 > maxLevel) {
                message.append(String.format("%d:      %s (Максимум)%n%n", counter++, buildingName));
            } else {
                message.append(String.format("%d:     %s     Уровень %d -> Уровень %d%n",
                                counter++, buildingName, currentLevel, currentLevel + 1))
                        .append(String.format("Нужно: %d дерева, %d золота и %d камня%n%n",
                                resourcesNeed.get("Wood"),
                                resourcesNeed.get("Gold"),
                                resourcesNeed.get("Stone")));
            }
        }

        message.append(Resources.resourceMessage(resources));
        return message.toString();
    }

    //Проверка, можно ли улучшать здание(проверка на превышение уровня)
    private boolean checkUpgradeBuilds(Map<String, Integer> builds, String build) {
        if (builds.get(build) >= ConstantResourcesForBuilds.LIST_LIMITS.get(build)) {
            return false;
        }
        return true;
    }

    //Проверка в меню улучшений(на отображение, можно ли улучшать не построенное)
    private boolean checkBuildBeforeUpgrade(Map<String, Integer> builds, String build) {
        if (builds.get(build).equals(0)) {
            return false;
        }
        return true;
    }

    //Сообщение меню постройки зданий
    private String upbuildBuildsMessage(Map<String, Integer> resources, Map<String, Integer> builds) {
        StringBuilder message = new StringBuilder(ConstantMessages.BUILDS_MESSAGE_UPBUILD);
        int counter = 1;

        builds.remove(ConstantDB.USER_TOWN_HALL);
        builds.remove(ConstantDB.USER_BARRACKS);
        builds.remove(ConstantDB.USER_FARM);
        builds.remove(ConstantDB.USER_SAWMILL);

        for (Map.Entry<String, Integer> entry : builds.entrySet()) {
            String buildingName = ConstantDB.accordanceListOfBuilds.get(entry.getKey());
            Map<String, Integer> costs = ConstantResourcesForBuilds.RESOURCES_FOR_BUILD.get(entry.getKey());

            message.append(counter++).append("  ").append(buildingName).append("  \n");

            if (entry.getValue() != 0) {
                message.append("(Построено)\n\n");
            } else {
                message.append("Нужно: ")
                        .append(costs.get("Wood")).append(" дерева, ")
                        .append(costs.get("Gold")).append(" золота и ")
                        .append(costs.get("Stone")).append(" камня\n\n");
            }
        }

        message.append(Resources.resourceMessage(resources));
        return message.toString();
    }

    //Проверка, можно ли строить здание(проверка на то, построено или нет)
    private boolean checkUpbuildBuilds(Map<String, Integer> builds, String build) {
        if (!builds.get(build).equals(0)) {
            return false;
        }
        return true;
    }

    public void buildsHandler(long chatID, String callbackData, Integer messageID) {
        Map<String, Integer> builds = databaseTools.getBuilds(chatID);
        Map<String, Integer> resources = databaseTools.getResources(chatID);
        switch (callbackData) {
            case ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON:
                userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON);
                messageSender.send(
                        chatID, editMessage.messageEdit(
                                chatID, messageID, callbackData, upbuildBuildsMessage(resources, builds)));
                break;

            case ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON:
                userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON);
                messageSender.send(
                        chatID, editMessage.messageEdit(
                                chatID, messageID, callbackData, upgradeBuildsMessage(resources, builds)));
                break;
        }
    }

    public void buildsHandlerUpbuild(long chatID, String callbackData, Integer messageID) {
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON);
        callbackData = callbackData.substring(0, callbackData.length() - 7);

        Map<String, Integer> builds = databaseTools.getBuilds(chatID);
        Map<String, Integer> resources = databaseTools.getResources(chatID);

        if (checkUpbuildBuilds(builds, callbackData)) {

            if (Resources.checkResourcesOnSpending(
                    resources, ConstantResourcesForBuilds.RESOURCES_FOR_BUILD.get(callbackData))) {
                databaseTools.setResources(
                        chatID, Resources.updateResources(
                                resources, ConstantResourcesForBuilds.RESOURCES_FOR_BUILD
                                        .get(callbackData), 0));
                databaseTools.setBuilds(
                        chatID, callbackData, 1);
                messageSender.send(
                        chatID, editMessage.warningMessage(
                                chatID, messageID, ConstantMessages.BUILD_SUCCESSFUL));

            } else {

                messageSender.send(
                        chatID, editMessage.warningMessage(
                                chatID, messageID, ConstantMessages.BUILD_FAILED_RESOURCES));
            }

        } else {

            messageSender.send(
                    chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.BUILD_FAILED));

        }
    }

    public void buildsHandlerUpgrade(long chatID, String callbackData, Integer messageID) {
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON);
        callbackData = callbackData.substring(0, callbackData.length() - 7);

        Map<String, Integer> builds = databaseTools.getBuilds(chatID);
        Map<String, Integer> resources = databaseTools.getResources(chatID);

        if (checkBuildBeforeUpgrade(builds, callbackData)) {

            if (checkUpgradeBuilds(builds, callbackData)) {

                if (Resources.checkResourcesOnSpending(
                        resources, ConstantResourcesForBuilds.RESOURCES_FOR_UPGRADE
                                .get(callbackData)
                                .get(databaseTools.getBuilds(chatID)
                                        .get(callbackData)))) {
                    Map<String, Integer> updatedResources = Resources.updateResources(
                            resources, ConstantResourcesForBuilds.RESOURCES_FOR_UPGRADE
                                    .get(callbackData)
                                    .get(builds.get(callbackData)), 0);
                    databaseTools.setResources(chatID, updatedResources);
                    databaseTools.setBuilds(chatID, callbackData, builds.get(callbackData) + 1);
                    messageSender.send(chatID,
                            editMessage.warningMessage(
                                    chatID, messageID, ConstantMessages.UPGRADE_BUILD_SUCCESSFUL));

                } else {

                    messageSender.send(
                            chatID, editMessage.warningMessage(
                                    chatID, messageID, ConstantMessages.UPGRADE_BUILD_FAILED_RESOURCES));

                }
            } else {

                messageSender.send(
                        chatID, editMessage.warningMessage(
                                chatID, messageID, ConstantMessages.UPGRADE_BUILD_FAILED));

            }
        } else {
            messageSender.send(
                    chatID, editMessage.warningMessage(
                            chatID, messageID, ConstantMessages.BUILD_NOT_BUILT_MESSAGE));
        }
    }
}
