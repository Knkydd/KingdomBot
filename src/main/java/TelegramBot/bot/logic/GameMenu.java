package TelegramBot.bot.logic;

import TelegramBot.bot.BotUtils;
import TelegramBot.bot.UserStateRepository;
import TelegramBot.data.DatabaseTools;
import TelegramBot.utility.ConstantMessages;
import TelegramBot.utility.MessageEditor;
import TelegramBot.utility.MessageSender;
import TelegramBot.utility.keyboard.ConstantKB;

import java.util.Map;

public class GameMenu {
    private final DatabaseTools databaseTools;
    private final MessageSender messageSender;
    private final UserStateRepository userStateRepository;
    private final MessageEditor messageEditor;

    public GameMenu(BotUtils botUtils) {
        this.messageSender = botUtils.getMessageSender();
        this.databaseTools = botUtils.getDatabaseTools();
        this.userStateRepository = botUtils.getUserStateRepository();
        this.messageEditor = botUtils.getEditMessage();
    }

    public void gameMenuHandler(long chatID, String callbackData, Integer messageID) {
        switch (callbackData) {
            case ConstantKB.CALLBACK_ACTION_BUTTON:
                userStateRepository.setState(chatID, ConstantKB.CALLBACK_ACTION_BUTTON);
                messageSender.send(
                        chatID, messageEditor.messageEdit(
                                chatID, messageID, callbackData, ConstantMessages.ACTIONS_MESSAGE));
                break;

            case ConstantKB.CALLBACK_BUILDS_BUTTON:
                Map<String, Integer> builds = databaseTools.getUserBuilds(chatID);
                userStateRepository.setState(
                        chatID, ConstantKB.CALLBACK_BUILDS_BUTTON);
                messageSender.send(
                        chatID, messageEditor.messageEdit(
                                chatID, messageID, callbackData, Builds.createBuildsMessage(builds)));
                break;
            case ConstantKB.CALLBACK_ARMY_BUTTON:
                Map<String, Integer> army = databaseTools.getUserArmy(chatID);
                Integer armyPower = databaseTools.getUserArmyPower(chatID);
                userStateRepository.setState(
                        chatID, ConstantKB.CALLBACK_ARMY_BUTTON);
                messageSender.send(
                        chatID, messageEditor.messageEdit(
                                chatID, messageID, callbackData, Army.createArmyMessage(army, armyPower)));
                break;
        }
    }
}
