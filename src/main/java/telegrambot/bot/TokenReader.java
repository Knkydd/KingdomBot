package telegrambot.bot;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Scanner;

@Slf4j
public class TokenReader {
    public static String readToken() {
        try (Scanner scan = new Scanner(new File("./Token.txt"))) {
            return scan.nextLine();
        } catch (IOException e) {
            log.error("Error with read BotToken. Exception: {}", e.getMessage());
        }
        return null;
    }
}