package by.gartsmanovich.composite.datahandler.interpreter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BitwiseExpressionParserTest {

    @DataProvider(name = "getDataForPositiveCalculate")
    public Object[][] getDataForPositiveCalculate() {
        return new Object[][]{
                {"5 42 2 | 3 << ^ ", 341},  //5^(42|2)<<3
                {"91 2 >>> ", 22},  //91>>>2
                {"44 ~ 3 | ", -45}, //~44|3
        };
    }
    
    @Test(dataProvider = "getDataForPositiveCalculate")
    public void testPositiveCalculate(String expression, Number expected) {
        Number actual = new BitwiseExpressionParser(expression).calculate();
        
        assertEquals(actual, expected);
    }
    
    @Test
    public void test() {
        System.out.println(5^(42|2)<<3);
        System.out.println(91>>>2);
        System.out.println(~44|3);
    }
}
