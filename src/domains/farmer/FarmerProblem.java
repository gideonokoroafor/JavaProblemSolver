/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.farmer;

import framework.problem.Problem;

/**
 *
 * @author gideonokoroafor
 */
public class FarmerProblem extends Problem {
//    public FarmerProblem() {
//        super.setName("Farmer, Wolf, Goat, and Cabbage");
//        super.setIntroduction(INTRO);
//        super.setMover(new FarmerMover());
//        super.setInitialState(new FarmerState("West", "West", "West", "West"));
//        super.setCurrentState(super.getInitialState());
//        super.setFinalState(new FarmerState("East", "East", "East", "East"));
//    }
    
    public FarmerProblem() {
        super();
        super.setName("Farmer Problem");
        super.setIntroduction("Get across the river");
        super.setMover(new FarmerMover());
        super.setInitialState(new FarmerState("West", "West", "West", "West"));
        super.setCurrentState(super.getInitialState());
        super.setFinalState(new FarmerState("East", "East", "East", "East"));
    }

    
    private final String INTRO = 
            "A farmer and his wolf, goat, and cabbage come to the end of a river they wish to cross." +
            " There is a boat at the river's edge that only the farmer can row." + 
            " The farmer can take at most one other object besides himself on a crossing, " +
            "but if the wolf is ever left with the goat, the wolf will eat the goat; " +
            "similarly, if the goat is left with the cabbage, the goat will eat the cabbag." +
            " Devise a sequence of crossings of the river so that all four characters arrive safely on the other side.";
}
