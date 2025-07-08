package TelegramBot.bot.logic;

import TelegramBot.bot.BotUtils;
import TelegramBot.data.DatabaseTools;
import TelegramBot.utility.*;
import TelegramBot.utility.keyboard.ConstantKB;

import java.util.Map;

public class AttackMenu {
    private DatabaseTools databaseTools;
    private MessageSender messageSender;
    private UserStateRepository userStateRepository;
    private EditMessage editMessage;

    public AttackMenu(BotUtils botUtils) {
        this.databaseTools = botUtils.getDatabaseTools();
        this.messageSender = botUtils.getMessageSender();
        this.editMessage = botUtils.getEditMessage();
        this.userStateRepository = botUtils.getUserStateRepository();
    }

    private String attackMessage(Integer armyPower, Integer currentLevel) {
        String message = "";
        String tempMessage = "";
        if (!currentLevel.equals(11)) {
            tempMessage = "Уровень:   " + currentLevel + "\n\n" +
                    "Для того, чтобы атаковать, вам нужно " + ConstantAttackMenu.ATTACK_LEVELS.get(currentLevel) + " мощи армии\n\n" +
                    "На данный момент мощь вашей армии составляет: " + armyPower + "\n";
        } else {
            tempMessage = ConstantMessages.FINAL_BATTLE_MESSAGE;
        }
        message += tempMessage;
        return message;
    }

    public String rewardsMessage(Integer currentLevel) {
        String message = "";
        Map<String, Integer> rewards = ConstantReward.REWARD_FOR_VICTORY_ATTACK.get(currentLevel);

        String tempMessage = "В награду за победу вы получаете " +
                rewards.get("Wood") + " дерева, " +
                rewards.get("Gold") + " золота, " +
                rewards.get("Stone") + " камня и " +
                rewards.get("Food") + " еды\n" +
                "Они будут добавлены к вам на склад";
        message += tempMessage;
        return message;
    }

    public void attackMenuHandler(long chatID, String callbackData, Integer messageID) {
        Integer armyPower = databaseTools.getArmyPower(chatID);
        Integer currentLevel = databaseTools.getCurrentAttackLevel(chatID);
        Map<String, Integer> resources = databaseTools.getResources(chatID);
        switch (callbackData) {
            case ConstantKB.CALLBACK_ATTACK_BUTTON:

                userStateRepository.setState(chatID, ConstantKB.CALLBACK_ATTACK_BUTTON);
                messageSender.send(chatID, editMessage.messageEdit(chatID, messageID, ConstantKB.CALLBACK_ATTACK_BUTTON, attackMessage(armyPower, currentLevel)));

                break;
            case ConstantKB.CALLBACK_ATTACK_ENEMY_BUTTON:
                userStateRepository.setState(chatID, ConstantKB.CALLBACK_ATTACK_ENEMY_BUTTON);
                if (armyPower >= ConstantAttackMenu.ATTACK_LEVELS.get(currentLevel)) {

                    databaseTools.setResources(chatID, Resources.updateResources(resources, ConstantReward.REWARD_FOR_VICTORY_ATTACK.get(currentLevel), 1));
                    messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.ATTACK_ENEMY_SUCCESSFUL + rewardsMessage(currentLevel)));
                    databaseTools.setLevelAttack(chatID, currentLevel + 1);

                } else {
                    messageSender.send(chatID, editMessage.warningMessage(chatID, messageID, ConstantMessages.ATTACK_ENEMY_FAILED));
                }
        }
    }

}
