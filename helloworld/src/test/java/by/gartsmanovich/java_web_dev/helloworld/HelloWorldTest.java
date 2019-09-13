package by.gartsmanovich.java_web_dev.helloworld;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HelloWorldTest {

    private HelloWorld helloWorld = new HelloWorld();

    @DataProvider(name = "positiveDataForSum")
    public Object[][] createPositiveDataForSum() {
        return new Object[][]{{new int[]{3, 5}, 8}, {new int[]{-3, 3}, 0},
                {new int[]{0, 0}, 0}, {new int[]{-3, -5}, -8}, {new
                int[]{-300, 200}, -100}, {new int[]{10, 0}, 10}, {new
                int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, -2}};
    }

    @Test(description = "Positive scenario of the sum calculation",
            dataProvider = "positiveDataForSum")
    public void testPositiveSum(int[] ab, int c) {
        int actual = helloWorld.sum(ab[0], ab[1]);
        assertEquals(actual, c);
    }
}