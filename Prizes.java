import javafx.scene.control.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Prizes {
    private HashMap<String, String> threeStarPrizes;
    private HashMap<String, String> fourStarPrizes;
    private HashMap<String, String> fiveStarPrizes;
    private File file;
    private FileReader fr;
    private BufferedReader br;
    private String line;
    private static int counter;
    private ArrayList<Integer> prizeWeight;
    private int guessingGamePrize;
    private int lottoCurePrize;

    public Prizes() {
        this.counter = 0;
        prizeWeight = new ArrayList<>();
        threeStarPrizes = new HashMap<>();
        fourStarPrizes = new HashMap<>();
        fiveStarPrizes = new HashMap<>();
        file = new File("prizes.txt");
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);


            while ((line = br.readLine()) != null) {
                String[] splittedString = line.split("  ");
                if ((Integer.parseInt(splittedString[1]) == 3)) {
                    threeStarPrizes.put(splittedString[2], splittedString[0]);
                }
                else if ((Integer.parseInt(splittedString[1]) == 4)) {
                    fourStarPrizes.put(splittedString[2], splittedString[0]);
                }
                else if ((Integer.parseInt(splittedString[1]) == 5)) {
                    fiveStarPrizes.put(splittedString[2], splittedString[0]);
                }
                prizeWeight.add(Integer.parseInt(splittedString[1]));

            }

        } catch (IOException ioex) {
            System.out.println("ERROR");
            System.out.println(ioex.getMessage() + " Error reading file");
            System.exit(0);

        }
    }

    public void choosePrize(String prize, TextArea displayField) {
        guessingGamePrize = readGuessGameFile();
        lottoCurePrize = readLottoCureFile();
        counter = establishCounter(guessingGamePrize, lottoCurePrize);
        //System.out.println("Counter" + counter);
        if (counter > 0) {
            if (guessingGamePrize < 5) {
                displayField.setPromptText("You need to win " + (5 - guessingGamePrize) + " more games to earn a three star prize!");
                displayField.setText("");
                //counter--;
                //writeToFile(0, "guessgame");

            }
            else if (guessingGamePrize >= 5) {
                for (String key : threeStarPrizes.keySet()) {
                    if (key.equals(prize)){
                        displayField.setPromptText("Three Star Prize: your prize is: " + this.threeStarPrizes.get(prize));
                        displayField.setText("");
                        counter--;
                        writeToFile(0, "guessgame");
                    }
                    else {
                        displayField.setPromptText("You have not earned this prize");
                        displayField.setText("");
                    }
                }
            }
            if (guessingGamePrize < 5 && lottoCurePrize == 0) {
                System.out.println(guessingGamePrize);
                System.out.print(lottoCurePrize);
                displayField.setPromptText("You did not win enough games to earn a prize");
                displayField.setText("");
                //counter--; // so this doesn't count as if the user received a prize
            }
            if (lottoCurePrize == 4) {
                for (String key : fourStarPrizes.keySet()) {
                    if (key.equals(prize)) {
                        displayField.setPromptText("Four Star Prize: your prize is: " + this.fourStarPrizes.get(prize));
                        displayField.setText("");
                        writeToFile(0, "lottocure");
                        counter--;
                    }
                    else {
                        displayField.setPromptText("You have not earned this prize");
                        displayField.setText("");
                    }
                }

            }
            if (lottoCurePrize == 5) {
                for (String key : fiveStarPrizes.keySet()) {
                    if (key.equals(prize)) {
                        displayField.setPromptText("Five Star Prize: your prize is: " + this.fiveStarPrizes.get(prize));
                        displayField.setText("");
                        writeToFile(0, "lottocure");
                        counter--;
                    }
                    else {
                        displayField.setPromptText("You have not earned this prize");
                        displayField.setText("");
                    }
                }
            }

            else if (guessingGamePrize == 0 && lottoCurePrize ==0) {
                displayField.setPromptText("You need to win more games to earn new prizes");
                displayField.setText("");
            }

        }
        else if (counter <= 0) {
            displayField.setPromptText("You need to win more games to earn new prizes");
            displayField.setText("");
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

        public int readLottoCureFile() {
            int toReturn = 0;
            ArrayList<Integer> guessGameList = new ArrayList<>();
            file = new File("lottocure.txt");
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
    public int establishCounter(int guessingGameFile, int lottoCureFile) {
        int counter =0;
        if (guessingGameFile >= 5) {
            counter++;
        }
        if ((lottoCureFile == 5 || lottoCureFile == 4)) {
            counter++; // increase the number of prizes the user can get to two because he would have 3 star prize and a 4 or 5 star prize
        }

        return counter;
    }

    public void writeToFile(int num, String fileName) {
        try(FileWriter fw = new FileWriter(fileName+".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(num);


        } catch (IOException e) {
            System.out.println("An error occured when writing to file");
        }
    }

}
