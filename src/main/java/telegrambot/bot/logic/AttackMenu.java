package telegrambot.bot.logic;

import telegrambot.bot.BotUtils;
import telegrambot.bot.UserStateRepository;
import telegrambot.data.DatabaseTools;
import telegrambot.utility.*;
import telegrambot.utility.keyboard.ConstantKB;

import java.util.Map;

public class AttackMenu {
    private final DatabaseTools databaseTools;
    private final MessageSender messageSender;
    private final UserStateRepository userStateRepository;
    private final MessageEditor messageEditor;

    public AttackMenu(BotUtils botUtils) {
        this.databaseTools = botUtils.getDatabaseTools();
        this.messageSender = botUtils.getMessageSender();
        this.messageEditor = botUtils.getMessageEditor();
        this.userStateRepository = botUtils.getUserStateRepository();
    }

    private String createAttackMessage(Integer armyPower, Integer currentLevel) {
        if (currentLevel == 11) {
            return ConstantMessages.FINAL_BATTLE_MESSAGE;
        }

        return "Уровень:   " + currentLevel + "\n\n" +
                "Для того, чтобы атаковать, вам нужно " +
                ConstantAttackMenu.ATTACK_LEVELS.get(currentLevel) +
                " мощи армии\n\n" +
                "На данный момент мощь вашей армии составляет: " +
                armyPower + "\n";
    }

    private String createRewardsMessage(Integer currentLevel) {
        Map<String, Integer> rewards = ConstantReward.REWARD_FOR_VICTORY_ATTACK.get(currentLevel);

        return "В награду за победу вы получаете " +
                rewards.get("Wood") + " дерева, " +
                rewards.get("Gold") + " золота, " +
                rewards.get("Stone") + " камня и " +
                rewards.get("Food") + " еды\n" +
                "Они будут добавлены к вам на склад";
    }

    public void attackMenuHandler(long chatID, String callbackData, Integer messageID) {
        Integer armyPower = databaseTools.getUserArmyPower(chatID);
        Integer currentLevel = databaseTools.getUserCurrentAttackLevel(chatID);
        Map<String, Integer> resources = databaseTools.getUserResources(chatID);
        switch (callbackData) {
            case ConstantKB.CALLBACK_ATTACK_BUTTON:

                userStateRepository.setState(chatID, ConstantKB.CALLBACK_ATTACK_BUTTON);
                messageSender.send(chatID, messageEditor.messageEdit(
                        chatID, messageID, ConstantKB.CALLBACK_ATTACK_BUTTON,
                        createAttackMessage(armyPower, currentLevel)));

                break;
            case ConstantKB.CALLBACK_ATTACK_ENEMY_BUTTON:
                userStateRepository.setState(chatID, ConstantKB.CALLBACK_ATTACK_ENEMY_BUTTON);
                if (armyPower >= ConstantAttackMenu.ATTACK_LEVELS.get(currentLevel)) {

                    databaseTools.setUserResources(chatID, Resources.updateResources(
                            resources, ConstantReward.REWARD_FOR_VICTORY_ATTACK.get(
                                    currentLevel), 1));
                    messageSender.send(chatID, messageEditor.warningMessage(
                            chatID, messageID,
                            ConstantMessages.ATTACK_ENEMY_SUCCESSFUL + createRewardsMessage(currentLevel)));
                    databaseTools.setUserLevelAttack(chatID, currentLevel + 1);

                } else {
                    messageSender.send(chatID, messageEditor.warningMessage(
                            chatID, messageID, ConstantMessages.ATTACK_ENEMY_FAILED));
                }
        }
    }

}
