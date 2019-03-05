import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class GuessGame extends Tab {
    private Random random;
    private int randomNum;
    private int counter;
    private Boolean correct;
    private File file;
    private FileReader fr;
    private BufferedReader br;
    private String line;
    private String message;


    public GuessGame() {
        file = new File("prizes.txt");
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            random = new Random();
            randomNum = random.nextInt(101);
            System.out.println("Your random number is " + randomNum);
            counter = 0;
            correct = false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

        public void playGame(int guess, TextArea guessField) {
        if (this.counter < 4) {
            guessField.setPromptText("please enter an positive number between 1 and 100 inclusive: ");
            guessField.setText("");
            this.counter++;

            if (guess < 1 || guess > 100) {
                guessField.setPromptText("Invalid input");
                guessField.setText("");
                this.counter--;
            } else if (guess == randomNum) {
                guessField.setText("Congratulations you won! Your number of tries was: " + counter);
                guessField.setStyle("-fx-text-inner-color: #172457;");
                writeToFile(1);
                int wins = readGuessGameFile();
                int attempts = 5 - wins;
                if (attempts <= 0) {
                    AlertBox.display("Guess Game", "Well done! Go to Prizes tab and collect your prize");
                }
                else {
                    AlertBox.display("Guess Game", "You need to win the game " + attempts + " more times");
                }
                correct = true;
            }
            else if (guess < randomNum) {
                guessField.setPromptText("Your guess is too low!");
                guessField.setStyle("-fx-text-inner-color: #172457;");
                guessField.setText("");
            }
            else if (guess > randomNum) {
                guessField.setPromptText("Your guess is too high!");
                guessField.setStyle("-fx-text-inner-color: #172457;");
                guessField.setText("");
            }
        }

        if (counter == 4) {
            guessField.setText("You lose! You had 4 attempts - Press New Game to start again");
            writeToFile(0);
        }
    }

    void resetGame(GuessTab x) {
        x.reSetTextField();
        x.reSetGame();
    }

    public void writeToFile(int toWrite) {
        try(FileWriter fw = new FileWriter("guessgame.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(toWrite);

        } catch (IOException e) {
        }
    }
    public int readGuessGameFile() {
        int toReturn = 0;
        ArrayList<Integer> guessGameList = new ArrayList<>();
        file = new File("guessgame.txt");
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                guessGameList.add(Integer.parseInt(line));
            }
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        for (int i=0;i<guessGameList.size();i++) {
            toReturn+= guessGameList.get(i);
        }
        return toReturn; // returning total number of ones in the guess game prize file
    }

}


