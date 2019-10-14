package by.gartsmanovich.javawebdev.composite.controller;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ControllerTest {

    private Controller controller;
    private String pathToRead = "data/input.txt";
    private String pathToWrite = "data/output.txt";

    @BeforeMethod
    public void setUp() {
        controller = new Controller();
        controller.executeTask("1 " + pathToRead);
    }
    
    @Test
    public void testCreateComposite() {

    }

    @Test
    public void testSaveComposite() {
        controller.executeTask("5 " + pathToWrite);
    }
}
