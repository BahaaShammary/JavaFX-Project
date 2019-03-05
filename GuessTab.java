import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class GuessTab extends Tab implements EventHandler {
    Button guessBtn, newGameBtn, exitBtn;
    private GuessGame control;
    private static TextArea guessField;
    private int guess;

    public GuessTab() {
        setStyle("-fx-background-color: #7B85AD; -fx-text-inner-color: #172457;");
        setClosable(false);
        setText("Guess Game");
        control = new GuessGame();

        Text text = new Text();
        text.setFont(Font.font("verdana", FontPosture.REGULAR, 20));
        //setting the position of the text
        text.setX(50);
        text.setY(130);
        text.setText("Guess a number!");
        text.setFill(Color.web("#303D74"));

        Label label = new Label("> Winning the game 5 time in a row wins a 3 Star Prize");

        guessField = new TextArea();
        guessField.setPromptText("Make your guess");
        guessField.setPrefWidth(20);
        guessField.setPrefHeight(70);
        //guessField.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
        guessField.setStyle("-fx-text-inner-color: #172457;");


        guessBtn = new Button();
        guessBtn.setText("Guess");
        guessBtn.setOnAction(this);
        guessBtn.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");


        newGameBtn = new Button();
        newGameBtn.setText("New game");
        newGameBtn.setOnAction(e -> control.resetGame(this));
        newGameBtn.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");


        exitBtn = new Button();
        exitBtn.setText("Exit Game");
        exitBtn.setOnAction(event -> System.exit(0));
        exitBtn.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");


        VBox layout = new VBox(10);
        HBox layout1 = new HBox();
        layout1.getChildren().addAll(guessBtn, newGameBtn, exitBtn);
        layout.getChildren().addAll(text, label, guessField, layout1);

        setContent(layout);


    }

    public void reSetTextField() {
        guessField.setPromptText("Make a guess");
        guessField.setText("");
    }

    public void reSetGame() {
        control = new GuessGame();
    }

    @Override
    public void handle(Event event) {
        try {
            if (event.getSource() == guessBtn) {
                guess = Integer.parseInt(guessField.getText());
                control.playGame(guess, guessField);

            }
        } catch (Exception e) {
            //e.printStackTrace();
            guessField.setPromptText("You entered a wrong character");
            guessField.setText("");
        }
    }



}
