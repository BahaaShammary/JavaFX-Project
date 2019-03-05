import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

public class PrizesTab extends Tab {
    Button choice1, choice2, choice3, choice4, choice5;
    private Prizes control;
    private static TextArea display;

    public PrizesTab() {
        setStyle("-fx-background-color: #7B85AD; -fx-text-inner-color: #172457;");
        setClosable(false);
        control = new Prizes();
        // Text
        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        //setting the position of the text
        text.setX(50);
        text.setY(130);

        text.setText("Choose your prize!");
        text.setFill(Color.web("#303D74"));
        setText("Prizes");

        display = new TextArea();
        display.setPromptText("Your Prize will display here");
        display.setPrefWidth(60);
        display.setPrefHeight(40);

        Label label1 = new Label("Apple");
        choice1 = new Button();
        choice1.setText("Choose");
        choice1.setOnAction(event -> control.choosePrize("Apple", display));
        choice1.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");

        Label label2 = new Label("Grape");
        choice2 = new Button();
        choice2.setText("Choose");
        choice2.setOnAction(event -> control.choosePrize("Grape", display));
        choice2.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");

        Label label3 = new Label("Pear");
        choice3 = new Button();
        choice3.setText("Choose");
        choice3.setOnAction(event -> control.choosePrize("Pear", display));
        choice3.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");

        Label label4 = new Label("Banana");
        choice4 = new Button();
        choice4.setText("Choose");
        choice4.setOnAction(event -> control.choosePrize("Banana", display));
        choice4.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");

        Label label5 = new Label("Grapefruit");
        choice5 = new Button();
        choice5.setText("Choose");
        choice5.setOnAction(event -> control.choosePrize("Grapefruit", display));
        choice5.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");


        VBox layout1 = new VBox(20);
        layout1.getChildren().add(display);

        GridPane layout = new GridPane();
        layout.add(text, 0, 0);
        layout.add(label1, 0, 1);
        layout.add(choice1, 1, 1);
        layout.add(label2, 0, 2);
        layout.add(choice2, 1, 2);
        layout.add(label3, 0, 3);
        layout.add(choice3, 1, 3);
        layout.add(label4, 0, 4);
        layout.add(choice4, 1, 4);
        layout.add(label5, 0, 5);
        layout.add(choice5, 1, 5);
        layout.add(layout1, 0, 7);

        setContent(layout);
    }
}
