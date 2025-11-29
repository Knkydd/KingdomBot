package telegrambot.bot;

import java.util.*;

public class UserStateRepository {
    private final static Map<Long, List<String>> userStates = new HashMap<>();

    public boolean isEmpty(){
        return userStates.isEmpty();
    }

    public void removeAll(long chatID){
        userStates.remove(chatID);
    }

    public void setState(long chatID, String newState) {
        userStates.computeIfAbsent(chatID, k -> new ArrayList<>()).add(newState);
    }

    public String getState(long chatID){
        List<String> states = userStates.get(chatID);
        String result = "";
        if(states == null || states.isEmpty()){
            result = "mainMenu";
        } else {
            states.remove(states.size()-1);
            result = states.get(states.size()-1);
            states.remove(states.size()-1);
        }
        return result;
    }
}

