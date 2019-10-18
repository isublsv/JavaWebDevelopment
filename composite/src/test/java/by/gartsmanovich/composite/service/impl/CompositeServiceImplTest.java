package by.gartsmanovich.composite.service.impl;

import by.gartsmanovich.composite.service.CompositeService;
import by.gartsmanovich.composite.service.exception.ServiceException;
import by.gartsmanovich.composite.service.factory.ServiceFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

public class CompositeServiceImplTest {

    private CompositeService compositeService;

    @BeforeSuite
    public void beforeClass() {
        ServiceFactory factory = ServiceFactory.getInstance();
        compositeService = factory.getCompositeService();
    }

    @BeforeMethod
    public void setUp() throws ServiceException {
        String pathToRead = "data/input.txt";
        compositeService.createComposite(pathToRead);
    }

    @DataProvider(name = "DataForCreateCompositeException")
    public Object[][] getDataForCreateCompositeException() {
        return new Object[][]{{""}, {"data/fileNoteExist.txt"}};
    }

    @Test(dataProvider = "DataForCreateCompositeException",
            expectedExceptions = ServiceException.class)
    public void testCreateComposite(String path) throws ServiceException {
        compositeService.createComposite(path);
    }

    @Test
    public void testSortParagraphsByNumberOfSentences() {
    }

    @Test
    public void testSortWordsByLength() {
    }

    @Test
    public void testSortLexemesByCharNumber() {
    }

    @Test
    public void testSaveComposite() throws IOException, ServiceException {
        String pathToWrite = "data/output.txt";

        long before = Files.size(Paths.get(pathToWrite));
        compositeService.saveComposite(pathToWrite);
        long after = Files.size(Paths.get(pathToWrite));

        assertEquals(before, after);
    }
}
