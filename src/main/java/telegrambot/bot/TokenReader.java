package telegrambot.bot;

import java.io.*;
import java.util.Scanner;

public class TokenReader {
    public static String readToken() {
        try (Scanner scan = new Scanner(new File("./Token.txt"))) {
            return scan.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}