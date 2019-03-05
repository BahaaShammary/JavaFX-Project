import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class LottoCureTab extends Tab implements EventHandler {
    Button attemptBtn, exitBtn;
    private LottoCure control;
    private static TextArea attemptField;
    private String userAttempt;

    public LottoCureTab() {
        setStyle("-fx-background-color: #7B85AD; -fx-text-inner-color: #172457;");
        setClosable(false);
        control = new LottoCure();

        Text text = new Text();
        text.setFont(Font.font("verdana", FontPosture.REGULAR, 20));
        //setting the position of the text
        text.setX(50);
        text.setY(130);
        text.setText("Play the lotto");
        text.setFill(Color.web("#303D74"));

        Label label = new Label("> Guessing 4 out of 5 numbers wins a 4 star prize");
        Label label1 = new Label("> Guessing 5 out of 5 numbers wins a 5 star prize");

        setText("Lotto Cure");
        attemptField = new TextArea();
        attemptField.setPromptText("Enter a 5 digit number please");
        attemptField.setPrefWidth(60);
        attemptField.setPrefHeight(100);

        attemptBtn = new Button();
        attemptBtn.setText("Guess");
        attemptBtn.setOnAction(this);
        attemptBtn.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");


        exitBtn = new Button();
        exitBtn.setText("Exit Game");
        exitBtn.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");
        exitBtn.setOnAction(event -> System.exit(0));

        VBox layout = new VBox(10);
        HBox layout1 = new HBox();
        layout1.getChildren().addAll(attemptBtn, exitBtn);
        layout.getChildren().addAll(text, label, label1, attemptField, layout1);

        setContent(layout);
    }

    public void handle(Event event) {
        try {
            if (event.getSource() == attemptBtn) {
                userAttempt = attemptField.getText();
                System.out.println(userAttempt);
                control.play(userAttempt, attemptField);
            }
        } catch (Exception e) {
            attemptField.setText("Please only enter 5 digit number");
        }
    }

}
