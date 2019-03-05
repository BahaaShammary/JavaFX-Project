import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class LottoCure {
    private int[] lotteryRandomNumbers;
    private Random  randomNum;
    private int     correctNumbers;
    private String[] userListString;
    private int[]   userListInt;
    private String userGuess;
    private Boolean more = true;
    private String randomNumList = "";
    private String userGuessList = "";

    public LottoCure() {
        lotteryRandomNumbers = new int[5];
        randomNum = new Random();
        for (int i = 0; i < lotteryRandomNumbers.length; i++) { // Creating the lottery numbers
            lotteryRandomNumbers[i] = randomNum.nextInt(10);
            System.out.print(lotteryRandomNumbers[i] + " ");

        }
    }

    public void play(String guess, TextArea attemptField) {
        correctNumbers = 0;
        userGuess = guess;
        userListString = userGuess.split("\\B");
        userListInt = new int[userListString.length];
        for (int i = 0; i < userListString.length; i++) {
            userListInt[i] = Integer.parseInt(userListString[i]);
        }

        for (int i = 0; i < lotteryRandomNumbers.length; i++) {
            if (userListInt[i] == lotteryRandomNumbers[i]) {
                correctNumbers++;
            }
        }
        if (correctNumbers < 4) {
            attemptField.setPromptText("You only guessed " + correctNumbers + " numbers correct");
            writeToFile(0);
            attemptField.setText("");

        } else if (correctNumbers >= 4) {
            attemptField.setPromptText("Congratulations you guessed " + correctNumbers + " numbers from the lottery");
            attemptField.setStyle("-fx-text-inner-color: #172457;");
            attemptField.setText("");

            if (correctNumbers == 4) {
                writeToFile(4);
                AlertBox.display("Lotto Cure", "You won a 4 star prize");

            } else {
                AlertBox.display("Lotto Cure", "You won a 5 star prize");
                writeToFile(5);

            }
            more = false;
            for (int i = 0; i < lotteryRandomNumbers.length; i++) {
                randomNumList += lotteryRandomNumbers[i] + " ";

            }
            System.out.println();
            for (int i = 0; i < userListInt.length; i++) {
                userGuessList += userListInt[i] + " ";
            }
        }


    }
    public void writeToFile(int toWrite) {
        try(FileWriter fw = new FileWriter("lottocure.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(toWrite);

        } catch (IOException e) {
        }
    }

}
