package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.FlightList;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;

// represents the GUI for the flight app
public class FlightAppGUI extends Application {
    public static String datePrompt = "mm/dd/yyyy";

    private Button viewFlightListButton;
    private Button addSingleFlightButton;
    private Button removeFirstFlightButton;
    private Button showFirstFlightButton;
    private Button howManyFlightsButton;
    private Button clearFlightListButton;
    private Button saveButton;
    private Button loadButton;
    private Button imagePlane;

    public static FlightList currentFlightSchedule;

    private AddNewFlight addNewFlight;
    private ShowFlightList showFlightList;
    private FirstFlightActions firstFlightActions;
    private FlightListActions flightListActions;

    //EFFECTS: constructor for FlightAppGUI
    public FlightAppGUI() {
    }


    // MODIFIES: this
    // EFFECTS: prints out that the program was initialized and initializes the flight schedule and an empty flight
    @Override
    public void init() {
        System.out.println("FlightApp Initiated");
        currentFlightSchedule = new FlightList();
        addNewFlight = new AddNewFlight();
        showFlightList = new ShowFlightList();
        firstFlightActions = new FirstFlightActions();
        flightListActions = new FlightListActions();
    }

    // MODIFIES: this
    // EFFECTS: main GUI method, creates the stage for the application and shows the main application window
    // REFERENCE: https://www.youtube.com/playlist?list=PLfu_Bpi_zcDNYL6171Op3S1ABtuyFV7Nr
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
    // REFERENCE: https://youtu.be/3vAT-FpiJKw
    public void buttonMakerHelper() {
        viewFlightListButton = new Button("_1. Show Flight List");
        addSingleFlightButton = new Button("_2. Add New Flight");
        removeFirstFlightButton = new Button("_3. Remove First Flight");
        showFirstFlightButton = new Button("_4. Show First Flight");
        howManyFlightsButton = new Button("_5. Show How Many Flights on Schedule");
        clearFlightListButton = new Button("_6. Clear Current Flight List");
        saveButton = new Button("_Save Flight List");
        loadButton = new Button("_Load Last Flight List");

        viewFlightListButton.setOnAction(e -> showFlightList.tableWindow("Flight Schedule"));
        addSingleFlightButton.setOnAction(e -> addNewFlight.addWindow("Add New Flight"));
        removeFirstFlightButton.setOnAction(e -> firstFlightActions.doRemoveFlight());
        showFirstFlightButton.setOnAction(e -> firstFlightActions.doShowFirstFlight());
        howManyFlightsButton.setOnAction(e -> flightListActions.doHowManyFlights());
        clearFlightListButton.setOnAction(e -> flightListActions.clearList());
        saveButton.setOnAction(e -> flightListActions.saveFlights());
        loadButton.setOnAction(e -> flightListActions.loadFlights());

        buttonMnemonicHelper();
    }

    // MODIFIES: this
    // EFFECTS: sets the mnemonic parsing to be true for main menu buttons
    // REFERENCE: https://youtu.be/3vAT-FpiJKw
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
    // REFERENCE: http://tutorials.jenkov.com/javafx/imageview.html
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

        // SOURCE: https://www.freesoundeffects.com/free-sounds/airplane-10004/
        String soundPath = "/ui/media/airstrike.wav";
        AudioClip sound = Applet.newAudioClip(getClass().getResource(soundPath));

        imagePlane = new Button();
        imagePlane.setGraphic(iv1);
        imagePlane.setOnAction(e ->
                sound.play());
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

    //GETTERS/SETTERS

    // EFFECTS: returns the currentFlightSchedule
    public static FlightList getCurrentFlightSchedule() {
        return currentFlightSchedule;
    }

    // MODIFIES: this
    // EFFECTS: sets the currentFlightSchedule to inputFlightList
    public static void setCurrentFlightSchedule(FlightList inputFlightList) {
        currentFlightSchedule = inputFlightList;
    }



}
