package TelegramBot.bot;

import TelegramBot.bot.logic.UserStateRepository;
import TelegramBot.data.DatabaseConnection;
import TelegramBot.data.DatabaseTools;
import TelegramBot.utility.EditMessage;
import TelegramBot.utility.MessageSender;
import TelegramBot.utility.keyboard.Keyboard;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class BotUtils {
    private static volatile BotUtils instance;
    private final DatabaseTools databaseTools;
    private final MessageSender messageSender;
    private final UserStateRepository userStateRepository;
    private final EditMessage editMessage;
    private final Commands commands;

    private BotUtils(TelegramLongPollingBot bot) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        messageSender = new MessageSender(bot);
        databaseTools = dbConnection.getDatabaseTools();
        userStateRepository = new UserStateRepository();
        editMessage = new EditMessage();
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

    public EditMessage getEditMessage() {
        return editMessage;
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
