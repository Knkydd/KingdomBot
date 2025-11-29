package telegrambot.utility;

import telegrambot.utility.keyboard.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class MessageEditor {
    public EditMessageText messageEdit(long chatID, Integer messageID, String callbackData, String text) {
        EditMessageText messageEdited = new EditMessageText();
        messageEdited.setMessageId(messageID);
        messageEdited.setChatId(chatID);
        System.out.println(callbackData);
        switch (callbackData) {
            case ConstantKB.MAIN_MENU:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(Keyboard.startKeyboard());
                break;

            case ConstantKB.CALLBACK_PLAY_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(Keyboard.gameKeyboard());
                break;

            case ConstantKB.CALLBACK_ACTION_BUTTON:
                messageEdited.setText(ConstantMessages.ACTIONS_MESSAGE);
                messageEdited.setReplyMarkup(Keyboard.actionKeyboard());
                break;

            case ConstantKB.CALLBACK_BUILDS_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(Keyboard.buildsKeyboard());
                break;

            case ConstantKB.CALLBACK_NEXT_MOVE_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(Keyboard.warningKeyboard());
                break;

            case ConstantKB.CALLBACK_UPBUILD_BUILD_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(Keyboard.upbuildBuildsKeyboard());
                break;

            case ConstantKB.CALLBACK_UPGRADE_BUILD_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(Keyboard.upgradeBuildsKeyboard());
                break;
            case ConstantKB.CALLBACK_ARMY_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(Keyboard.armyKeyboard());
                break;
            case ConstantKB.CALLBACK_RECRUITING_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(Keyboard.recruitingKeyboard());
                break;
            case ConstantKB.CALLBACK_ATTACK_BUTTON:
                messageEdited.setText(text);
                messageEdited.setReplyMarkup(Keyboard.attackKeyboard());
                break;
        }
        return messageEdited;
    }

    public EditMessageText warningMessage(long chatID, Integer messageID, String messageText) {
        EditMessageText messageEdited = new EditMessageText();
        messageEdited.setText(messageText);
        messageEdited.setMessageId(messageID);
        messageEdited.setChatId(chatID);
        messageEdited.setReplyMarkup(Keyboard.warningKeyboard());
        return messageEdited;
    }
}
