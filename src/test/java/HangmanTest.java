/*
// this is the testing class
// DO NOT MODIFY THIS CLASS AND ITS METHODS
*/
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class HangmanTest {
    // test isInt
    @Test public void isIntTest() {
        String num = "15";
    	assertThat("\nThis is the test on your isInt method." + 
            "\nIs the given string " + num + " an integer?", 
            Hangman.isInt(num), 
            is(RightSolution.isInt(num)));    
    }

    // test isInt
    @Test public void isIntTest2() {
        String num = "15.7";
        assertThat("\nThis is the test on your isInt method." + 
            "\nIs the given string " + num + " an integer?", 
            Hangman.isInt(num), 
            is(RightSolution.isInt(num)));    
    }

    // test isInt
    @Test public void isIntTest3() {
        String num = "23a";
        assertThat("\nThis is the test on your isInt method." + 
            "\nIs the given string " + num + " an integer?", 
            Hangman.isInt(num), 
            is(RightSolution.isInt(num)));    
    }

    // test validPosition
    @Test public void testValidPosition() {
        String positionStr = "0 100 4 21.2 ";
        int spaceAllowed = 4;
        assertThat("\nThis is the test on your validPosition method." +
            "\nThe given spaces are " + positionStr +
            "\nThe number of allowed spaces is " + spaceAllowed +
            "\nAre the given spaces valid?",
            Hangman.validPosition(positionStr, spaceAllowed), 
            is(RightSolution.validPosition(positionStr, spaceAllowed))); 
    }

    // test validPosition
    @Test public void testValidPosition2() {
        String positionStr = "0 1 5 a";
        int spaceAllowed = 4;
        assertThat("\nThis is the test on your validPosition method." +
            "\nThe given spaces are " + positionStr +
            "\nThe number of allowed spaces is " + spaceAllowed +
            "\nAre the given spaces valid?",
            Hangman.validPosition(positionStr, spaceAllowed), 
            is(RightSolution.validPosition(positionStr, spaceAllowed))); 
    }

    // test validPosition
    @Test public void testValidPosition3() {
        String positionStr = "0 1 5 7";
        int spaceAllowed = 3;
        assertThat("\nThis is the test on your validPosition method." +
            "\nThe given spaces are " + positionStr +
            "\nThe number of allowed spaces is " + spaceAllowed +
            "\nAre the given spaces valid?",
            Hangman.validPosition(positionStr, spaceAllowed), 
            is(RightSolution.validPosition(positionStr, spaceAllowed))); 
    }

    // test validPosition
    @Test public void testValidPosition4() {
        String positionStr = "0, 100, 4, 21";
        int spaceAllowed = 4;
        assertThat("\nThis is the test on your validPosition method." +
            "\nThe given spaces are " + positionStr +
            "\nThe number of allowed spaces is " + spaceAllowed +
            "\nAre the given spaces valid?",
            Hangman.validPosition(positionStr, spaceAllowed), 
            is(RightSolution.validPosition(positionStr, spaceAllowed))); 
    }

    // test getPosition
    @Test public void testGetPosition() {
        String positionStr = "0 100 4 21 7";
        int spaceAllowed = 5;
        assertThat("\nThis is the test on your getPosition method." +
            "\nThe given spaces are " + positionStr +
            "\nThe number of allowed spaces is " + spaceAllowed,
            Hangman.getPosition(positionStr, spaceAllowed), 
            is(RightSolution.getPosition(positionStr, spaceAllowed))); 
    }

    // test getPosition
    @Test public void testGetPosition2() {
        String positionStr = "0 1 4 9";
        int spaceAllowed = 4;
        assertThat("\nThis is the test on your getPosition method." +
            "\nThe given spaces are " + positionStr +
            "\nThe number of allowed spaces is " + spaceAllowed,
            Hangman.getPosition(positionStr, spaceAllowed), 
            is(RightSolution.getPosition(positionStr, spaceAllowed))); 
    }

    // test getPosition
    @Test public void testGetPosition3() {
        String positionStr = "0 3 4 ";
        int spaceAllowed = 3;
        assertThat("\nThis is the test on your getPosition method." +
            "\nThe given spaces are " + positionStr +
            "\nThe number of allowed spaces is " + spaceAllowed,
            Hangman.getPosition(positionStr, spaceAllowed), 
            is(RightSolution.getPosition(positionStr, spaceAllowed))); 
    }
}
