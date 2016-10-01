package sample;

import sample.judges.JudgeBase;
import sample.players.PlayerBase;
import sample.players.PlayerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created by ryuta on 16/10/02.
 */
public class MainJanken {
    public static void main(String[] args) throws IOException {
        PlayerBase yamada = PlayerFactory.createOnlyGuTacticsPlayer("山田");
        PlayerBase kume = PlayerFactory.createRandomTacticsPlayer("粂");
        new JudgeBase().startJanken(kume, yamada, getJankenCount());
    }

    private static int getJankenCount() throws IOException {
        System.out.println("何回じゃんけんする?");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String count = "";
            while (!Pattern.matches("^\\d+$", count)) {
                count = reader.readLine();
            }
            return Integer.parseInt(count);
        }
    }
}
