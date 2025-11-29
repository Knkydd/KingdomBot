package TelegramBot.bot.logic;

import TelegramBot.bot.BotUtils;
import TelegramBot.bot.UserStateRepository;
import TelegramBot.data.ConstantDB;
import TelegramBot.data.DatabaseTools;
import TelegramBot.utility.ConstantMessages;
import TelegramBot.utility.EditMessage;
import TelegramBot.utility.MessageSender;
import TelegramBot.utility.keyboard.ConstantKB;

import java.util.HashMap;
import java.util.Map;

public class NextMove {
    private Integer woodResourceUpdate = 0;
    private Integer goldResourceUpdate = 0;
    private Integer foodResourceUpdate = 0;
    private Integer stoneResourceUpdate = 0;
    private DatabaseTools databaseTools;
    private MessageSender messageSender;
    private EditMessage editMessage;
    private UserStateRepository userStateRepository;

    public NextMove(BotUtils botUtils) {
        this.databaseTools = botUtils.getDatabaseTools();
        this.messageSender = botUtils.getMessageSender();
        this.editMessage = botUtils.getEditMessage();
        this.userStateRepository = botUtils.getUserStateRepository();
    }

    public void setWoodResourceUpdate(Integer wood, Integer sawMillLevel, Integer townHallLevel) {

        this.woodResourceUpdate = wood + (sawMillLevel * 10) + (townHallLevel * 5);
    }

    public void setGoldResourceUpdate(Integer gold, Integer tradeBuildLevel, Integer townHallLevel) {
        this.goldResourceUpdate = gold + (tradeBuildLevel * 50) + (townHallLevel * 5);
    }

    public void setStoneResourceUpdate(Integer stone, Integer quarryLevel, Integer townHallLevel) {
        this.stoneResourceUpdate = stone + (quarryLevel * 10) + (townHallLevel * 5);
    }

    public void setFoodResources(Integer food, Integer farmLevel, Map<String, Integer> army, Integer townHallLevel) {
        this.foodResourceUpdate = food + (farmLevel * 30) + (townHallLevel * 5) - (army.get(ConstantDB.USER_WARRIOR_UNIT) * 2) - (army.get(ConstantDB.USER_MAGE_UNIT) * 4) -
                (army.get(ConstantDB.USER_ARCHER_UNIT) * 3) - (army.get(ConstantDB.USER_PALADIN_UNIT) * 5) -
                (army.get(ConstantDB.USER_HEALER_UNIT) * 7);
    }

    public Integer getFoodResourceUpdate() {
        return foodResourceUpdate;
    }

    public Integer getGoldResourceUpdate() {
        return goldResourceUpdate;
    }

    public Integer getStoneResourceUpdate() {
        return stoneResourceUpdate;
    }

    public Integer getWoodResourceUpdate() {
        return woodResourceUpdate;
    }

    public Map<String, Integer> calculatingResources(long chatID) {
        Map<String, Integer> resourcesUpdated = new HashMap<>();
        Map<String, Integer> army = databaseTools.getArmy(chatID);
        Map<String, Integer> builds = databaseTools.getBuilds(chatID);
        Map<String, Integer> resources = databaseTools.getResources(chatID);

        setGoldResourceUpdate(resources.get(ConstantDB.USER_GOLD), builds.get(ConstantDB.USER_TRADE_BUILD), builds.get(ConstantDB.USER_TOWN_HALL));
        setStoneResourceUpdate(resources.get(ConstantDB.USER_STONE), builds.get(ConstantDB.USER_QUARRY), builds.get(ConstantDB.USER_TOWN_HALL));
        setWoodResourceUpdate(resources.get(ConstantDB.USER_WOOD), builds.get(ConstantDB.USER_SAWMILL), builds.get(ConstantDB.USER_TOWN_HALL));
        setFoodResources(resources.get(ConstantDB.USER_FOOD), builds.get(ConstantDB.USER_FARM), army, builds.get(ConstantDB.USER_TOWN_HALL));

        resourcesUpdated.put(ConstantDB.USER_WOOD, getWoodResourceUpdate());
        resourcesUpdated.put(ConstantDB.USER_GOLD, getGoldResourceUpdate());
        resourcesUpdated.put(ConstantDB.USER_FOOD, getFoodResourceUpdate());
        resourcesUpdated.put(ConstantDB.USER_STONE, getStoneResourceUpdate());

        return resourcesUpdated;
    }

    public void updateResources(long chatID) {
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_ACTION_BUTTON);
        Map<String, Integer> resourcesCalculated = calculatingResources(chatID);
        databaseTools.setResources(chatID, resourcesCalculated);
    }

    public void updateResourcesPlusWood(long chatID) {
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_ACTION_BUTTON);
        Map<String, Integer> resourcesCalculated = calculatingResources(chatID);
        resourcesCalculated.put(ConstantDB.USER_WOOD, resourcesCalculated.get(ConstantDB.USER_WOOD) + 15);
        databaseTools.setResources(chatID, resourcesCalculated);
    }

    public void updateResourcesPlusGold(long chatID) {
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_ACTION_BUTTON);
        Map<String, Integer> resourcesCalculated = calculatingResources(chatID);
        resourcesCalculated.put(ConstantDB.USER_GOLD, resourcesCalculated.get(ConstantDB.USER_GOLD) + 50);
        databaseTools.setResources(chatID, resourcesCalculated);
    }

    public void updateResourcesPlusStone(long chatID) {
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_ACTION_BUTTON);
        Map<String, Integer> resourcesCalculated = calculatingResources(chatID);
        resourcesCalculated.put(ConstantDB.USER_STONE, resourcesCalculated.get(ConstantDB.USER_STONE) + 10);
        databaseTools.setResources(chatID, resourcesCalculated);
    }

    public void updateResourcesPlusFood(long chatID) {
        userStateRepository.setState(chatID, ConstantKB.CALLBACK_ACTION_BUTTON);
        Map<String, Integer> resourcesCalculated = calculatingResources(chatID);
        resourcesCalculated.put(ConstantDB.USER_FOOD, resourcesCalculated.get(ConstantDB.USER_FOOD) + 30);
        databaseTools.setResources(chatID, resourcesCalculated);
    }

    public void nextMoveHandler(long chatID, String callbackData, Integer messageID) {
        switch (callbackData) {
            case ConstantKB.CALLBACK_NEXT_MOVE_BUTTON:
                updateResources(chatID);
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.NEXT_MOVE_MESSAGE));
                break;
            case ConstantKB.CALLBACK_MOVE_CHOP:
                updateResourcesPlusWood(chatID);
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.MOVE_CHOP_MESSAGE));
                break;
            case ConstantKB.CALLBACK_MOVE_DIG:
                updateResourcesPlusStone(chatID);
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.MOVE_DIG_MESSAGE));
                break;
            case ConstantKB.CALLBACK_MOVE_TRADE:
                updateResourcesPlusGold(chatID);
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.MOVE_TRADE_MESSAGE));
                break;
            case ConstantKB.CALLBACK_MOVE_WORK_ON_FARM:
                updateResourcesPlusFood(chatID);
                messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.MOVE_WORK_ON_FARM_MESSAGE));
                break;
        }
    }
}

