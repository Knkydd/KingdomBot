package telegrambot.bot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class KingdomBot extends TelegramLongPollingBot {
    private final BotController botController;

    public KingdomBot() {
        this.botController = new BotController(this);
        log.info("KingdomBot created successful");
    }

    @Override
    public String getBotUsername() {
        return "KingdomBot";
    }

    @Override
    public String getBotToken() {
        return TokenReader.readToken();
    }

    public void onUpdateReceived(Update update) {
        try {
            botController.updateReceived(update);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}