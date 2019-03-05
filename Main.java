import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;

import java.io.File;
import java.io.FileWriter;


public class Main extends Application {
    Stage window;
    //int check = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // SETTING UP THE FILES FOR PRIZE TRACKING:
            File file = new File("guessgame.txt");
            File file1 = new File("lottocure.txt");

            // TO ERASE PREVIOUS DATA on guessgame file:
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();

            // TO ERASE PREVIOUS DATA on lottocure file:
            FileWriter writer1 = new FileWriter(file1);
            writer1.write("");
            writer.close();
            //new JMetro(JMetro.Style.LIGHT).applyTheme(root);

            window = primaryStage;
            window.getIcons().add(new Image("icon.png"));

            // SETTING UP TABS
            TabPane layout10 = new TabPane();

            GuessTab firstGame = new GuessTab();
            LottoCureTab secondGame = new LottoCureTab();
            PrizesTab prizesTab = new PrizesTab();



            layout10.getTabs().add(firstGame);
            layout10.getTabs().add(secondGame);


            layout10.getTabs().add(prizesTab);
            layout10.setStyle("-fx-background-color: #7B85AD; -fx-font-size: 11pt;");


            window.setTitle("Games and Prizes");

            Scene scene = new Scene(layout10, 500, 280);

            window.setScene(scene);

            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
