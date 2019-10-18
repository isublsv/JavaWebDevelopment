package by.gartsmanovich.composite.controller;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ControllerTest {

    private Controller controller;

    @BeforeMethod(description = "Execute the load composite from the file")
    public void setUp() {
        controller = new Controller();

        String pathToRead = "data/input.txt";
        System.out.println("COMPOSITE CREATION FROM FILE: " + pathToRead);
        controller.executeTask("1 " + pathToRead);
    }

    @Test(description = "Execute the sort paragraph by number of sentences method")
    public void testSortParagraphsByNumberOfSentences() {
        System.out.println("SORT PARAGRAPHS BY NUMBER OF SENTENCES:");
        System.out.println(controller.executeTask("2"));
    }

    @Test(description = "Execute the sort sentences by word length method")
    public void testSortSentencesByWordLength() {
        System.out.println("SORT SENTENCES BY WORDS LENGTH:");
        System.out.println(controller.executeTask("3"));
    }

    @Test(description = "Execute the sort lexemes by letter number than by alphabet method")
    public void testSortLexemesByCharNumber() {
        char letter = 'a';
        System.out.println("SORT LEXEMES BY NUMBER OF PROVIDED LETTER " + letter +
                           " THAN BY ALPHABET:");
        System.out.println(controller.executeTask("4 " + letter));
    }

    @Test(description = "Execute the save composite to the file")
    public void testSaveComposite() {
        String pathToWrite = "data/output.txt";
        System.out.println("SAVE COMPOSITE INTO THE FILE: " + pathToWrite);
        System.out.println(controller.executeTask("5 " + pathToWrite));
    }
}
