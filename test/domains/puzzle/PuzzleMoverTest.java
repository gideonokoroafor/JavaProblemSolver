package domains.puzzle;

import framework.problem.Mover;
import framework.problem.State;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gideon o
 */
public class PuzzleMoverTest {
        
    /**
     * Declare private instance fields here. For example:
     */
    
    private final State start, startCopy;
    private final Mover mover;
    private final String slide1, slide2, slide3, slide4,   // move names
                         slide5, slide6, slide7, slide8;
    
    public PuzzleMoverTest() {
        
        /**
         * Initialize instance fields here
         */
        
        start = new PuzzleState(new int[][]{
            new int[]{1, 2, 3}, 
            new int[]{8, 0, 4}, 
            new int[]{7, 6, 5}});
        
        startCopy = new PuzzleState(new int[][]{
            new int[]{1, 2, 3}, 
            new int[]{8, 0, 4}, 
            new int[]{7, 6, 5}});
        
        mover = new PuzzleMover(3, 3);
        
        List<String>moveNames = mover.getMoveNames();
        
        slide1 = moveNames.get(0);
        slide2 = moveNames.get(1);
        slide3 = moveNames.get(2);
        slide4 = moveNames.get(3);
        slide5 = moveNames.get(4);
        slide6 = moveNames.get(5);
        slide7 = moveNames.get(6);
        slide8 = moveNames.get(7);
    }
    
    /**
     * Test all moves in the methods below. 
     */

    @Test
    public void testSlide1() {
        /**
         * For example, if mover, start, and slide1 have been
         * initialized, call:
         *
         *     mover.doMove(slide1, start)
         *
         * and test the result with assertions.
         */
        State next = mover.doMove(slide1, start);
        assertTrue(next == null);
    }

    @Test
    public void testSlide2() {
        State next = mover.doMove(slide2, start);
        next.equals(new PuzzleState(new int[][]{
            new int[]{1, 0, 3}, 
            new int[]{8, 2, 4}, 
            new int[]{7, 6, 5}}));
        start.equals(startCopy);
    }

    @Test
    public void testSlide3() {
        State next = mover.doMove(slide3, start);
        assertTrue(next == null);
    }

    @Test
    public void testSlide4() {
        State next = mover.doMove(slide4, start);
        next.equals(new PuzzleState(new int[][]{
            new int[]{1, 2, 3}, 
            new int[]{8, 4, 0}, 
            new int[]{7, 6, 5}}));
        start.equals(startCopy);
    }

    @Test
    public void testSlide5() {
        State next = mover.doMove(slide5, start);
        assertTrue(next == null);
    }

    @Test
    public void testSlide6() {
        State next = mover.doMove(slide6, start);
        next.equals(new PuzzleState(new int[][]{
            new int[]{1, 2, 3}, 
            new int[]{8, 6, 4}, 
            new int[]{7, 0, 5}}));
        start.equals(startCopy);
    }

    @Test
    public void testSlide7() {
        State next = mover.doMove(slide7, start);
        assertTrue(next == null);
    }

    @Test
    public void testSlide8() {
        State next = mover.doMove(slide8, start);
        next.equals(new PuzzleState(new int[][]{
            new int[]{1, 2, 3}, 
            new int[]{0, 8, 4}, 
            new int[]{7, 6, 5}}));
        start.equals(startCopy);
    }
    
}