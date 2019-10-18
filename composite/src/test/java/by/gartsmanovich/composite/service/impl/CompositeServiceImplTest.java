package by.gartsmanovich.composite.service.impl;

import by.gartsmanovich.composite.service.CompositeService;
import by.gartsmanovich.composite.service.exception.ServiceException;
import by.gartsmanovich.composite.service.factory.ServiceFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

public class CompositeServiceImplTest {

    private CompositeService compositeService;

    @BeforeClass(groups = "sortAndSave")
    public void beforeClass() {
        System.out.println("BEFORE CLASS:");
        ServiceFactory factory = ServiceFactory.getInstance();
        compositeService = factory.getCompositeService();
    }

    @BeforeGroups(groups = "sortAndSave")
    public void beforeGroup() throws ServiceException {
        System.out.println("BEFORE GROUP:");
        String pathToRead = "test/input1.txt";
        compositeService.createComposite(pathToRead);
    }

    @DataProvider(name = "DataForCreateCompositeException")
    public Object[][] getDataForCreateCompositeException() {
        return new Object[][]{{""}, {"test/fileNoteExist.txt"}};
    }

    @Test(dataProvider = "DataForCreateCompositeException",
            expectedExceptions = ServiceException.class,
            description = "Tests a method to throw an ServiceException")
    public void testCreateCompositeException(String path)
            throws ServiceException {
        compositeService.createComposite(path);
    }

    @Test(groups = "sortAndSave", 
            description = "Tests the sort paragraphs by sentence number")
    public void testSortParagraphsByNumberOfSentences() throws
            ServiceException {
        String actual = compositeService.sortParagraphsByNumberOfSentences();
        String expected = "This is my first sentence! \r\n"
                          + "There must be second sentence, but there is no any words.";
        assertEquals(actual.strip(), expected.strip());
    }

    @Test(groups = "sortAndSave",
    description = "Tests the sort sentences by word length")
    public void testSortWordsByLength() throws ServiceException {
        String actual = compositeService.sortWordsByLength();
        System.out.println(actual);
        String expected = "! is my This first sentence \r\n"
                          + ", . be is no but any must There there words " 
                          + "second sentence";
        assertEquals(actual.strip(), expected.strip());
    }

    @Test(groups = "sortAndSave",
    description = "Tests the sort lexemes by char number than by alphabet")
    public void testSortLexemesByCharNumber() throws ServiceException {
        char letter = 'e';
        String actual = compositeService.sortLexemesByCharNumber(letter);
        System.out.println(actual);
        String expected = "sentence!\r\nsentence,\r\nThere\r\nthere\r\n"
                          + "be\r\nsecond\r\nThis\r\nany\r\nbut\r\n"
                          + "first\r\nis\r\nis\r\nmust\r\nmy\r\nno\r\nwords.";
        assertEquals(actual.strip(), expected.strip());
    }

    @Test(groups = "sortAndSave",
    description = "Test the method for saving composite in the file")
    public void testSaveComposite() throws IOException, ServiceException {
        String pathToWrite = "test\\output1.txt";

        long before = Files.size(Paths.get(pathToWrite));
        compositeService.saveComposite(pathToWrite);
        long after = Files.size(Paths.get(pathToWrite));

        assertEquals(before, after);
    }
}
