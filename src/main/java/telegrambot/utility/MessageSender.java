package telegrambot.utility;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class MessageSender {
    private final TelegramLongPollingBot bot;

    public MessageSender(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    public void send(long chatID, SendMessage message) {
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error! Message is not send. Exception: {}, ChatID: {}, Message: {}", e.getMessage(), chatID, message);
        }
    }

    public void send(long chatID, EditMessageText message) {
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error! Message is not edited. Exception: {}, ChatID: {}, Message: {}", e.getMessage(), chatID, message);
        }
    }
}
