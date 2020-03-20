package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Flight;
import model.FlightList;
import persistence.Reader;
import persistence.Writer;
import ui.FlightApp;
import persistence.*;

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class FlightAppGUI extends Application {
    private Button a;
    private Button b;
    private Button c;
    private Button d;
    private Button e;
    private Button f;
    private Button s;
    private Button l;

    public static final String FLIGHTS_FILE = "./data/flights.txt";
    protected Scanner input;
    protected Flight newFlight1;
    protected FlightList currentFlightSchedule;
    protected FlightList tempFlightSchedule;

    private  Label flightName;
    private  Label flightDate;
    private  Label flightTime;
    private  TextField inputName;
    private  TextField inputDate;
    private  TextField inputTime;
    private  Button addButton;

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


    // EFFECTS: prints out that the program was initialized
    @Override
    public void init() throws Exception {
        System.out.println("FlightApp Initiated");
        currentFlightSchedule = new FlightList();
        newFlight1 = new Flight(null, null, null);
    }

    // EFFECTS: creates the stage for the application and shows the new window
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("FlightApp");
        stage.setWidth(350);
        stage.setHeight(500);

        BorderPane root = new BorderPane();

        Label title = new Label();
        title.setText("Welcome to Flight App");
        title.setFont(new Font("Arial", 24));
        root.setTop(title);
        root.setAlignment(title, Pos.TOP_CENTER);
        root.setMargin(title, new Insets(50,0,0,0));

       /* //load the image
        //Source: https://www.flaticon.com/free-icon/plane_1983892?term=airplane&page=3&position=96
        Image planeIcon = new Image(new FileInputStream("src/main/ui/media/plane.png"));

        //displays the ImageView the image as is
        ImageView iv1 = new ImageView();
        iv1.setImage(planeIcon);
        iv1.setFitWidth(100);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        root.setBottom(iv1);
        root.setAlignment(iv1, Pos.TOP_CENTER);
        root.setMargin(iv1, new Insets(100,0,0,0));*/

        VBox leftButtons = new VBox();
        a = new Button("1. Show Flight List");
        b = new Button("2. Add New Flight");
        c = new Button("3. Remove First Flight");
        d = new Button("4. Show First Flight");
        e = new Button("5. Show How Many Flights on Schedule");
        f = new Button("6. Clear Current Flight List");
        s = new Button("Save Flight List");
        l = new Button("Load Last Flight List");

        leftButtons.getChildren().addAll(a, b, c, d, e, f);
        leftButtons.setSpacing(20);
        root.setCenter(leftButtons);
        leftButtons.setAlignment(Pos.CENTER);

        a.setOnAction(e -> tableWindow("Flight Schedule"));
        b.setOnAction(e -> doAddFlight());
        c.setOnAction(e -> doRemoveFlight());
        d.setOnAction(e -> doShowFirstFlight());
        e.setOnAction(e -> doHowManyFlights());
        f.setOnAction(e -> clearList());
        s.setOnAction(e -> saveFlights());
        l.setOnAction(e -> loadFlights());

        /*TextField input = new TextField();
        root.setCenter(input);
        input.setMaxSize(400,20);*/

        HBox saveLoadLayout = new HBox();
        saveLoadLayout.getChildren().addAll(s, l);
        saveLoadLayout.setSpacing(110);
        saveLoadLayout.setAlignment(Pos.CENTER);
        saveLoadLayout.setPadding(new Insets(5,5,5,5));
        root.setBottom(saveLoadLayout);

        Scene scene1 = new Scene(root);
        stage.setScene(scene1);

        stage.show();
    }


    // EFFECTS: prints in console that the application was closed
    @Override
    public void stop() throws Exception {
        System.out.println("Closing FlightApp. Goodbye!");
    }

    //METHODS FOR FLIGHTLIST

    //MODIFIES: this
    //EFFECTS: adds a flight to the flight schedule
    private void doAddFlight() {
        addWindow("Add New Flight");
    }

    //MODIFIES: this
    //EFFECTS: shows first flight on list
    private void doShowFirstFlight() {
        Flight ff1;
        if (currentFlightSchedule.isEmpty()) {
            AlertBox.display("First Flight","Your current flight schedule is empty.");
        } else {
            ff1 = currentFlightSchedule.showFirst();
            AlertBox.multiDisplay("First Flight", "Here is the first flight on your current schedule:\n\n" + "Flight name/number: " + ff1.getFlightName() + "\nFlight date: " + ff1.getFlightDate() + "\nFlight departure time: " + ff1.getDepartureTime());
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the first flight from the flight schedule
    private void doRemoveFlight() {
        if (currentFlightSchedule.isEmpty()) {
            AlertBox.display("Remove First Flight","Your current flight schedule is empty.");
        } else {
            currentFlightSchedule.removeFirstFlight();
            AlertBox.display("Remove First Flight","Success! We have removed the first flight from the schedule.");
        }
    }


    //EFFECTS: shows how many flights are currently on the schedule
    private void doHowManyFlights() {
        try {
            AlertBox.display("Number of Flights", "You currently have " + currentFlightSchedule.listSize() + " flight(s) on your schedule.");
        } catch (NullPointerException e) {
            AlertBox.display("Number of Flights", "You do not currently have any flights on your schedule.");
        }
    }

    // MODIFIES: this
    // EFFECTS: clears current Flight List
    private void clearList() {
        currentFlightSchedule = new FlightList();
        AlertBox.display("Removed Flight","Success! Cleared the current flight list.");
    }

    // MODIFIES: this
    // EFFECTS: initializes Flight List
    private void initializeFlightList() {
        currentFlightSchedule = new FlightList();
    }

    // MODIFIES: this
    // EFFECTS: saves the current list of flights to FLIGHTS_FILE
    private void saveFlights() {
        try {
            Writer writer = new Writer(new File(FLIGHTS_FILE));
            for (int i = 0; i < currentFlightSchedule.listSize(); i++) {
                writer.write(currentFlightSchedule.getFlight(i));
            }
            writer.close();
            AlertBox.display("Save Successful","Current flight schedule saved to file " + FLIGHTS_FILE);
        } catch (FileNotFoundException e) {
            AlertBox.display("Save Unsuccessful","Unable to save current flight schedule to " + FLIGHTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // MODIFIES: this
    // EFFECTS: loads flights from FLIGHTS_FILE, if that file exists;
    // otherwise initializes new flight schedule with default values
    private void loadFlights() {
        try {
            List<Flight> flights = Reader.readFlights(new File(FLIGHTS_FILE));
            initializeFlightList();
            for (int i = 0; i < flights.size(); i++) {
                currentFlightSchedule.addFlight(flights.get(i));
            }
            AlertBox.display("Load Successful","Successfully loaded your last saved flight schedule!");
        } catch (IOException e) {
            initializeFlightList();
        }
    }

    //COMPLEX WINDOWS
    public void addWindow(String title) {
        Stage window = new Stage();

        window.initModality((Modality.APPLICATION_MODAL));
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(180);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        createInputBoxes();

        //Button for adding
        addButton = new Button("Add Flight");
        GridPane.setConstraints(addButton, 1,3);
        addButton.setOnAction(e -> {
            clickAddFlight(inputName.getText(), inputDate.getText(), inputTime.getText());
            inputName.clear();
            inputDate.clear();
            inputTime.clear();
        });

        grid.getChildren().addAll(flightName, inputName, flightDate, inputDate, flightTime, inputTime, addButton);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }

    public void createInputBoxes() {
        flightName = new Label();
        flightName.setText("Flight name/number: ");
        inputName = new TextField();
        inputName.setMaxSize(400,20);

        flightDate = new Label();
        flightDate.setText("Flight date: ");
        inputDate = new TextField();
        inputDate.setMaxSize(400,20);

        flightTime = new Label();
        flightTime.setText("Flight departure time: ");
        inputTime = new TextField();
        inputTime.setMaxSize(400,20);

        //flightName
        GridPane.setConstraints(flightName, 0,0);
        GridPane.setConstraints(inputName, 1, 0);
        inputName.setPromptText("ex. AC100");

        //flightDate
        GridPane.setConstraints(flightDate, 0,1);
        GridPane.setConstraints(inputDate, 1,1);
        inputDate.setPromptText("mm/dd/yy");

        //flightTime
        GridPane.setConstraints(flightTime,0,2);
        GridPane.setConstraints(inputTime,1,2);
        inputTime.setPromptText("xx:xx");
    }

    public void clickAddFlight(String flightName, String flightDate, String flightTime) {
        newFlight1 = new Flight(null, null, null);
        newFlight1.setFlightName(flightName);
        newFlight1.setFlightDate(flightDate);
        newFlight1.setDepartureTime(flightTime);
        currentFlightSchedule.addFlight(newFlight1);
        AlertBox.display("Added Flight", "Success! The flight has been added to your schedule.");
    }

    //Get all of flights
    public ObservableList<Flight> getFlight() {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        for (int i = 0; i < currentFlightSchedule.listSize(); i++) {
            flights.add(currentFlightSchedule.getFlight(i));
        }
        return flights;
    }

    public void tableWindow(String title) {
        Stage window = new Stage();

        window.initModality((Modality.APPLICATION_MODAL));
        window.setTitle(title);

        columnMakerHelper();

        inputCreator();

        labelMakerHelper();

        //Add/Delete Buttons
        Button addFlightButton = new Button("Add");
        Button removeFlightButton = new Button("Remove");

        //Button implementation
        addFlightButton.setOnAction(e -> addFlightButtonClicked());
        removeFlightButton.setOnAction(e -> removeFlightButtonClicked());


        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(flightNameInput, flightDateInput, flightTimeInput, addFlightButton, removeFlightButton);

        HBox hbox2 = new HBox();
        hbox2.setSpacing(95);
        hbox2.setPadding(new Insets(0,15,0,10));
        hbox2.getChildren().addAll(flightNameLabel, flightDateLabel, flightTimeLabel);

        flightTable = new TableView<>();
        flightTable.setItems(getFlight());
        flightTable.getColumns().addAll(flightNameColumn, flightDateColumn, flightTimeColumn);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(flightTable, hbox2, hbox);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

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

    public void inputCreator() {
        //Flight Name Input
        flightNameInput = new TextField();
        flightNameInput.setPromptText("ex. AC100");
        flightNameInput.setMinWidth(100);

        //Flight Date Input
        flightDateInput = new TextField();
        flightDateInput.setPromptText("mm/dd/yy");
        flightDateInput.setMinWidth(100);

        //Flight Time Input
        flightTimeInput = new TextField();
        flightTimeInput.setPromptText("xx:xx");
        flightTimeInput.setMinWidth(100);
    }

    public void labelMakerHelper() {
        flightNameLabel = new Label("Flight Name:");
        flightDateLabel = new Label("Flight Date:");
        flightTimeLabel = new Label("Flight Time:");
    }

    public void addFlightButtonClicked() {
        if (flightNameInput.getText().equals("") || flightDateInput.getText().equals("") || flightTimeInput.getText().equals("")) {
            AlertBox.display("Error", "Flight entered is missing information.");
        } else {
            Flight newFlight = new Flight(flightNameInput.getText(), flightDateInput.getText(), flightTimeInput.getText());
            currentFlightSchedule.addFlight(newFlight);
            flightTable.getItems().add(newFlight);
            flightNameInput.clear();
            flightDateInput.clear();
            flightTimeInput.clear();
        }
    }

    public void removeFlightButtonClicked() {
        ObservableList<Flight> flightSelected;
        ObservableList<Flight> allFlights;
        allFlights = flightTable.getItems();
        flightSelected = flightTable.getSelectionModel().getSelectedItems();

        flightSelected.forEach(allFlights::remove);
    }

}
