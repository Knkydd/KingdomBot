package TelegramBot.utility.keyboard;


import TelegramBot.utility.ConstantMessages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class Keyboard {
    public static SendMessage startKeyboardMessage(long chatID) {
        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText(ConstantMessages.START_MESSAGE);
        InlineKeyboardMarkup keyboard = startKeyboard();
        message.setReplyMarkup(keyboard);
        return message;
    }

    public static InlineKeyboardMarkup startKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.startButtons, 1);
    }

    public static InlineKeyboardMarkup gameKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.gameButtons, 2);
    }

    public static InlineKeyboardMarkup actionKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.actionsButtons, 3);
    }

    public static InlineKeyboardMarkup warningKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.oneBackButton, 1);
    }

    public static InlineKeyboardMarkup leaderboardKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.oneBackButton, 1);
    }

    public static InlineKeyboardMarkup buildsKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.buildMenuButtons, 2);
    }

    public static InlineKeyboardMarkup upgradeBuildsKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.upgradeButtons, 3);
    }

    public static InlineKeyboardMarkup upbuildBuildsKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.upbuildButtons, 3);
    }

    public static InlineKeyboardMarkup armyKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.armyButtons, 2);
    }

    public static InlineKeyboardMarkup recruitingKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.recruitingButtons, 3);
    }

    public static InlineKeyboardMarkup attackKeyboard() {
        return KeyboardBuilder.createKeyboard(ButtonsMaps.attackButtons, 1);
    }
}




