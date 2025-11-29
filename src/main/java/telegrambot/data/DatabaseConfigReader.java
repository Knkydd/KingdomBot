package telegrambot.data;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@Slf4j
public class DatabaseConfigReader {
    public static HashMap<String, Object> readingConfigDB(){
        var configDatabased = new HashMap<String, Object>();
        try {
            Object j = new JSONParser().parse(new FileReader("D:\\projects\\java\\KingdomBot\\db_data.json"));
            JSONObject json = (JSONObject) j;
            configDatabased.put("host", json.get("host"));
            configDatabased.put("port", json.get("port"));
            configDatabased.put("name_db", json.get("name_db"));
            configDatabased.put("user", json.get("user"));
            configDatabased.put("password", json.get("password"));

        } catch (IOException | ParseException e){
            log.error("Error! Read config for database failed. Exception: {}", e.getMessage());
        }
        return configDatabased;
    }
}