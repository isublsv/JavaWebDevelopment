package by.gartsmanovich.composite.datahandler.interpreter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BitwiseExpressionParserTest {

    @DataProvider(name = "DataForPositiveCalculate")
    public Object[][] getDataForPositiveCalculate() {
        return new Object[][]{
                {"5 42 2 | 3 << ^ ", 341},  //5^(42|2)<<3
                {"91 2 >>> ", 22},  //91>>>2
                {"44 ~ 3 | ", -45}, //~44|3
        };
    }

    @Test(dataProvider = "DataForPositiveCalculate")
    public void testPositiveCalculate(String expression, Number expected) {
        Number actual = new BitwiseExpressionParser(expression).calculate();
        
        assertEquals(actual, expected);
    }
}
