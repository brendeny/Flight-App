package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Flight;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// represents the class that creates the Show Flight List window and contains methods related to that window
public class ShowFlightList {
    Button addFlightButton;
    Button removeFlightButton;
    TableView<Flight> flightTable;
    TableColumn<Flight, String> flightNameColumn;
    TableColumn<Flight, String> flightDateColumn;
    TableColumn<Flight, String> flightTimeColumn;
    TextField flightNameInput;
    TextField flightDateInput;
    TextField flightTimeInput;
    Label flightNameLabel;
    Label flightDateLabel;
    Label flightTimeLabel;

    boolean correctDateFormat;
    boolean correctDepartureTimeFormat;

    //EFFECTS: constructor for ShowFlightList
    public ShowFlightList() {
    }


    // MODIFIES: this
    // EFFECTS: creates the observable list with the flights that will be in the table
    public ObservableList<Flight> getFlight() {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        for (int i = 0; i < FlightAppGUI.getCurrentFlightSchedule().listSize(); i++) {
            flights.add(FlightAppGUI.getCurrentFlightSchedule().getFlight(i));
        }
        return flights;
    }

    // MODIFIES: this
    // EFFECTS: creates the table window where the flight schedule will be shown to users
    //          has an add and remove button for modification of the schedule
    // REFERENCE: https://youtu.be/sYga9AxY72M
    public void tableWindow(String title) {
        Stage window = new Stage();
        window.initModality((Modality.APPLICATION_MODAL));
        window.setTitle(title);

        columnMakerHelper();
        inputCreator();
        labelMakerHelper();

        addRemoveButtonHelper();

        HBox hbox = new HBox();
        inputTableHelper(hbox, addFlightButton, removeFlightButton);

        HBox hbox2 = new HBox();
        hbox2.setSpacing(95);
        hbox2.setPadding(new Insets(0, 15, 0, 10));
        hbox2.getChildren().addAll(flightNameLabel, flightDateLabel, flightTimeLabel);

        flightTableSetupHelper();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(flightTable, hbox2, hbox);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    // MODIFIES: this
    // EFFECTS: sets up the add remove button for the table window
    public void addRemoveButtonHelper() {

        //Add/Delete Buttons
        addFlightButton = new Button("_Add");
        removeFlightButton = new Button("_Remove");
        addFlightButton.setMnemonicParsing(true);
        removeFlightButton.setMnemonicParsing(true);

        //Button implementation
        addFlightButton.setOnAction(e -> addFlightButtonClicked());
        removeFlightButton.setOnAction(e -> removeFlightButtonClicked());
    }

    // MODIFIES: this
    // EFFECTS: sets up the columns for the table
    public void columnMakerHelper() {
        //Flight Name column
        flightNameColumn = new TableColumn<>("Flight Name");
        flightNameColumn.setMinWidth(200);
        flightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));

        //Flight Date column
        flightDateColumn = new TableColumn<>("Flight Date");
        flightDateColumn.setMinWidth(200);
        flightDateColumn.setCellValueFactory(new PropertyValueFactory<>("flightDate"));

        //Flight Time column
        flightTimeColumn = new TableColumn<>("Flight Time");
        flightTimeColumn.setMinWidth(200);
        flightTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
    }

    // MODIFIES: this
    // EFFECTS: sets up the input boxes for the table
    public void inputCreator() {
        //Flight Name Input
        flightNameInput = new TextField();
        flightNameInput.setPromptText("ex. AC100");
        flightNameInput.setMinWidth(100);

        //Flight Date Input
        flightDateInput = new TextField();
        flightDateInput.setPromptText(FlightAppGUI.datePrompt);
        flightDateInput.setMinWidth(100);

        //Flight Time Input
        flightTimeInput = new TextField();
        flightTimeInput.setPromptText("xx:xx");
        flightTimeInput.setMinWidth(100);
    }

    // MODIFIES: this
    // EFFECTS: sets up the labels for the table inputs
    public void labelMakerHelper() {
        flightNameLabel = new Label("Flight Name:");
        flightDateLabel = new Label("Flight Date:");
        flightTimeLabel = new Label("Flight Time:");
    }

    // MODIFIES: this
    // EFFECTS: helper to setup the columns and items for the table
    public void flightTableSetupHelper() {
        flightTable = new TableView<>();
        flightTable.setItems(getFlight());
        flightTable.getColumns().addAll(flightNameColumn, flightDateColumn, flightTimeColumn);
    }

    // MODIFIES: this
    // EFFECTS: helper for formatting the add remove buttons and the input boxes
    public void inputTableHelper(HBox h, Button b1, Button b2) {
        h.setPadding(new Insets(0, 10, 10, 10));
        h.setSpacing(10);
        h.getChildren().addAll(flightNameInput,
                flightDateInput,
                flightTimeInput,
                b1,
                b2);
    }

    // MODIFIES: this
    // EFFECTS: implementation for the add button in the table, adds flight if all info is typed out
    // REFERENCE: https://youtu.be/uz2sWCnTq6E
    public void addFlightButtonClicked() {
        formatChecker(flightDateInput.getText(), flightTimeInput.getText());
        if (flightNameInput.getText().equals("")
                || flightDateInput.getText().equals("")
                || flightTimeInput.getText().equals("")) {
            AlertBox.display("Error", "Flight entered is missing information.");
        } else if (!correctDateFormat) {
            AlertBox.display("Error", "Flight Date entered is the wrong format.");
        } else if (!correctDepartureTimeFormat) {
            AlertBox.display("Error", "Flight Time entered is the wrong format.");
        } else {
            Flight newFlight = new Flight(flightNameInput.getText(),
                    flightDateInput.getText(),
                    flightTimeInput.getText());

            FlightAppGUI.getCurrentFlightSchedule().addFlight(newFlight);
            flightTable.getItems().add(newFlight);
            flightNameInput.clear();
            flightDateInput.clear();
            flightTimeInput.clear();
        }
    }

    // MODIFIES: this
    // EFFECTS: checks whether the format for the date and departure time are correct
    public void formatChecker(String s1, String s2) {
        String regex1 = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern patternDate = Pattern.compile(regex1);
        Matcher matcherDate = patternDate.matcher(s1);
        correctDateFormat = matcherDate.matches();

        String regex2 = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern patternDepartureTime = Pattern.compile(regex2);
        Matcher matcherDepartureTime = patternDepartureTime.matcher(s2);
        correctDepartureTimeFormat = matcherDepartureTime.matches();
    }

    // MODIFIES: this
    // EFFECTS: implementation of the remove button in the table, removes the flights that are selected in the table
    // REFERENCE: https://youtu.be/uz2sWCnTq6E
    public void removeFlightButtonClicked() {
        ObservableList<Flight> flightSelected;
        ObservableList<Flight> allFlights;
        allFlights = flightTable.getItems();
        flightSelected = flightTable.getSelectionModel().getSelectedItems();

        for (Flight f : flightSelected) {
            for (int i = 0; i < FlightAppGUI.getCurrentFlightSchedule().listSize(); i++) {
                if (f.equals(FlightAppGUI.getCurrentFlightSchedule().getFlight(i))) {
                    FlightAppGUI.getCurrentFlightSchedule().removeFlight(f);
                }
            }
        }
        flightSelected.forEach(allFlights::remove);
    }
}
