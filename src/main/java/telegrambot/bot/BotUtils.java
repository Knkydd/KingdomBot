package telegrambot.bot;

import telegrambot.data.DatabaseConnection;
import telegrambot.data.DatabaseTools;
import telegrambot.utility.MessageEditor;
import telegrambot.utility.MessageSender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class BotUtils {
    private static volatile BotUtils instance;
    private final DatabaseTools databaseTools;
    private final MessageSender messageSender;
    private final UserStateRepository userStateRepository;
    private final MessageEditor messageEditor;
    private final Commands commands;

    private BotUtils(TelegramLongPollingBot bot) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        messageSender = new MessageSender(bot);
        databaseTools = dbConnection.getDatabaseTools();
        userStateRepository = new UserStateRepository();
        messageEditor = new MessageEditor();
        commands = new Commands(this);
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

    public DatabaseTools getDatabaseTools() {
        return databaseTools;
    }

    public MessageEditor getEditMessage() {
        return messageEditor;
    }

    public MessageSender getMessageSender() {
        return messageSender;
    }

    public UserStateRepository getUserStateRepository() {
        return userStateRepository;
    }

    public Commands getCommands() {
        return commands;
    }
}
