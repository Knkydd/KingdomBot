package telegrambot.bot;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import telegrambot.data.DatabaseConnection;
import telegrambot.data.DatabaseTools;
import telegrambot.utility.MessageEditor;
import telegrambot.utility.MessageSender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

@Slf4j
public class BotUtils {
    private static volatile BotUtils instance;
    @Getter
    private final DatabaseTools databaseTools;
    @Getter
    private final MessageSender messageSender;
    @Getter
    private final UserStateRepository userStateRepository;
    @Getter
    private final MessageEditor messageEditor;
    @Getter
    private final Commands commands;

    private BotUtils(TelegramLongPollingBot bot) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        messageSender = new MessageSender(bot);
        databaseTools = dbConnection.getDatabaseTools();
        userStateRepository = new UserStateRepository();
        messageEditor = new MessageEditor();
        commands = new Commands(this);
        log.info("BotUtils created successful");
    }

    public static BotUtils getInstance(TelegramLongPollingBot bot) {
        if (instance == null) {
            synchronized (BotUtils.class) {
                if (instance == null)
                    instance = new BotUtils(bot);
            }
        }
        return instance;
    }
}
