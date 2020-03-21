package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//represents the Alert Boxes for the GUI
public class AlertBox {

    // MODIFIES: this
    // EFFECTS: this sets a display window with a given title and message
    // REFERENCE: https://youtu.be/SpL3EToqaXA
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality((Modality.APPLICATION_MODAL));
        window.setTitle(title);
        window.setMinWidth(300);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("_Close the window");
        closeButton.setOnAction(e -> window.close());
        closeButton.setMnemonicParsing(true);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5,5,5,5));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    // MODIFIES: this
    // EFFECTS: sets a display window with a title and a given message in multiple lines using a textarea
    // REFERENCE: https://youtu.be/SpL3EToqaXA
    public static void multiDisplay(String title, String message) {
        Stage window = new Stage();

        window.initModality((Modality.APPLICATION_MODAL));
        window.setTitle(title);
        window.setMinWidth(300);

        TextArea textArea = new TextArea();
        textArea.setText(message);
        Button closeButton = new Button("_Close the window");
        closeButton.setOnAction(e -> window.close());
        closeButton.setMnemonicParsing(true);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(textArea, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
