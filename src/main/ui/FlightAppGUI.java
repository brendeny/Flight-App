package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.*;
import java.util.List;
import java.util.Scanner;

// represents the GUI for the flight app
public class FlightAppGUI extends Application {
    private Button viewFlightListButton;
    private Button addSingleFlightButton;
    private Button removeFirstFlightButton;
    private Button showFirstFlightButton;
    private Button howManyFlightsButton;
    private Button clearFlightListButton;
    private Button saveButton;
    private Button loadButton;
    private Button imagePlane;


    public static final String FLIGHTS_FILE = "./data/flights.txt";
    protected Scanner input;
    protected Flight newFlight1;
    protected FlightList currentFlightSchedule;
    protected FlightList tempFlightSchedule;

    private Label flightName;
    private Label flightDate;
    private Label flightTime;
    private TextField inputName;
    private TextField inputDate;
    private TextField inputTime;
    private Button addButton;

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


    // MODIFIES: this
    // EFFECTS: prints out that the program was initialized and initializes the flight schedule and an empty flight
    @Override
    public void init() {
        System.out.println("FlightApp Initiated");
        currentFlightSchedule = new FlightList();
        newFlight1 = new Flight(null, null, null);
    }

    // MODIFIES: this
    // EFFECTS: main GUI method, creates the stage for the application and shows the main application window
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        setStageHelperStart(stage);

        BorderPane root = new BorderPane();

        Label title = new Label();
        title.setText("Welcome to Flight App");
        title.setFont(new Font("Arial", 24));
        root.setTop(title);
        root.setAlignment(title, Pos.TOP_CENTER);
        root.setMargin(title, new Insets(50, 0, 0, 0));

        VBox mainButtonLayout = new VBox();
        buttonMakerHelper();

        imageInsertHelper(root);
        buttonLayoutHelper(mainButtonLayout);
        root.setCenter(mainButtonLayout);

        HBox saveLoadLayout = new HBox();
        saveLoadFormatHelper(saveLoadLayout);
        root.setBottom(saveLoadLayout);

        Scene scene1 = new Scene(root);
        stage.setScene(scene1);

        stage.show();
    }

    // MODIFIES: this
    // EFFECTS: initializes main menu buttons, sets their actions, and calls the mnemonic helper
    public void buttonMakerHelper() {
        viewFlightListButton = new Button("_1. Show Flight List");
        addSingleFlightButton = new Button("_2. Add New Flight");
        removeFirstFlightButton = new Button("_3. Remove First Flight");
        showFirstFlightButton = new Button("_4. Show First Flight");
        howManyFlightsButton = new Button("_5. Show How Many Flights on Schedule");
        clearFlightListButton = new Button("_6. Clear Current Flight List");
        saveButton = new Button("_Save Flight List");
        loadButton = new Button("_Load Last Flight List");

        viewFlightListButton.setOnAction(e -> tableWindow("Flight Schedule"));
        addSingleFlightButton.setOnAction(e -> doAddFlight());
        removeFirstFlightButton.setOnAction(e -> doRemoveFlight());
        showFirstFlightButton.setOnAction(e -> doShowFirstFlight());
        howManyFlightsButton.setOnAction(e -> doHowManyFlights());
        clearFlightListButton.setOnAction(e -> clearList());
        saveButton.setOnAction(e -> saveFlights());
        loadButton.setOnAction(e -> loadFlights());

        buttonMnemonicHelper();
    }

    // MODIFIES: this
    // EFFECTS: sets the mnemonic parsing to be true for main menu buttons
    public void buttonMnemonicHelper() {
        viewFlightListButton.setMnemonicParsing(true);
        addSingleFlightButton.setMnemonicParsing(true);
        removeFirstFlightButton.setMnemonicParsing(true);
        showFirstFlightButton.setMnemonicParsing(true);
        howManyFlightsButton.setMnemonicParsing(true);
        clearFlightListButton.setMnemonicParsing(true);
        saveButton.setMnemonicParsing(true);
        loadButton.setMnemonicParsing(true);
    }

    // EFFECTS: button helper method that adds the main menu buttons and adjust the formatting
    public void buttonLayoutHelper(VBox v) {
        v.getChildren().addAll(imagePlane,
                viewFlightListButton,
                addSingleFlightButton,
                removeFirstFlightButton,
                showFirstFlightButton,
                howManyFlightsButton,
                clearFlightListButton);
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates an image and assigns it to a button and plays a media sound when button is pressed
    public void imageInsertHelper(BorderPane b) throws FileNotFoundException {
        //load the image
        //Source: https://www.flaticon.com/free-icon/plane_1983892?term=airplane&page=3&position=96
        Image planeIcon = new Image(new FileInputStream("src/main/ui/media/plane.png"));

        //displays the ImageView the image as is
        ImageView iv1 = new ImageView(planeIcon);
        iv1.setImage(planeIcon);
        iv1.setFitWidth(100);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        //Media sound = new Media(new File("src/main/ui/media/airstrike.wav").toURI().toString());
        //MediaPlayer mediaPlayer = new MediaPlayer(sound);

        imagePlane = new Button();
        imagePlane.setGraphic(iv1);
        //imagePlane.setOnAction(e ->
        //        mediaPlayer.play());
    }

    // EFFECTS: formats the save and load buttons
    public void saveLoadFormatHelper(HBox h) {
        h.getChildren().addAll(saveButton, loadButton);
        h.setSpacing(110);
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(5, 5, 5, 5));
    }

    // EFFECTS: formats the stage
    public void setStageHelperStart(Stage s) {
        s.setTitle("FlightApp");
        s.setWidth(350);
        s.setHeight(575);
    }


    // EFFECTS: prints in console that the application was closed
    @Override
    public void stop() {
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
            AlertBox.display("First Flight", "Your current flight schedule is empty.");
        } else {
            ff1 = currentFlightSchedule.showFirst();
            AlertBox.multiDisplay("First Flight",
                    "Here is the first flight on your current schedule:\n\n"
                            + "Flight name/number: "
                            + ff1.getFlightName()
                            + "\nFlight date: "
                            + ff1.getFlightDate()
                            + "\nFlight departure time: "
                            + ff1.getDepartureTime());
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the first flight from the flight schedule
    private void doRemoveFlight() {
        if (currentFlightSchedule.isEmpty()) {
            AlertBox.display("Remove First Flight", "Your current flight schedule is empty.");
        } else {
            currentFlightSchedule.removeFirstFlight();
            AlertBox.display("Remove First Flight", "Success! We have removed the first flight from the schedule.");
        }
    }


    //EFFECTS: shows how many flights are currently on the schedule
    private void doHowManyFlights() {
        try {
            AlertBox.display("Number of Flights",
                    "You currently have " + currentFlightSchedule.listSize() + " flight(s) on your schedule.");
        } catch (NullPointerException e) {
            AlertBox.display("Number of Flights",
                    "You do not currently have any flights on your schedule.");
        }
    }

    // MODIFIES: this
    // EFFECTS: clears current Flight List
    private void clearList() {
        currentFlightSchedule = new FlightList();
        AlertBox.display("Removed Flight", "Success! Cleared the current flight list.");
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
            AlertBox.display("Save Successful", "Current flight schedule saved to file " + FLIGHTS_FILE);
        } catch (FileNotFoundException e) {
            AlertBox.display("Save Unsuccessful", "Unable to save current flight schedule to " + FLIGHTS_FILE);
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
            AlertBox.display("Load Successful", "Successfully loaded your last saved flight schedule!");
        } catch (IOException e) {
            initializeFlightList();
        }
    }

    //COMPLEX WINDOWS

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

    // MODIFIES: this
    // EFFECTS: sets up the input boxes for the addWindow method
    public void createInputBoxes() {
        flightName = new Label();
        flightName.setText("Flight name/number: ");
        inputName = new TextField();
        inputName.setMaxSize(400, 20);

        flightDate = new Label();
        flightDate.setText("Flight date: ");
        inputDate = new TextField();
        inputDate.setMaxSize(400, 20);

        flightTime = new Label();
        flightTime.setText("Flight departure time: ");
        inputTime = new TextField();
        inputTime.setMaxSize(400, 20);

        //flightName
        GridPane.setConstraints(flightName, 0, 0);
        GridPane.setConstraints(inputName, 1, 0);
        inputName.setPromptText("ex. AC100");

        //flightDate
        GridPane.setConstraints(flightDate, 0, 1);
        GridPane.setConstraints(inputDate, 1, 1);
        inputDate.setPromptText("mm/dd/yy");

        //flightTime
        GridPane.setConstraints(flightTime, 0, 2);
        GridPane.setConstraints(inputTime, 1, 2);
        inputTime.setPromptText("xx:xx");
    }

    // MODIFIES: this
    // EFFECTS: implementation for the add flight button
    public void clickAddFlight(String flightName, String flightDate, String flightTime) {
        newFlight1 = new Flight(null, null, null);
        newFlight1.setFlightName(flightName);
        newFlight1.setFlightDate(flightDate);
        newFlight1.setDepartureTime(flightTime);
        currentFlightSchedule.addFlight(newFlight1);
        AlertBox.display("Added Flight", "Success! The flight has been added to your schedule.");
    }

    // MODIFIES: this
    // EFFECTS: creates the observable list with the flights that will be in the table
    public ObservableList<Flight> getFlight() {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        for (int i = 0; i < currentFlightSchedule.listSize(); i++) {
            flights.add(currentFlightSchedule.getFlight(i));
        }
        return flights;
    }

    // MODIFIES: this
    // EFFECTS: creates the table window where the flight schedule will be shown to users
    //          has an add and remove button for modification of the schedule
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
        flightDateInput.setPromptText("mm/dd/yy");
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
    public void addFlightButtonClicked() {
        if (flightNameInput.getText().equals("")
                || flightDateInput.getText().equals("")
                || flightTimeInput.getText().equals("")) {
            AlertBox.display("Error", "Flight entered is missing information.");
        } else {
            Flight newFlight = new Flight(flightNameInput.getText(),
                    flightDateInput.getText(),
                    flightTimeInput.getText());

            currentFlightSchedule.addFlight(newFlight);
            flightTable.getItems().add(newFlight);
            flightNameInput.clear();
            flightDateInput.clear();
            flightTimeInput.clear();
        }
    }

    // MODIFIES: this
    // EFFECTS: implementation of the remove button in the table, removes the flights that are selected in the table
    public void removeFlightButtonClicked() {
        ObservableList<Flight> flightSelected;
        ObservableList<Flight> allFlights;
        allFlights = flightTable.getItems();
        flightSelected = flightTable.getSelectionModel().getSelectedItems();

        for (Flight f : flightSelected) {
            for (int i = 0; i < currentFlightSchedule.listSize(); i++) {
                if (f.equals(currentFlightSchedule.getFlight(i))) {
                    currentFlightSchedule.removeFlight(f);
                }
            }
        }
        flightSelected.forEach(allFlights::remove);
    }

}
