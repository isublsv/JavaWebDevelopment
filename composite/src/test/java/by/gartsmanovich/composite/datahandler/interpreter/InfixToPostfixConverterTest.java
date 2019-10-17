package by.gartsmanovich.composite.datahandler.interpreter;

import by.gartsmanovich.composite.datahandler.exception.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class InfixToPostfixConverterTest {

    private InfixToPostfixConverter converter;
    
    @BeforeMethod
    public void setUp() {
        converter = new InfixToPostfixConverter();
    }
    
    @Test
    public void testConvert() throws ParseException {
        String expression = "5^(4|2)<<3";
        String expected = "3542|^<<";
        String actual = converter.convert(expression);
        
        assertEquals(expected, expected);
    }
}
