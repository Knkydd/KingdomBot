package TelegramBot.bot.logic;

import java.util.*;

public class Leaderboard {
    public static String leaderboardMessage(Map<String, Integer> leaderboard){
        String message = "Таблица лидеров по мощи армии:\n";
        Set<String> sortedLeaderboardKeys = leaderboard.keySet();
        Iterator<String> leaderboardIterator = sortedLeaderboardKeys.iterator();
        int i =1;
        while(leaderboardIterator.hasNext()){
            String next = leaderboardIterator.next();
            String m = String.format("%s    %s     %s\n",i, next, leaderboard.get(next));
            message = message + m;
            i++;
        }
        return message;
    }
}