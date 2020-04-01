package ui;

import exceptions.DateFormat;
import exceptions.DepartureTimeFormat;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Flight;

// represents the class that creates the Add NeW Flight window and contains methods related to that window
public class AddNewFlight {

    protected Label flightName;
    protected Label flightDate;
    protected Label flightTime;
    protected TextField inputName;
    protected TextField inputDate;
    protected TextField inputTime;
    protected Button addButton;
    protected Flight newFlight1;

    private int maxHeightInputBox = 20;
    private int maxMaxWidthInputBox = 400;

    //EFFECTS: constructor for AddNewFlight
    public AddNewFlight() {
    }

    // MODIFIES: this
    // EFFECTS: creates the add flight window which allows users to add flights to the current flight list
    public void addWindow(String title) {
        Stage window = new Stage();

        window.initModality((Modality.APPLICATION_MODAL));
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(180);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        createInputBoxes();

        //Button for adding
        addButton = new Button("Add Flight");
        GridPane.setConstraints(addButton, 1, 3);
        addButton.setOnAction(e -> addButtonAction());

        grid.getChildren().addAll(flightName, inputName, flightDate, inputDate, flightTime, inputTime, addButton);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }

    // EFFECTS: sets action of add button, otherwise if the input boxes are blank, display an alert box
    public void addButtonAction() {
        if (inputName.getText().equals("")
                || inputDate.getText().equals("")
                || inputTime.getText().equals("")) {
            AlertBox.display("Error", "Flight entered is missing information.");
        } else {
            clickAddFlight(inputName.getText(), inputDate.getText(), inputTime.getText());
            inputName.clear();
            inputDate.clear();
            inputTime.clear();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the input boxes for the addWindow method
    public void createInputBoxes() {
        flightName = new Label();
        flightName.setText("Flight name/number: ");
        inputName = new TextField();
        inputName.setMaxSize(maxMaxWidthInputBox, maxHeightInputBox);

        flightDate = new Label();
        flightDate.setText("Flight date: ");
        inputDate = new TextField();
        inputDate.setMaxSize(maxMaxWidthInputBox, maxHeightInputBox);

        flightTime = new Label();
        flightTime.setText("Flight departure time: ");
        inputTime = new TextField();
        inputTime.setMaxSize(maxMaxWidthInputBox, maxHeightInputBox);

        //flightName
        GridPane.setConstraints(flightName, 0, 0);
        GridPane.setConstraints(inputName, 1, 0);
        inputName.setPromptText("ex. AC100");

        //flightDate
        GridPane.setConstraints(flightDate, 0, 1);
        GridPane.setConstraints(inputDate, 1, 1);
        inputDate.setPromptText(FlightAppGUI.datePrompt);

        //flightTime
        GridPane.setConstraints(flightTime, 0, 2);
        GridPane.setConstraints(inputTime, 1, 2);
        inputTime.setPromptText("xx:xx");
    }


    // MODIFIES: this
    // EFFECTS: implementation for the add flight button
    public void clickAddFlight(String flightName, String flightDate, String flightTime) {
        try {
            newFlight1 = new Flight(null, null, null);
            newFlight1.setFlightName(flightName);
            newFlight1.setFlightDate(flightDate);
            newFlight1.setDepartureTime(flightTime);
            FlightAppGUI.getCurrentFlightSchedule().addFlight(newFlight1);
            AlertBox.display("Added Flight", "Success! The flight has been added to your schedule.");
        } catch (DateFormat e) {
            AlertBox.display("Unsuccessful", "The date format is incorrect");
        } catch (DepartureTimeFormat e2) {
            AlertBox.display("Unsuccessful", "The departure time format is incorrect");
        }
    }


}
