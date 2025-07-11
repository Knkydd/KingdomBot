package TelegramBot.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
}
