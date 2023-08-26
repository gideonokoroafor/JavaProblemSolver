
package domains.arithmetic;

import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author gideonokoroafor
 */
public class ArithmeticMover extends Mover {
    public static final String ADD = "Add 3";
    public static final String SUB = "Subtract 5";
    public static final String DIV = "Divide by 2";
    public static final String MUL = "Multiply by 2";
    private int value;
    
    public ArithmeticMover() {
        super.addMove(ADD, s->tryAdd3(s));
        super.addMove(SUB, s->subtract5(s));
        super.addMove(DIV, s->divide2(s));
        super.addMove(MUL, s->multiply2(s));
        this.value = 0;
    }
    /**
     * tryAdd3 gets the value from state and adds 3 to the value
     * @return a new ArithmeticState with the new value
    */
    private State tryAdd3(State state) {
        ArithmeticState s = (ArithmeticState)state;
        value = s.getValue()+3;
        return new ArithmeticState(value);
    }
    
    /**
     * tryAdd3 gets the value from state and subtracts 5 from the value
     * @return a new ArithmeticState with the new value
    */
    private State subtract5(State state){
        value -= 5;
        return new ArithmeticState(value);
    }
    
    /**
     * tryAdd3 gets the value from the state and divide the value by 2
     * @return a new ArithmeticState with the new value
    */
    private State divide2(State state){
        value /= 2;
        return new ArithmeticState(value);
    }
    
    /**
     * tryAdd3 gets the current value from state and multiply the value by 2
     * @return a new ArithmeticState with the new value
    */
    private State multiply2(State state){
        value *= 2;
        return new ArithmeticState(value);
    }
}
