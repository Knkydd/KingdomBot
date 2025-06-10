package TelegramBot.bot;

import TelegramBot.bot.logic.*;
import TelegramBot.utility.keyboard.ConstantKB;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.BiConsumer;

public class BotController {
    private final BotUtils botUtils;
    private final UserStateRepository userStateRepository;
    private final Commands commands;

    public BotController(TelegramLongPollingBot bot) {
        this.botUtils = BotUtils.getInstance(bot);
        userStateRepository = botUtils.getUserStateRepository();
        commands = botUtils.getCommands();
    }

    public void updateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                handleCommands(update);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (update.hasCallbackQuery()) {
            try {
                handleCallbackData(update);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleCommands(Update update) {
        long chatID = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        String username = update.getMessage().getFrom().getUserName();
        if (text.equalsIgnoreCase("/start")) {
            if(!botUtils.getDatabaseTools().isRegistered(chatID)){
                botUtils.getDatabaseTools().registrationUser(chatID, username);
            }
            if (userStateRepository.isEmpty()) {
                userStateRepository.setState(chatID, ConstantKB.MAIN_MENU);
            } else {
                userStateRepository.removeAll(chatID);
                userStateRepository.setState(chatID, ConstantKB.MAIN_MENU);
            }
            botUtils.getMessageSender().send(chatID, botUtils.getKeyboard().startKeyboardMessage(chatID));
        }
    }

    private void handleCallbackData(Update update) {
        long chatID = update.getCallbackQuery().getMessage().getChatId();
        String callbackData = update.getCallbackQuery().getData();
        Integer messageID = update.getCallbackQuery().getMessage().getMessageId();
        if (callbackData.equalsIgnoreCase(ConstantKB.CALLBACK_BACK_BUTTON)) {
            callbackData = userStateRepository.getState(chatID);
        }
        try {
            BiConsumer<Long, Integer> command = commands.getCommand(callbackData);
            command.accept(chatID, messageID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
