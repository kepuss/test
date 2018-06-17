import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DoStuffTest {

    @Test
    public void displayFirstSmallerThan3() {
        new DoStuff().displayFirstSmallerThan3(Arrays.asList(2,3,4,5,6,7));
        assertTrue(true);
    }

    @Test
    public void createAscList() {
        List<Integer> list = new DoStuff().createAscList(8);
        System.out.println(Arrays.toString(list.toArray()));
        assertTrue(true);
    }

    @Test
    public void getMessageWithoutPrefix() {
        String result = new DoStuff().getMessageWithoutPrefix("CUSTOMasdasdas");
        System.out.println(result);
        assertTrue(true);
    }

    @Test
    public void getSum() {
    }

    @Test
    public void makeDecision() {
    }

    @Test
    public void divide() {
    }

    @Test
    public void getStuffName() {
    }
}