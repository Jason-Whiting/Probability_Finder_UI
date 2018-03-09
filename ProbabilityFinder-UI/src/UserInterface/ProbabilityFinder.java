package UserInterface;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jason Whiting
 */
public class ProbabilityFinder extends Application {

    public static TextArea display;
    public static TextField input;
    public static Button enter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Dice Probability Finder UI");
        stage.setWidth(960);
        stage.setHeight(720);
        stage.setResizable(false);

        VBox root = new VBox();

        BorderPane low = new BorderPane();
        low.setPadding(new Insets(10, 10, 10, 10));

        MenuBar menu = new MenuBar();
        Menu file = new Menu("File");
        MenuItem save = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");
        MenuItem newFile = new MenuItem("New");
        file.getItems().addAll(save, load, newFile);
        Menu help = new Menu("Help");
        menu.getMenus().addAll(file, help);

        display = new TextArea();
        display.setEditable(false);
        display.setPrefHeight(750);
        low.setCenter(display);

        HBox userInput = new HBox();
        userInput.setSpacing(15);
        userInput.setPadding(new Insets(10, 0, 0, 0));
        userInput.setAlignment(Pos.CENTER);
        input = new TextField();
        input.setPromptText("Please Enter Values Here...");
        input.setPrefWidth(250);
        enter = new Button("Enter");
        userInput.getChildren().addAll(input, enter);
        low.setBottom(userInput);

        root.getChildren().addAll(menu, low);

        Scene scene = new Scene(root, 960, 720);
        stage.setScene(scene);
        stage.show();

        checkRollType();

    }

    public static void checkRollType() {

        display.appendText("Are you trying to dodge or block?\n\n");
        input.clear();

        enter.setOnAction(e -> {
            String rollType = input.getText();
            if (rollType.equalsIgnoreCase("dodge")) {
                getDodgeDice();
            } else if (rollType.equalsIgnoreCase("block")) {
                getBlackBlockDice();
            } else {
                display.appendText("Invalid Input. Please try again.\n\n");
                input.clear();
            }
        });

        input.setOnAction(e -> {
            String rollType = input.getText();
            if (rollType.equalsIgnoreCase("dodge")) {
                getDodgeDice();
            } else if (rollType.equalsIgnoreCase("block")) {
                getBlackBlockDice();
            } else {
                display.appendText("Invalid Input. Please try again.\n\n");
                input.clear();
            }
        });

    }

    public static void getDodgeDice() {

        display.appendText("How many green dodge dice do you have to roll?\n");
        display.appendText("Please enter a number greater than 0 and less than or equal to 4.\n\n");
        input.clear();

        enter.setOnAction(e -> {
            int diceNum = Integer.parseInt(input.getText());
            selectDice(diceNum);
        });

        input.setOnAction(e -> {
            int diceNum = Integer.parseInt(input.getText());
            selectDice(diceNum);
        });

    }

    public static void getBlackBlockDice() {

        display.appendText("WARNING: Please ensure that the sum of your dice does not exceed 6.\n\n");

        display.appendText("How many black block dice do you have to roll?\n");
        display.appendText("Please enter a number greater than 0 and less than or equal to 5.\n\n");
        input.clear();

        enter.setOnAction(e -> {
            int black = Integer.parseInt(input.getText());
            getBlueBlockDice(black);
        });

        input.setOnAction(e -> {
            int black = Integer.parseInt(input.getText());
            getBlueBlockDice(black);
        });

    }

    public static void getBlueBlockDice(int black) {

        display.appendText("How many blue block dice do you have to roll?\n");
        display.appendText("Please enter a number greater than 0 and less than or equal to 4.\n\n");
        input.clear();

        enter.setOnAction(e -> {
            int blue = Integer.parseInt(input.getText());
            getOrangeBlockDice(black, blue);
        });

        input.setOnAction(e -> {
            int blue = Integer.parseInt(input.getText());
            getOrangeBlockDice(black, blue);
        });

    }

    public static void getOrangeBlockDice(int black, int blue) {

        display.appendText("How many orange block dice do you have to roll?\n");
        display.appendText("Please enter a number greater than 0 and less than or equal to 2.\n\n");
        input.clear();

        enter.setOnAction(e -> {
            int orange = Integer.parseInt(input.getText());
            selectDice(black, blue, orange);
        });

        input.setOnAction(e -> {
            int orange = Integer.parseInt(input.getText());
            selectDice(black, blue, orange);
        });

    }

    public static void selectDice(int diceNum) {

        int[] greenDie = {1, 1, 1, 0, 0, 0};

        int[][] allDice = new int[diceNum][6];

        for (int i = 0; i < diceNum; i++) {
            for (int j = 0; j < 6; j++) {
                allDice[i][j] = greenDie[j];
            }
        }

        calculate(allDice);

    }

    public static void selectDice(int black, int blue, int orange) {

        int[] blackDie = {2, 2, 1, 1, 1, 0};
        int[] blueDie = {3, 2, 2, 2, 1, 1};
        int[] orangeDie = {4, 3, 3, 2, 2, 1};

        int totalDice = black + blue + orange;
        int[][] allDice = new int[totalDice][6];

        for (int i = 0; i < black; i++) {
            for (int j = 0; j < 6; j++) {
                if (black > 0) {
                    allDice[i][j] = blackDie[j];
                }
            }
        }

        for (int i = black; i < black + blue; i++) {
            for (int j = 0; j < 6; j++) {
                if (blue > 0) {
                    allDice[i][j] = blueDie[j];
                }
            }
        }

        for (int i = black + blue; i < black + blue + orange; i++) {
            for (int j = 0; j < 6; j++) {
                if (orange > 0) {
                    allDice[i][j] = orangeDie[j];
                }
            }
        }

        calculate(allDice);

    }

    public static void calculate(int[][] allDice) {

        int totalDice = allDice.length;
        int totalPossibilities = (int) Math.pow(6, totalDice);

        int max = 0;
        for (int i = 0; i < totalDice; i++) {
            max = max + allDice[i][0];
        }

        int result;
        int[] resultsArray = new int[max + 1];

        switch (totalDice) {
            case 6:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 6; k++) {
                            for (int m = 0; m < 6; m++) {
                                for (int n = 0; n < 6; n++) {
                                    for (int p = 0; p < 6; p++) {
                                        result = allDice[0][i] + allDice[1][j] + allDice[2][k] + allDice[3][m] + allDice[4][n] + allDice[5][p];
                                        resultsArray[result]++;
                                    }
                                }
                            }
                        }
                    }
                }
                print(resultsArray, totalPossibilities, max + 1);
                break;
            case 5:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 6; k++) {
                            for (int m = 0; m < 6; m++) {
                                for (int n = 0; n < 6; n++) {
                                    result = allDice[0][i] + allDice[1][j] + allDice[2][k] + allDice[3][m] + allDice[4][n];
                                    resultsArray[result]++;
                                }
                            }
                        }
                    }
                }
                print(resultsArray, totalPossibilities, max + 1);
                break;
            case 4:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 6; k++) {
                            for (int m = 0; m < 6; m++) {
                                result = allDice[0][i] + allDice[1][j] + allDice[2][k] + allDice[3][m];
                                resultsArray[result]++;
                            }
                        }
                    }
                }
                print(resultsArray, totalPossibilities, max + 1);
                break;
            case 3:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 6; k++) {
                            result = allDice[0][i] + allDice[1][j] + allDice[2][k];
                            resultsArray[result]++;
                        }
                    }
                }
                print(resultsArray, totalPossibilities, max + 1);
                break;
            case 2:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        result = allDice[0][i] + allDice[1][j];
                        resultsArray[result]++;
                    }
                }
                print(resultsArray, totalPossibilities, max + 1);
                break;
            case 1:
                for (int i = 0; i < 6; i++) {
                    result = allDice[0][i];
                    resultsArray[result]++;
                }
                print(resultsArray, totalPossibilities, max + 1);
                break;
            default:
                display.appendText("You have either entered 0 dice or more than 6 dice.\n");
                display.appendText("That, or you entered invalid input. Please try again.\n\n");
                checkRollType();
        }

    }

    public static void print(int[] results, int total, int max) {

        DecimalFormat df = new DecimalFormat("###.##");

        display.appendText("The total number of possible rolls is: " + total + ".\n\n");

        for (int i = 0; i < max; i++) {
            display.appendText("The probability of rolling a " + i + " is: " + df.format((double) results[i] / total * 100) + "%.\n");
        }

        display.appendText("\n");

        for (int i = 0; i < max; i++) {
            display.appendText("The probability of rolling a " + i + " or higher is: " + df.format((double) addResults(results, i) / total * 100) + "%.\n");

        }

        display.appendText("\n");
        tryAgain();

    }

    public static int addResults(int[] results, int index) {

        int sum = 0;

        for (int i = results.length - 1; i >= index; i--) {
            sum = sum + results[i];
        }

        return sum;

    }

    public static void tryAgain() {

        display.appendText("Would you like to make another calculation?\n\n");
        input.clear();

        enter.setOnAction(e -> {
            String answer = input.getText();
            if (answer.equalsIgnoreCase("yes")) {
                checkRollType();
            } else if (answer.equalsIgnoreCase("no")) {
                Platform.exit();
            } else {
                display.appendText("Invalid Input. Please try again.\n\n");
                input.clear();
            }
        });

        input.setOnAction(e -> {
            String answer = input.getText();
            if (answer.equalsIgnoreCase("yes")) {
                checkRollType();
            } else if (answer.equalsIgnoreCase("no")) {
                Platform.exit();
            } else {
                display.appendText("Invalid Input. Please try again.\n\n");
                input.clear();
            }
        });

    }

}
