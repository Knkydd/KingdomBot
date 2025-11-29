package telegrambot.utility.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeyboardBuilder {
    public static InlineKeyboardMarkup createKeyboard(Map<String, String> buttonMap, int rows) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardRowsList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardRow = new ArrayList<>();

        for (Map.Entry<String, String> entry : buttonMap.entrySet()) {
            InlineKeyboardButton button = createButton(entry.getKey(), entry.getValue());
            keyboardRow.add(button);
            if (keyboardRow.size() == rows) {
                keyboardRowsList.add(keyboardRow);
                keyboardRow = new ArrayList<>();
            }
        }
        if (!keyboardRow.isEmpty()) {
            keyboardRowsList.add(keyboardRow);
        }
        keyboard.setKeyboard(keyboardRowsList);

        return keyboard;
    }

    private static InlineKeyboardButton createButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
