package TelegramBot.bot.logic;

import java.util.*;

public class UserStateRepository {
    private final static Map<Long, List<String>> userStates = new HashMap<>();

    public boolean isEmpty(){
        if(userStates.isEmpty())
            return true;
        return false;
    }

    public void removeAll(long chatID){
        userStates.get(chatID).clear();
    }

    public void setState(long chatID, String newState) {
        userStates.computeIfAbsent(chatID, k -> new ArrayList<>()).add(newState);
    }

    public String getState(long chatID){
        List<String> states = userStates.get(chatID);
        String result = "";
        if(!states.isEmpty()) {
            states.remove(states.size());
        }
        if(states.isEmpty()){
            result = "mainMenu";
        } else {
            result = states.get(states.size());
            states.remove(states.size());
        }
        return result;
    }
}

