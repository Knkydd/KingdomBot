package TelegramBot.utility.keyboard;


import TelegramBot.utility.ConstantMessages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class Keyboard {
    private final KeyboardBuilder keyboardBuilder;

    public Keyboard() {
        this.keyboardBuilder = new KeyboardBuilder();

    }

    public SendMessage startKeyboardMessage(long chatID) {
        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText(ConstantMessages.START_MESSAGE);
        InlineKeyboardMarkup keyboard = startKeyboard();
        message.setReplyMarkup(keyboard);
        return message;
    }

    public InlineKeyboardMarkup startKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.startButtons, 1);
    }

    public InlineKeyboardMarkup gameKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.gameButtons, 2);
    }

    public InlineKeyboardMarkup actionKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.actionsButtons, 3);
    }

    public InlineKeyboardMarkup warningKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.oneBackButton, 1);
    }

    public InlineKeyboardMarkup leaderboardKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.oneBackButton, 1);
    }

    public InlineKeyboardMarkup buildsKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.buildMenuButtons, 2);
    }

    public InlineKeyboardMarkup upgradeBuildsKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.upgradeButtons, 3);
    }

    public InlineKeyboardMarkup upbuildBuildsKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.upbuildButtons, 3);
    }

    public InlineKeyboardMarkup armyKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.armyButtons, 2);
    }

    public InlineKeyboardMarkup recruitingKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.recruitingButtons, 3);
    }

    public InlineKeyboardMarkup attackKeyboard() {
        return keyboardBuilder.createKeyboard(ButtonsMaps.attackButtons, 1);
    }
}




