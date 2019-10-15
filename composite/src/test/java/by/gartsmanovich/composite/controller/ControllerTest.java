package by.gartsmanovich.composite.controller;

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
    public void testSortParagraphsByNumberOfSentences() {
        System.out.println(controller.executeTask("2"));
    }

    @Test
    public void testSortByWordLength() {
        System.out.println(controller.executeTask("3"));
    }

    @Test
    public void testSortLexemesByCharNumber() {
        System.out.println(controller.executeTask("4 a"));
    }

    @Test
    public void testSaveComposite() {
        System.out.println(controller.executeTask("5 " + pathToWrite));
    }
}
