# Flight Schedule Personal Project CPSC 210 - by Brenden Yee

## Proposal

In my personal project, I plan to design a **flight schedule** application that allows users to store and organize 
upcoming flights that they have. This will be used by travelers for both personal and business related flights. 

Users will be able to add/do the following:

- flight dates
- flight times
- flight numbers
- flight names
- add flights
- remove flights
- see first flight
- remove first flight

These will be added to a schedule which can then be requested by the user to see the order of the
schedule of flights they have. The application will guide users through what information they should input to help
ensure the users do not forget important information that they will need for their flights. I hope to expand the scope
of the project throughout the term to include other possible features.

## User Stories

Phase 1
- As a user I want to be able to add a new flight to a list of flights
- As a user I want to be able to view the number of flights currently on my schedule
- As a user I want to be able to remove the first flight from my flight schedule
- As a user I want to be able to view the first flight on my current schedule

Phase 2
- As a user, I want to be able to save my current flight schedule to file from the main menu
- As a user, I want to be able to load my last saved flight schedule from the main menu

Phase 3 Instructions for Grader

- On EDX it asks to say how many points we are expecting based on the grading key. I am expecting to receive full marks
(100 pts) as I believe I have fulfilled the requirements outlined and have justified this below.
- You can generate the first required event by first clicking the "1. Show Flight List" button to get to the flight
schedule display screen ("Panel that displays Xs that have been added to the Y"). Then you can enter the flight 
information as required then click (click-event type, first event-type) the "Add" button which will add the 
"Flight (X)" to the "FlightList (Y)" and also to the table. You can additionally press alt+a (keyboard-event type, 
second event-type) to use a mnemonic shortcut to add the flight.
- You can generate the second required event by first clicking the "1. Show Flight List" button to get to the flight
  schedule display screen. Then you can then select the flight you want to remove by clicking it to highlight it, then 
  clicking (click-event type, first event-type) the "Remove" button which will remove the "Flight (X)" from the 
  "FlightList (Y)" and table. You can additionally press alt+r (keyboard-event type, second event-type) to use a 
  mnemonic shortcut to add the flight.
- You can locate my visual component in the main application menu by seeing the plane logo. You can also trigger my 
audio component by clicking the plane logo image which will play a plane sound.
- You can save the state of my application by clicking the "Save Flight List" button in the main menu, or by pressing
alt+S to use the mnemonic shortcut in the main menu
- You can reload the state of my application by clicking the "Load Last Flight List" button in the main menu or by
pressing alt+L to use the mnemonic shortcut in the main menu

Phase 4: Task 2

- I have chosen the option "Test and design a class that is robust." for Task 2
- The class which is robust is the "Flight" class in the model package with the following two methods: 
setDepartureTime() and setFlightDate()
- These methods throw DateFormat Exception (checked) and DepartureTimeFormat Exception (checked) respectively
- To test these robust methods, please see the "FlightTest" class in the test package with the following 4 tests that 
test where the DateFormat exception is expected/not expected and where the DepartureTimeFormat is expected/not expected: 
testSetFlightDateCorrect(), testSetFlightDateWrong(), testSetDepartureTimeCorrect(), testSetDepartureTimeIncorrect()
- You can also find an additional try/catch clause in the "AddNewFlight" class under the ui package, the applicable
method is named clickAddFlight()

Phase 4: Task 3

- My Flight App contained poor cohesion and a couple places where coupling could be improved. I originally had a master
class for the GUI (FlightAppGUI) with contained all the methods related to the GUI. I have now improved the cohesion by
splitting this master class into individual classes which are better suited to different parts of the GUI. The fixes I
made to my app are listed below:
- Fix 1: Created a separate class named "ShowFlightList". I moved the methods related to the Show Flight List pop out
window into this new class from the "FlightAppGUI" class improving the cohesion of my app and classes. These methods 
were the following: getFlight(), tableWindow(), addRemoveButtonHelper(), columnMakerHelper(), inputCreator(), 
labelMakerHelper, flightTableSetupHelper(), inputTableHelper(), addFlightButtonClicked(), formatChecker(), 
removeFlightButtonClicked()
- Fix 2: Created a separate class named "AddNewFlight". I moved the methods related to the Add New Flight pop out
window into this new class from the "FlightAppGUI" class improving the cohesion of my app and classes. These methods
were the following: addWindow(), addButtonAction(), createInputBoxes(), clickAddFlight()
- Fix 3: Created a separate class named "FirstFlightActions". I moved the methods related to showing/removing the first
flight in a flight list into this new class from the "FlightAppGUI" class improving the cohesion of my app and classes.
These methods were the following: doShowFirstFlight(), doRemoveFlight()
- Fix 4: Created a separate class named "FlightListActions". I moved the methods related to working with the flight list
(such as save/load, clearing the flight list, showing how many flights are on the flight list) into this new class
from the "FlightAppGUI" class improving the cohesion of my app and classes. These methods were the following:
doHowManyFlights(), clearList(), initializeFlightList(), saveFlights(), loadFlights()
- Fix 5: I created a new field called "datePrompt" in the "FlightAppGUI" which controls the departure date text prompt
for all the input boxes in the application. Thus, you only need to change the departure date text prompt in this single
place now instead of trying to look through all the classes to identify where to change the prompt (specifically
the AddNewFlight and ShowFlightList classes) which improves the coupling in the app and classes.
- Fix 6: In the "AddNewFlight" class, I created the fields "maxHeightInputBox", and "maxMaxWidthInputBox" which helps
create a single point of control for controlling the dimensions of input boxes