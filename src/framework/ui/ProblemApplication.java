/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.ui;

import domains.arithmetic.ArithmeticProblem;
import domains.dummy.DummyProblem;
import domains.farmer.FarmerProblem;
import domains.puzzle.FifteenPuzzleProblem;
import domains.puzzle.PuzzleProblem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author gideonokoroafor
 *
 *
 * This class presents problem solving domains in a tabbed pane.
 */
public class ProblemApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        
	/* Add tabs using the following */

	Tab tab = new Tab();
        tab.setText("Dummy");
        tab.setContent(new ProblemGUI(new DummyProblem(), 950, 650));
        tab.setClosable(false);
        
        Tab tab1 = new Tab();
        tab1.setText("Arithmetic");
        tab1.setContent(new ProblemGUI(new ArithmeticProblem(), 1050, 750));
        tab1.setClosable(false);
        
        Tab tab2 = new Tab();
        tab2.setText("Farmer, Wolf, Goat, and Cabbage");
        tab2.setContent(new ProblemGUI(new FarmerProblem(), 1050, 750));
        tab2.setClosable(false);
        
        Tab tab3 = new Tab();
        tab3.setText("8-Puzzle");
        tab3.setContent(new ProblemGUI(new PuzzleProblem(), 1200, 750));
        tab3.setClosable(false);
        
        Tab tab4 = new Tab();
        tab4.setText("15-Puzzle");
        tab4.setContent(new ProblemGUI(new FifteenPuzzleProblem(), 1300, 950));
        tab4.setClosable(false);
        
        tabPane.getTabs().addAll(tab, tab1, tab2, tab3, tab4);
        
        Scene scene = new Scene(tabPane);
        primaryStage.setTitle("Problem Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
