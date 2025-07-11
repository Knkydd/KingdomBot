package TelegramBot.bot.logic;

import TelegramBot.bot.BotUtils;
import TelegramBot.data.DatabaseTools;
import TelegramBot.utility.ConstantMessages;
import TelegramBot.utility.EditMessage;
import TelegramBot.utility.MessageSender;
import TelegramBot.utility.keyboard.ConstantKB;

public class MainMenu {
    private final BotUtils botUtils;
    private final DatabaseTools databaseTools;
    private final MessageSender messageSender;
    private final UserStateRepository userStateRepository;
    private final EditMessage editMessage;

    public MainMenu(BotUtils botUtils) {
        this.botUtils = botUtils;
        databaseTools = botUtils.getDatabaseTools();
        messageSender = botUtils.getMessageSender();
        userStateRepository = botUtils.getUserStateRepository();
        editMessage = botUtils.getEditMessage();
    }

    public void setMainMenuInMSG(long chatID, Integer messageID) {
        messageSender.send(chatID, editMessage.messageEdit(chatID, messageID, ConstantKB.MAIN_MENU, ConstantMessages.START_MESSAGE));
        userStateRepository.setState(chatID, ConstantKB.MAIN_MENU);
    }

    public void mainMenuStart(long chatID){
        if(!databaseTools.isRegistered(chatID)){
            databaseTools.registrationUser(chatID);
        }
        if (userStateRepository.isEmpty()) {
            userStateRepository.setState(chatID, ConstantKB.MAIN_MENU);
        } else {
            userStateRepository.removeAll(chatID);
            userStateRepository.setState(chatID, ConstantKB.MAIN_MENU);
        }
        botUtils.getMessageSender().send(chatID, botUtils.getKeyboard().startKeyboardMessage(chatID));
    }

    public void mainMenuHandler(long chatID, String callbackData, Integer messageID) {

        switch (callbackData) {

            case ConstantKB.MAIN_MENU:

                messageSender.send(chatID, editMessage.messageEdit(chatID, messageID, callbackData, ConstantMessages.START_MESSAGE));
                userStateRepository.setState(chatID, ConstantKB.MAIN_MENU);
                break;

            case ConstantKB.CALLBACK_PLAY_BUTTON:

                messageSender.send(chatID, editMessage.messageEdit(chatID, messageID, callbackData, ConstantMessages.GAME_MESSAGE + Resources.resourceMessage(databaseTools.getResources(chatID))));
                userStateRepository.setState(chatID, ConstantKB.CALLBACK_PLAY_BUTTON);

                break;
        }
    }

}
