
package domains.arithmetic;

import framework.problem.State;

/**
 *
 * @author gideonokoroafor
 */
public class ArithmeticState extends State {
    public ArithmeticState(int contents){
        this.contents = contents;
    }
    
    @Override
    public boolean equals(Object other) {
        ArithmeticState arithmetic = (ArithmeticState) other;
        return this.contents == arithmetic.contents;
    }
    
    @Override
    public String toString(){
        return "The value is: " + contents;
    }
    
    /**
     * Getter for the valid item in the ArithmeticState
     * @return the value in the contents
     */
    public int getValue(){
        return contents;
    }
    

    private final int contents;
}
