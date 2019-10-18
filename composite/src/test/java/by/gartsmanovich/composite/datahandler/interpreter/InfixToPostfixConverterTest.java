package by.gartsmanovich.composite.datahandler.interpreter;

import by.gartsmanovich.composite.datahandler.exception.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class InfixToPostfixConverterTest {

    private InfixToPostfixConverter converter;
    
    @BeforeMethod
    public void setUp() {
        converter = new InfixToPostfixConverter();
    }

    @DataProvider(name = "getDataForPositiveConvert")
    public Object[][] getDataForPositiveConvert() {
        return new Object[][]{
                {"5^(42|2)<<3", "5 42 2 | 3 << ^ "},
                {"91>>>2", "91 2 >>> "},
                {"~44|3", "44 ~ 3 | "},   
        };
    }

    @Test(dataProvider = "getDataForPositiveConvert")
    public void testPositiveConvert(String expression, String expected)
            throws ParseException {
        String actual = converter.convert(expression);
        
        assertEquals(actual, expected);
    }

    @DataProvider(name = "getDataForConvertException")
    public Object[][] getDataForConvertException() {
        return new Object[][]{
                {"(5^4|2(<<3"},
                {"(~24|3"},
        };
    }

    @Test(dataProvider = "getDataForConvertException",
            expectedExceptions = ParseException.class)
    public void testConvertException(String expression)
            throws ParseException {
        converter.convert(expression);
    }
}
