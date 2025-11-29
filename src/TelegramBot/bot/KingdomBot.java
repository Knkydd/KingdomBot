package TelegramBot.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class KingdomBot extends TelegramLongPollingBot {
    private final BotController botController;

    public KingdomBot() {
        this.botController = new BotController(this);
    }

    @Override
    public String getBotUsername() {
        return "KingdomBot";
    }

    @Override
    public String getBotToken() {
        return Token.readToken();
    }

    public void onUpdateReceived(Update update) {
        try {
            botController.updateReceived(update);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}