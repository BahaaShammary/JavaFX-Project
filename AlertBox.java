import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class AlertBox {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(340);
        window.setHeight(100);

        Label label = new Label();
        label.setText(message);
        label.setFont(Font.font("verdana", FontPosture.REGULAR, 13));

        Button closeBtn = new Button("Great!");
        closeBtn.setStyle("-fx-background-color: #303D74; -fx-text-fill: WHITE;");
        closeBtn.setOnAction(event -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeBtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
