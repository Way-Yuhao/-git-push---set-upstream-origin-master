/**
 * Filename:   Main.java
 * Project:    CS400 Final Project
 * Authors:    Ruijian Huang, Yuhao Liu, Huifeng Su, Junheng Wang, Leon Zhang
 * <p>
 * Semester:   Fall 2018
 * Course:     CS400
 * <p>
 * Due Date:   10pm, 11/30/2018
 * Version:    1.0
 * <p>
 * Credits:    None
 * <p>
 * Bugs:       For now there seems to be no bug
 */

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This is a GUI class for the purpose of establishing a bridge between users and the final program.
 * It contains all functions required to utilize the function, with information of available foods,
 * current meal selected, and current query applied given to users.
 *
 * @author Ruijian Huang, Yuhao Liu, Huifeng Su, Junheng Wang, Leon Zhang
 */
public class Main extends Application {
    public int width = 1000; // Scene's initial width
    public int height = 700; // Scene's initial height

    /**
     * This method contains all the settings of GUI's layout, with only one primaryStage passed
     * into.
     *
     * @param primaryStage
     */
    public String calLowerLimHolder = "";  //saved previous info
    public String calUpperLimHolder = "";  //saved previous info
    public String fatLowerLimHolder = "";  //saved previous info
    public String fatUpperLimHolder = "";  //saved previous info
    public String carboLowerLimHolder = "";  //saved previous info
    public String carboUpperLimHolder = "";  //saved previous info
    public String fiberLowerLimHolder = "";  //saved previous info
    public String fiberUpperLimHolder = "";  //saved previous info
    public String proteinLowerLimHolder = "";  //saved previous info
    public String proteinUpperLimHolder = "";  //saved previous info

    public Double calLower;  //saved previous info
    public Double calUpper;  //saved previous info
    public Double fatLower;  //saved previous info
    public Double fatUpper;  //saved previous info
    public Double carboLower;  //saved previous info
    public Double carboUpper;  //saved previous info
    public Double fiberLower;  //saved previous info
    public Double fiberUpper;  //saved previous info
    public Double proteinLower;  //saved previous info
    public Double proteinUpper;  //saved previous info


    //Line Setup 1: calories
    TextField caloriesLowerLim = new TextField();
    TextField caloriesUpperLim = new TextField();

    TextField fatLowerLim = new TextField();
    TextField fatUpperLim = new TextField();

    TextField carboLowerLim = new TextField();
    TextField carboUpperLim = new TextField();

    TextField fiberLowerLim = new TextField();
    TextField fiberUpperLim = new TextField();

    TextField proteinLowerLim = new TextField();
    TextField proteinUpperLim = new TextField();

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, width, height);

            // All ScrollPanes this program needs
            ScrollPane foodSP = new ScrollPane();
            ScrollPane querySP = new ScrollPane();
            ScrollPane mealSP = new ScrollPane();
            ScrollPane sumSP = new ScrollPane();

            HBox biggestBox = new HBox(); // Biggest box to carry vBox1 and vBox2
            VBox vBox1 = new VBox(); // Left side Box containing all elements
            VBox vBox2 = new VBox(); // Right side Box containing all elements


            // Menu bar - setup
            MenuBar menuBar = new MenuBar();
            Menu FileMenu = new Menu("File");
            MenuItem filemenu1 = new MenuItem("load");
            MenuItem filemenu2 = new MenuItem("save");
            FileMenu.getItems().addAll(filemenu1, filemenu2);
            menuBar.getMenus().addAll(FileMenu);


            // Setting up each part of the vBox at left
            // Including HBox for Query, GridPane for Query within ScrollPane, HBox for FoodList,
            // GridPane for Foodlist within ScrollPane

            // Query - HBox setup
            HBox queryBox = new HBox(); // HBox containing name of Querypane and a add query button
            Text queryBoxName = new Text("Currently Applied Query");
            Button addQueryButton = new Button();
            addQueryButton.setText("Add Filter");
            queryBox.getChildren().addAll(queryBoxName, addQueryButton);
            queryBox.setSpacing(183); // Manually place this button to the right of HBox

            // Foodlist - HBox setup
            HBox foodListBox = new HBox(); // HBox containing name of FoodListPane and add food button
            Text foodListName = new Text("Food List");
            Button addFoodButton = new Button();
            addFoodButton.setText("Add Food");
            foodListBox.getChildren().addAll(foodListName, addFoodButton);
            foodListBox.setSpacing(270); // Manually place this button to the right of HBox

            // Query list - GridPane setup
            GridPane filterGrid = new GridPane(); // GridPane for viewing query list
            Text Nutrient = new Text(" Nutrient Type : ");
            Text Comparison = new Text(" Comaprison Type : ");
            Text Threshold = new Text(" Threshhold amount : ");
            filterGrid.add(Nutrient, 1, 0);
            filterGrid.add(Comparison, 2, 0);
            filterGrid.add(Threshold, 3, 0);
            // following code is for demonstration of the composition of foodList
            // TODO: delete this after milestone 2
            Text Calories = new Text(" Calories");
            Text GOET = new Text(" >=");
            Text fiveHundred = new Text(" 500");
            Text Calories1 = new Text(" Fat");
            Text GOET1 = new Text(" >=");
            Text fiveHundred1 = new Text(" 500");
            Button deleteFilter1 = new Button();
            Button deleteFilter2 = new Button();
            deleteFilter1.setText("-");
            deleteFilter2.setText("-");
            filterGrid.add(deleteFilter1, 0, 1);
            filterGrid.add(Calories, 1, 1);
            filterGrid.add(GOET, 2, 1);
            filterGrid.add(fiveHundred, 3, 1);
            filterGrid.add(deleteFilter2, 0, 3);
            filterGrid.add(Calories1, 1, 3);
            filterGrid.add(GOET1, 2, 3);
            filterGrid.add(fiveHundred1, 3, 3);

            // Foodlist - GridPane setup
            GridPane foodListGrid = new GridPane(); // GridPane for viewing foodList
            Text foodName = new Text(" Food Item Name      ");
            Text calories = new Text(" Calories      ");
            Text fat = new Text(" Fat      ");
            Text carbohydrate = new Text(" Carbohydrate      ");
            Text fiber = new Text(" Fiber      ");
            Text protein = new Text(" Protein      ");

            foodListGrid.add(foodName, 1, 0, 1, 1);
            foodListGrid.add(calories, 2, 0, 1, 1);
            foodListGrid.add(fat, 3, 0, 1, 1);
            foodListGrid.add(carbohydrate, 4, 0, 1, 1);
            foodListGrid.add(fiber, 5, 0, 1, 1);
            foodListGrid.add(protein, 6, 0, 1, 1); // row one for labels of nutrients

            // following code is for demonstration of the composition of foodList
            // TODO: delete this after milestone 2
            Button addToMealButton = new Button();
            addToMealButton.setText("+"); // Button for adding food to meal plan
            Text testFoodName = new Text(" Apple pie");
            Text testFoodCalories = new Text(" 1000");
            Text testFoodFat = new Text(" 900");
            Text testFoodCarbohydrate = new Text(" 800");
            Text testFoodFiber = new Text(" 700");
            Text testFoodProtein = new Text(" 600");
            foodListGrid.add(addToMealButton, 0, 1, 1, 1);
            foodListGrid.add(testFoodName, 1, 1, 1, 1);
            foodListGrid.add(testFoodCalories, 2, 1, 1, 1);
            foodListGrid.add(testFoodFat, 3, 1, 1, 1);
            foodListGrid.add(testFoodCarbohydrate, 4, 1, 1, 1);
            foodListGrid.add(testFoodFiber, 5, 1, 1, 1);
            foodListGrid.add(testFoodProtein, 6, 1, 1, 1);


            // Setting up each part of the vBox at right
            // Including HBox for Query, GridPane for Query within ScrollPane, HBox for FoodList,
            // GridPane for Foodlist within ScrollPane

            // Meal List - HBox setup
            HBox upperRight = new HBox();
            Text mealLTitle = new Text("Meal List");
            Button clearBtn = new Button("Clear All");
            upperRight.setSpacing(300);
            upperRight.getChildren().addAll(mealLTitle, clearBtn);

            // Meal List - GridPane setup
            GridPane mealListGrid = new GridPane(); // GridPane for viewing foodList
            Button maddToMealButton = new Button();
            maddToMealButton.setText("+"); // Button for adding food to meal plan
            Text mfoodName = new Text(" Food Item Name      ");
            Text mcalories = new Text(" Calories      ");
            Text mfat = new Text(" Fat      ");
            Text mcarbohydrate = new Text(" Carbohydrate      ");
            Text mfiber = new Text(" Fiber      ");
            Text mprotein = new Text(" Protein      ");
            ArrayList<Text> mlItem = new ArrayList<>();

            // following loop generates an empty table by filling many rows into GridPane
            int itemNum = 100;
            String mlName = " Item";
            for (Integer i = 1; i <= itemNum; i++) {
                mlItem.add(new Text(mlName + i.toString()));
            }

            mealListGrid.add(mfoodName, 1, 0, 1, 1);
            mealListGrid.add(mcalories, 2, 0, 1, 1);
            mealListGrid.add(mfat, 3, 0, 1, 1);
            mealListGrid.add(mcarbohydrate, 4, 0, 1, 1);
            mealListGrid.add(mfiber, 5, 0, 1, 1);
            mealListGrid.add(mprotein, 6, 0, 1, 1); // row one for labels of nutrients
            for (int i = 0; i < itemNum; i++) {
                mealListGrid.add(mlItem.get(i), 0, i + 1, 1, 1);
            }

            //Meal Summary - HBox setup
            HBox sumTitleBox = new HBox();
            Text sumTitle = new Text("Meal Summary");
            Button sumBtn = new Button("Generate Summary");
            sumTitleBox.getChildren().addAll(sumTitle, sumBtn);
            sumTitleBox.setSpacing(250);


            // Following are wrapping up all components and put them together

            // Left side - wrap up
            vBox1.getChildren().addAll(queryBox, querySP, foodListBox, foodSP);
            vBox1.setPrefWidth(scene.getWidth() / 2);
            vBox1.setVgrow(foodSP, Priority.ALWAYS); // only expanding size of FoodList

            // Right side - wrap up
            vBox2.getChildren().addAll(upperRight, mealSP, sumTitleBox, sumSP);
            vBox2.setPrefWidth(scene.getWidth() / 2);

            // ScrollPanes - policies
            // ScrollBar policies
            querySP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            querySP.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
            foodSP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            foodSP.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
            sumSP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            sumSP.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
            // Size policies
            querySP.setPrefHeight(150);
            sumSP.setPrefSize(width / 2, height / 2);
            mealSP.setPrefSize(width / 2, height / 1.5);

            // Putting GridPanes into ScrollPanes
            foodSP.setContent(foodListGrid); // insert GridPane into FoodList scrollpane
            querySP.setContent(filterGrid); // insert GridPane into Query scrollpane
            mealSP.setContent(mealListGrid); // insert GridPane into Meal scrollPane
            foodListGrid.setGridLinesVisible(true);
            mealListGrid.setGridLinesVisible(true);// Showing Grid Lines

            // Scene - setup
            root.setTop(menuBar); // put the menuBar at top
            biggestBox.getChildren().addAll(vBox1, vBox2); // wrapping rest elements into one box
            root.setLeft(biggestBox); // Putting the box into BorderPane

            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Meal Planner");
            primaryStage.show();













            setDefaultFilter();
            // Add Filter button
            Dialog dialogFilter = new Dialog();
            dialogFilter.initModality(Modality.APPLICATION_MODAL);
            dialogFilter.initOwner(primaryStage);
            dialogFilter.setTitle("Add Filter");
            //dialogFilter.setHeight();

            // GridPane & DialogPane setup
            GridPane gridDialogFilter = new GridPane();
            DialogPane dialogPaneFilter = new DialogPane();
            dialogPaneFilter.setContent(gridDialogFilter);
            dialogFilter.setDialogPane(dialogPaneFilter);
            gridDialogFilter.setHgap(10);
            gridDialogFilter.setVgap(10);

            //Line Setup 1: calories
            Text textCaloriesFilter = new Text(" <    Calories            < ");
            caloriesLowerLim.setText(calLowerLimHolder);    //load saved previous info to TextField
            caloriesUpperLim.setText(calUpperLimHolder);    //load saved previous info to TextField

            //Line Setup 2: fat
            Text textFatFilter = new Text(" <    Fat                    < ");
            caloriesLowerLim.setText(fatLowerLimHolder);    //load saved previous info to TextField
            caloriesUpperLim.setText(fatUpperLimHolder);    //load saved previous info to TextField

            //Line Setup 3: Carbohydrate
            Text textCarboFilter = new Text(" <    Carbohydrate   < ");
            caloriesLowerLim.setText(carboLowerLimHolder);    //load saved previous info to TextField
            caloriesUpperLim.setText(carboUpperLimHolder);    //load saved previous info to TextField

            //Line Setup 4: Fiber
            Text textFiberFilter = new Text(" <    Fiber                 < ");
            caloriesLowerLim.setText(fiberLowerLimHolder);    //load saved previous info to TextField
            caloriesUpperLim.setText(fiberUpperLimHolder);    //load saved previous info to TextField

            //Line Setup 5: Protein
            Text textProteinFilter = new Text(" <    Protein             < ");
            caloriesLowerLim.setText(proteinLowerLimHolder);    //load saved previous info to TextField
            caloriesUpperLim.setText(proteinUpperLimHolder);    //load saved previous info to TextField

            //BUTTON setup
            // "x" button setup
            dialogFilter.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            Node closeButton = dialogFilter.getDialogPane().lookupButton(ButtonType.CLOSE);
            closeButton.managedProperty().bind(closeButton.visibleProperty());
            closeButton.setVisible(false);
            // cancel button setup
            Button btnCancelFilter = new Button("Cancel");
            btnCancelFilter.setOnAction(event -> {
                dialogFilter.close();
                //FIXME: testing filter functionalities
                testFilter();
            });
            // reset button to clean the data
            Button btnResetFilter = new Button("Reset Filter");
            btnResetFilter.setOnAction(event -> {
                calLowerLimHolder = "";
                calUpperLimHolder = "";
                carboLowerLimHolder = "";
                carboUpperLimHolder = "";
                fatLowerLimHolder = "";
                fatUpperLimHolder = "";
                fiberLowerLimHolder = "";
                fiberUpperLimHolder = "";
                proteinLowerLimHolder = "";
                proteinUpperLimHolder = "";

                caloriesLowerLim.setText("");
                caloriesUpperLim.setText("");
                carboLowerLim.setText("");
                carboUpperLim.setText("");
                fatLowerLim.setText("");
                fatUpperLim.setText("");
                fiberLowerLim.setText("");
                fiberUpperLim.setText("");
                proteinLowerLim.setText("");
                proteinUpperLim.setText("");
                setDefaultFilter();

                //FIXME: testing filter functionalities
                testFilter();
            });
            // Save Button setup
            Button btnFilterSave = new Button("Save");
            HBox hBoxBtnFilterSave = new HBox();
            Text space = new Text("                        ");
            hBoxBtnFilterSave.getChildren().addAll(space, btnFilterSave);
            btnFilterSave.setAlignment(Pos.TOP_RIGHT);
            btnFilterSave.setOnAction(event -> {
                try {
                    calLowerLimHolder = caloriesLowerLim.getText();
                    calUpperLimHolder = caloriesUpperLim.getText();
                    carboLowerLimHolder = carboLowerLim.getText();
                    carboUpperLimHolder = carboUpperLim.getText();
                    fatLowerLimHolder = fatLowerLim.getText();
                    fatUpperLimHolder = fatUpperLim.getText();
                    fiberLowerLimHolder = fiberLowerLim.getText();
                    fiberUpperLimHolder = fiberUpperLim.getText();
                    proteinLowerLimHolder = proteinLowerLim.getText();
                    proteinUpperLimHolder = proteinUpperLim.getText();

                    if (!calLowerLimHolder.equals("")) calLower = Double.parseDouble(calLowerLimHolder);
                    if (!calUpperLimHolder.equals("")) calUpper = Double.parseDouble(calUpperLimHolder);
                    if (!carboLowerLimHolder.equals("")) carboLower = Double.parseDouble(carboLowerLimHolder);
                    if (!carboUpperLimHolder.equals("")) carboUpper = Double.parseDouble(carboUpperLimHolder);
                    if (!fatLowerLimHolder.equals("")) fatLower = Double.parseDouble(fatLowerLimHolder);
                    if (!fatUpperLimHolder.equals("")) fatUpper = Double.parseDouble(fatUpperLimHolder);
                    if (!fiberLowerLimHolder.equals("")) fiberLower = Double.parseDouble(fiberLowerLimHolder);
                    if (!fiberUpperLimHolder.equals("")) fiberUpper = Double.parseDouble(fiberUpperLimHolder);
                    if (!proteinLowerLimHolder.equals("")) proteinLower = Double.parseDouble(proteinLowerLimHolder);
                    if (!proteinUpperLimHolder.equals("")) proteinUpper = Double.parseDouble(proteinUpperLimHolder);

                    if (calLower.compareTo(calUpper) >= 0 || carboLower.compareTo(carboUpper) >= 0 ||
                            fatLower.compareTo(fatUpper) >= 0 || fiberLower.compareTo(fiberUpper) >= 0 ||
                            proteinLower.compareTo(proteinUpper) >= 0 ||calLower.compareTo(0.0) < 0 ||
                            carboLower.compareTo(0.0) < 0 ||
                            fatLower.compareTo(0.0) < 0 || fiberLower.compareTo(0.0) < 0 ||
                            proteinLower.compareTo(0.0) < 0 ) throw new IllegalArgumentException();

                    dialogFilter.close();

                } catch (NumberFormatException n) {
                    Alert numberFormatFilter = new Alert(Alert.AlertType.INFORMATION);
                    numberFormatFilter.setTitle("ERROR");
                    numberFormatFilter.setHeaderText("Entry Format Error");
                    numberFormatFilter.setContentText("Filter entered cannot be processed.");
                    numberFormatFilter.showAndWait();
                    btnResetFilter.fire();
                } catch (IllegalArgumentException i){
                    Alert numberFormatFilter = new Alert(Alert.AlertType.INFORMATION);
                    numberFormatFilter.setTitle("ERROR");
                    numberFormatFilter.setHeaderText("Illegal Filter Entry");
                    numberFormatFilter.setContentText("Please enter legal filter entries. \n   1. Integer or decimal larger or equal to 0 \n   2. [Entry1] < [Nutrient] < [Entry2]: Entry1 should be smaller than Entry2. ");
                    numberFormatFilter.showAndWait();
                    btnResetFilter.fire();
                }
                //FIXME: testing filter functionalities
                testFilter();
            });
            // filter prompt
            Text promptFilter1 = new Text("Entry is Optional.");
            Text promptFilter2 = new Text("Please enter legal integer or decimal number(s).");
            //fiter gridPane integration
            int filTableLine = 2;
            gridDialogFilter.add(promptFilter1, 0, 0, 3, 1);
            gridDialogFilter.add(promptFilter2, 0, 1, 3, 1);

            gridDialogFilter.add(hBoxBtnFilterSave, 2, filTableLine + 5, 1, 1);
            gridDialogFilter.add(btnResetFilter, 1, filTableLine + 5, 1, 1);
            gridDialogFilter.add(btnCancelFilter, 0, filTableLine + 5, 1, 1);

            gridDialogFilter.add(caloriesLowerLim, 0, filTableLine, 1, 1);
            gridDialogFilter.add(textCaloriesFilter, 1, filTableLine, 1, 1);
            gridDialogFilter.add(caloriesUpperLim, 2, filTableLine, 1, 1);

            gridDialogFilter.add(fatLowerLim, 0, filTableLine + 1, 1, 1);
            gridDialogFilter.add(textFatFilter, 1, filTableLine + 1, 1, 1);
            gridDialogFilter.add(fatUpperLim, 2, filTableLine + 1, 1, 1);

            gridDialogFilter.add(carboLowerLim, 0, filTableLine + 2, 1, 1);
            gridDialogFilter.add(textCarboFilter, 1, filTableLine + 2, 1, 1);
            gridDialogFilter.add(carboUpperLim, 2, filTableLine + 2, 1, 1);

            gridDialogFilter.add(fiberLowerLim, 0, filTableLine + 3, 1, 1);
            gridDialogFilter.add(textFiberFilter, 1, filTableLine + 3, 1, 1);
            gridDialogFilter.add(fiberUpperLim, 2, filTableLine + 3, 1, 1);

            gridDialogFilter.add(proteinLowerLim, 0, filTableLine + 4, 1, 1);
            gridDialogFilter.add(textProteinFilter, 1, filTableLine + 4, 1, 1);
            gridDialogFilter.add(proteinUpperLim, 2, filTableLine + 4, 1, 1);

            addQueryButton.setOnAction((event) -> dialogFilter.show());





            //Filter Table setup











        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setDefaultFilter() {
        calLower = 0.0;
        calLower = 0.0;
        calUpper = Double.MAX_VALUE;
        fatLower = 0.0;
        fatUpper = Double.MAX_VALUE;
        carboLower = 0.0;
        carboUpper = Double.MAX_VALUE;
        fiberLower = 0.0;
        fiberUpper = Double.MAX_VALUE;
        proteinLower = 0.0;
        proteinUpper = Double.MAX_VALUE;
    }

    //FIXME: testing only
    public void testFilter(){
        //FIXME: testing filter functionalities
        System.out.println("Cal: " + calLower + " " + calUpper);
        System.out.println("Carbo: " + carboLower + " " + carboUpper);
        System.out.println("Fat: " + fatLower + " " + fatUpper);
        System.out.println("Fiber: " + fiberLower + " " + fiberUpper);
        System.out.println("Protein: " + proteinLower + " " + proteinUpper);
        System.out.println((calLower.compareTo(calUpper) >= 0 ) + " " + (carboLower.compareTo(carboUpper) >= 0) + " " +
                (fatLower.compareTo(fatUpper) >= 0) + " " + (fiberLower.compareTo(fiberUpper) >= 0) + " " +
                (proteinLower.compareTo(proteinUpper) >= 0));
    }
}

class FilterTableInnerClass{
    FilterTableInnerClass(){

    }
}