package jva.test.java;
import jva.main.java.Barbell;

import java.util.Random;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BarbellTest {
    
    private Barbell barbell = new Barbell();
   
    private static final Random RN = new Random();
    /**
     * The class generated id.    
     */
    private int generateId() {
        return RN.nextInt(20);
    }
    /**
     * This class works with max array.    
     */
    private int[] testMaxArray() {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1 + (int) (Math.random() * 20);
        }
        return array;
    }

    @Test
    public void maxWeight() throws WorkException {
        int[] array = {1, 2, 3, 4, 5, 6};
        int value = this.barbell.printTest(array);
        int result = 20;
        assertThat(value, is(result));
    }

    @Test(expected = WorkException.class)
    public void maxWeightExceeded() throws WorkException {
        this.barbell.printTest(testMaxArray());
    }

    @Test
    public void maxWeightId() throws WorkException {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = generateId();
        }
        int value = this.barbell.printTest(array);
        int result = 10000;
        assertThat(value < result, is(true));
    }
}
