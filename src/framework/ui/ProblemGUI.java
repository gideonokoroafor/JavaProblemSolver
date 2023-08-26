package framework.ui;

import framework.problem.Benchmark;
import framework.problem.Problem;
import framework.problem.State;
import framework.solution.AStarSolver;
import framework.solution.BFSStateSpaceSolver;
import framework.solution.BestFirstSolver;
import framework.solution.DFSStateSpaceSolver;
import framework.solution.Solution;
import framework.solution.Solver;
import framework.solution.SolvingAssistant;
import javafx.scene.shape.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author gideonokoroafor
 */
public class ProblemGUI extends VBox  {
    
    /**
     * Creates a problem GUI with a given width and height.
     * @param problem the problem
     * @param width the width of the display area
     * @param height the height of the display area
     */
    
    public ProblemGUI(Problem problem, double width, double height) {
        super.setPrefSize(width, height);
        solver = new SolvingAssistant(problem);
        this.problem = problem;
        displayMessage();
        makeControls();
        super.getChildren().addAll(welcome, introduction, controls, resetBox, Benchmarks);
        super.setAlignment(Pos.CENTER);
    }
    
    public ProblemGUI(Problem problem){
        this(problem, 600, 600);
    }
    
    private String makeWelcome() {
        StringBuilder builder = new StringBuilder();
        builder.append("Welcome to the ");
        builder.append(problem.getName());
        builder.append(" Problem.");
        return builder.toString();
    }
    
    /**
     * Getter for the string representation of the current state area of the problem.
     * This includes an identifying label, the state depiction, and the current
     * move count
     * @return the current state area
     */
    
    private String makeCurrent() {
        StringBuilder builder = new StringBuilder();
        builder.append(problem.getCurrentState());
        return builder.toString();
    }
    
    /**
     * Getter for the string representation of the goal state area of the problem.
     * @return the goal state area
     */
    private String makeGoal() {
        StringBuilder builder = new StringBuilder();
        builder.append(problem.getFinalState());
        return builder.toString();
    }
    
    /**
     * Getter for the string representation of the moves count of the problem.
     * @return the current move count
     */
    private String getMoves(){
        StringBuilder builder = new StringBuilder();
        builder.append("Moves (");
        builder.append(solver.getMoveCount());
        builder.append(" so far):");
        return builder.toString();
    }
    
    /**
     *  Makes a button for the moves and add to the controls 
     * 
     */
    
    private void makeButtons(){
        problem.getMover().getMoveNames().forEach((m) -> {
            btn = new ToggleButton();
            btn.setText(m);
            btn.setPrefWidth(200);
            btn.setPrefHeight(35);
            btn.setStyle("-fx-background-color: #FFDEAD");
            btn.setFont(font);
            btn.setBorder(new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5))));
            buttonList.add(btn);
            btn.setOnAction(e -> {
                message.setVisible(false);
                solver.tryMove(m);
                current.setText(makeCurrent());
                movesLabel.setText(getMoves());
                if(!solver.isMoveLegal()){
                    message.setVisible(true);
                    message.setText(ILLEGAL_MOVE);
                    message.setTextFill(Color.RED);
                    message.setFont(font1);
                } else if(solver.isProblemSolved()){
                    message.setText(CONGRATS);
                    message.setTextFill(Color.GREEN);
                    message.setFont(font1);
                    message.setVisible(true);
                    buttonList.forEach(action-> action.setDisable(true));
                } 
            });
        });
        
    }
    
    /**
     * Display for the button representation.
     */
    private void displayButton() {
        for(int i = 0; i < buttonList.size(); i++){
            if(i < 5) {
                b1.getChildren().addAll(buttonList.get(i));
            } else if(i < 10){
                b2.getChildren().addAll(buttonList.get(i));
            } else {
                b3.getChildren().addAll(buttonList.get(i));
            }
        }
    }
    
    /**
     * Getter for the message string for the problem.
     * Messages can include error indications and congratulatory messages.
     * @return the message string
     */
    
    private void displayMessage() {
        welcome = new Label(makeWelcome());
        welcome.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
        welcome.setWrapText(true);
        welcome.setTextAlignment(TextAlignment.JUSTIFY);
        welcome.setPadding(new Insets(10, 10, 10, 10));
        welcome.setAlignment(Pos.CENTER);
        introduction = new Label(problem.getIntroduction());
        introduction.setFont(new Font("Times New Roman", 20));
        introduction.setWrapText(true);
        introduction.setTextAlignment(TextAlignment.JUSTIFY);
        introduction.setPadding(new Insets(10, 10, 10, 10));
        currentState = new VBox(10);
        currentLabel = new Label("Current State:");
        current = new Label(makeCurrent());
        currentLabel.setFont(font1);
        current.setFont(new Font("Monospaced Bold", 25));
        current.setBorder(new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        current.setPadding(new Insets(10, 10, 10, 10));
        goalState = new VBox(10);
        goalLabel = new Label("Goal State:");
        goal = new Label(makeGoal());
        goal.setFont(new Font("Monospaced Bold", 25));
        goalLabel.setFont(font1);
        goal.setBorder(new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        goal.setPadding(new Insets(10, 10, 10, 10));
    }
    
    /**
     * Getter controls
     */
    private void makeControls() {
        controls = new HBox(25);
        controls.setAlignment(Pos.CENTER);
        currentState.getChildren().addAll(currentLabel, current);
        goalState.getChildren().addAll(goalLabel, goal);
        currentState.setAlignment(Pos.CENTER);
        goalState.setAlignment(Pos.CENTER);
        
        buttons = new VBox(10);
        b1 = new VBox(10);
        b2 = new VBox(10);
        b3 = new VBox(10);
        tileBox = new HBox(10);
        movesLabel = new Label(getMoves());
        movesLabel.setFont(font1);
        movesLabel.setAlignment(Pos.CENTER);
        message = new Label(ILLEGAL_MOVE);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setPadding(new Insets(15, 10, 5, 10));
        message.setVisible(false);
        makeButtons();
        displayButton();
        
        tileBox.getChildren().addAll(b1, b2, b3);
        buttons.getChildren().addAll(movesLabel, tileBox);
        buttons.setAlignment(Pos.CENTER);
        reset = new Button("Reset");
        reset.setPrefSize(100, 35);
        reset.setOnAction(e -> {
            solver.reset();
            stats.setText(" ");
            solvebtn.setDisable(false);
            next.setDisable(true);
            benchmarks.setDisable(false);
            benchmarks.getSelectionModel().selectFirst();
            choiceBox.getSelectionModel().selectFirst();
            buttonList.forEach(action-> {
                action.setDisable(false);
                action.setStyle("-fx-background-color: #FFDEAD");
            });
            current.setText(makeCurrent());
            movesLabel.setText(getMoves());
            message.setVisible(false);
        });
        
        resetBox = new VBox(10);
        resetBox.getChildren().addAll(message, reset);
        resetBox.setAlignment(Pos.CENTER);
        controls.getChildren().addAll(currentState, buttons, goalState);
        
        solvebtn = new Button("Solve");
        solvebtn.setPrefSize(100, 35);
        next = new Button("Next");
        next.setPrefSize(100, 35);
        next.setDisable(true);
        
        solveBox = new VBox(15);  // Solve and Next button box
        solveBox.setAlignment(Pos.CENTER);
        solveBox.setPadding(new Insets(15, 10, 5, 10));
        solveBox.getChildren().addAll(solvebtn, next);
        
        searchType = new Label("Search Type");
        searchType.setFont(font);
        searchType.setTextAlignment(TextAlignment.JUSTIFY);
        choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("BF State Space Search",
                                    "DF State Space Search",
                                    "Best-First Search",
                                    "A* Search");
        choiceBox.getSelectionModel().selectFirst();
        searchTypeBox = new VBox(5);
        searchTypeBox.setAlignment(Pos.CENTER);
        searchTypeBox.getChildren().addAll(searchType, choiceBox); // choiceBox1
        
        statsLabel = new Label("Statistics");
        statsLabel.setFont(font);
        statsLabel.setTextAlignment(TextAlignment.JUSTIFY);
        Rectangle rec = new Rectangle();
        rec.setHeight(200);
        rec.setWidth(250);
        rec.setFill(null);
        rec.setStroke(Color.BLACK);
        rec.setStrokeWidth(3);
        stats = new Label();
        stats.setFont(font);
        stats.setTextAlignment(TextAlignment.LEFT);
        
        stack = new StackPane();
        stack.getChildren().addAll(rec, stats);
        statsBox = new VBox(5);
        statsBox.setAlignment(Pos.CENTER);
        statsBox.getChildren().addAll(statsLabel, stack);
        
        benchLabel = new Label("Benchmarks");
        benchLabel.setFont(font);
        benchLabel.setTextAlignment(TextAlignment.JUSTIFY);
        benchmarks = new ChoiceBox<>(FXCollections.observableArrayList(problem.getBenchmarks()));
        benchmarks.getSelectionModel().selectFirst();
        benchBox = new VBox(5);
        benchBox.setAlignment(Pos.CENTER);
        benchBox.getChildren().addAll(benchLabel, benchmarks); // choiceBox2
        
        solvebtn.setOnAction(e -> {
            updateStatsDisplay();
            next.setDisable(false);
            benchmarks.setDisable(true);
            solvebtn.setDisable(true);
            buttonList.forEach(action-> {
                action.setDisable(true);
            });
            solution.next();
        });
        
        benchmarks.setOnAction(value->updateBenchDisplay());
        
        next.setOnAction(e -> {
            buttonList.forEach(a -> a.setStyle("-fx-background-color: #FFDEAD"));
            State sol = (State)solution.next().getData();
            String res = highlight(sol);
            buttonList.forEach(m -> {
                if(res.equals(m.getText())){
                    m.setStyle("-fx-background-color: red;" + 
                             "-fx-text-fill: white");
                }
            });
            solver.update(sol);
            current.setText(makeCurrent());
            movesLabel.setText(getMoves());
            if(solver.isProblemSolved()){
                message.setText(CONGRATS);
                message.setTextFill(Color.LIMEGREEN);
                message.setFont(font1);
                message.setVisible(true);
                next.setDisable(true);
            } 
        });
        Benchmarks = new HBox(20);
        Benchmarks.setAlignment(Pos.CENTER);
        Benchmarks.setPadding(new Insets(15, 10, 5, 10));
        Benchmarks.getChildren().addAll(solveBox, searchTypeBox, statsBox, benchBox);
        
    }
    
    private String highlight(State input){
        for(String s: problem.getMover().getMoveNames()){
            State st = problem.getMover().doMove(s, solverState);
            if(st != null && st.equals(input)){
                solverState = st;
                return s;
            }
        }
        return null;
    }
    
    private void updateStatsDisplay(){
        String selected = (String)choiceBox.getValue();
        b = (Benchmark)benchmarks.getValue();
        switch(selected) {
            case "BF State Space Search" :
                solve = new BFSStateSpaceSolver(problem);
                break;
            case "DF State Space Search" :
                solve = new DFSStateSpaceSolver(problem);
                break;
            case "Best-First Search" :
                solve = new BestFirstSolver(problem);
                break;
            case "A* Search" :
                solve = new AStarSolver(problem);
                break;
            default:
                throw new IllegalArgumentException();
        }
        solve.solve();
        stats.setText(solve.getStatistics().toString());
        solverState = solver.getProblem().getCurrentState();
        solution = solve.getSolution();
    }
    
    private void updateBenchDisplay() {
        b = (Benchmark)benchmarks.getValue();
        solver.reset();
        stats.setText(" ");
        problem.setCurrentState(b.getStart());
        current.setText(makeCurrent());
        movesLabel.setText(getMoves());
    }
    
    // Private Fields
    
    private final Problem problem;
    private final SolvingAssistant solver;
    private Solver solve;
    private Benchmark b;
    private Solution solution;
    private State solverState;
    
    private Label current, currentLabel, movesLabel, goalLabel;
    private Label goal;
    private Label welcome;
    private Label introduction;
    private Label message;
    
    private ToggleButton btn;
    
    private VBox currentState;
    private VBox buttons, b1, b2, b3, resetBox;
    private VBox goalState;
    private VBox solveBox, searchTypeBox, statsBox, benchBox;
    private HBox controls, tileBox, Benchmarks;
    
    private List<ToggleButton> buttonList = new ArrayList<>();
    private StackPane stack;
    
    private Button reset, solvebtn, next;
    
    private Label searchType, statsLabel, stats, benchLabel;
    private ChoiceBox choiceBox, benchmarks;
    
    private Font font = Font.font("Times New Roman", FontPosture.REGULAR, 18);
    private Font font1 = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25);
    
    public static final String ILLEGAL_MOVE = "Illegal move. Try again.";
    public static final String CONGRATS = "You solved the problem. Congratulations.";

}