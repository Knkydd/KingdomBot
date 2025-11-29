package telegrambot.bot.logic;

import java.util.*;

public class Leaderboard {
    public static String createLeaderboardMessage(Map<String, Integer> leaderboard) {
        StringBuilder message = new StringBuilder("Таблица лидеров по мощи армии:\n");
        int counter = 0;
        for(var entry : leaderboard.entrySet()){
            message.append(counter)
                    .append("     ")
                    .append(entry.getKey())
                    .append("     ")
                    .append(entry.getValue())
                    .append("\n");
            counter+=1;
            if(counter == 10)
                break;
        }
        return message.toString();
    }
}