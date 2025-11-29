package TelegramBot.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
    Чтение из файла с данными от бд, располагать в таком порядке:
    host
    port
    имя_бд
    имя_пользователя
    пароль
 */
public class DatabaseConfigReader {
    public static ArrayList<String> read(){
        ArrayList<String> dbConfig = new ArrayList<>();
        try{
            Scanner scan = new Scanner(new File("./db_data.txt"));
            while(scan.hasNext()){
                dbConfig.add(scan.next());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return dbConfig;
    }
    public static HashMap<String, Object> readingConfigDB(){
        HashMap<String, Object> configDatabased = new HashMap<>();
        try {
            Object j = new JSONParser().parse(new FileReader("D:\\projects\\java\\KingdomBot\\db_data.json"));
            JSONObject json = (JSONObject) j;
            configDatabased.put("host", json.get("host"));
            configDatabased.put("port", json.get("port"));
            configDatabased.put("name_db", json.get("name_db"));
            configDatabased.put("user", json.get("user"));
            configDatabased.put("password", json.get("password"));

        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return configDatabased;
    }
}