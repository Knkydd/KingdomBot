package TelegramBot.utility;

import TelegramBot.utility.keyboard.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class EditMessage {
    private Keyboard keyboard;

    public EditMessage() {
        this.keyboard = new Keyboard();
    }

    public EditMessageText messageEdit(long chatID, Integer messageID, String callbackData, String text) {
        EditMessageText messageEdited = new EditMessageText();
        messageEdited.setMessageId(messageID);
        messageEdited.setChatId(chatID);
        System.out.println(callbackData);
        switch (callbackData) {
            case ConstantKB.MAIN_MENU:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(keyboard.startKeyboard());
                break;

            case ConstantKB.CALLBACK_PLAY_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(keyboard.gameKeyboard());
                break;

            case ConstantKB.CALLBACK_ACTION_BUTTON:
                messageEdited.setText(ConstantMessages.ACTIONS_MESSAGE);
                messageEdited.setReplyMarkup(keyboard.actionKeyboard());
                break;

            case ConstantKB.CALLBACK_LEADERBOARD_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(keyboard.leaderboardKeyboard());
                break;

            case ConstantKB.CALLBACK_BUILDS_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(keyboard.buildsKeyboard());
                break;

            case ConstantKB.CALLBACK_NEXT_MOVE_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(keyboard.warningKeyboard());
                break;

            case ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(keyboard.upbuildBuildsKeyboard());
                break;

            case ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(keyboard.upgradeBuildsKeyboard());
                break;
            case ConstantKB.CALLBACK_ARMY_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(keyboard.armyKeyboard());
                break;
            case ConstantKB.CALLBACK_RECRUITING_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(keyboard.recruitingKeyboard());
                break;
            case ConstantKB.CALLBACK_ATTACK_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(keyboard.attackKeyboard());
                break;
        }
        return messageEdited;
    }

    public EditMessageText warningMessage(long chatID, Integer messageID, String messageText) {
        EditMessageText messageEdited = new EditMessageText();
        messageEdited.setText(messageText);
        messageEdited.setMessageId(messageID);
        messageEdited.setChatId(chatID);
        messageEdited.setReplyMarkup(keyboard.warningKeyboard());
        return messageEdited;
    }
}
