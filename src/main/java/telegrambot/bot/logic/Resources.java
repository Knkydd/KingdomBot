package telegrambot.bot.logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Resources {
    public static String createResourceMessage(Map<String, Integer> resources) {
        return "Ресурсы, которые у вас есть: " +
                "\nЕда: " + resources.get("Food") + "\n" +
                "Дерево: " + resources.get("Wood") + "\n" +
                "Золото: " + resources.get("Gold") + "\n" +
                "Камень: " + resources.get("Stone") + "\n";

    }

    public static boolean checkResourcesOnSpending(Map<String, Integer> resources, Map<String, Integer> expendedResources) {
        Set<String> resourcesKeys = resources.keySet();
        for (Object temp : resourcesKeys) {
            if (resources.get(temp) < expendedResources.get(temp)) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, Integer> updateResources(Map<String, Integer> resources, Map<String, Integer> expendedResources, Integer flagUpdate) {
        Map<String, Integer> updatedResources = new HashMap<>();
        Set<String> resourcesKeys = resources.keySet();
        Iterator<String> iterator = resourcesKeys.iterator();
        if (flagUpdate.equals(0)) {
            while (iterator.hasNext()) {
                String temp = iterator.next();
                Integer value = resources.get(temp) - expendedResources.get(temp);
                updatedResources.put(temp, value);
            }
        } else {
            while (iterator.hasNext()) {
                String temp = iterator.next();
                Integer value = resources.get(temp) + expendedResources.get(temp);
                updatedResources.put(temp, value);
            }
        }
        return updatedResources;
    }
}
