package by.gartsmanovich.javawebdev.playroom.service.impl;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.service.PlayRoomService;
import by.gartsmanovich.javawebdev.playroom.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.playroom.service.factory.ServiceFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PlayRoomServiceImplTest {

    private ServiceFactory serviceFactory;
    private PlayRoomService<Toy> playRoomService;

    private String pathToRead =
            "D:\\WORK\\Dropbox\\Java\\JavaWebDevelopment\\playroom"
            + "\\data\\input.txt";

    @BeforeClass
    public void setUp() {
        serviceFactory = ServiceFactory.getInstance();
        playRoomService = serviceFactory.getPlayRoomService();
    }

    @DataProvider(name = "inputDataPlayRoomException")
    public Object[][] getInputDataForCreatePlayRoom() {
        return new Object[][]{
                {-100, pathToRead, ","},
                {-0.1e-10, pathToRead, ","},
                {0, pathToRead, ","},
                {Double.MAX_VALUE, pathToRead, ","},
                {Double.NaN, pathToRead, ","},
                {Double.POSITIVE_INFINITY, pathToRead, ","},
                {1000, "data", ","},
                {1000, "", ","},
                {1000, null, ","},
                {1000, pathToRead, ""},
                {1000, pathToRead, null}};
    }

    @Test(dataProvider = "inputDataPlayRoomException", expectedExceptions =
            ServiceException.class, groups = "createPlayRoom")
    public void testCreatePlayRoomException(double d, String path,
                                                     String del) throws
            ServiceException {
        playRoomService.createPlayRoom(d, path, del);
    }

    @DataProvider(name = "inputDataAddEntityException")
    public Object[][] getInputDataForAddEntityException() {
        return new Object[][]{
                {"toy", "red", "3", "44.5"},
                {"", "red", "3", "44.5"},
                {null, "red", "3", "44.5"},
                {"car", "", "3", "44.5"},
                {"car", null, "3", "44.5"},
                {"car", "test", "3", "44.5"},
                {"car", "red", "3", "44.5"},
                {"car", "red", "3", "44.5", "large"},
                {"car", "black", "3", "44.5", "big"},
                {"car", "", "3", "44.5", "big"},
                {"car", null, "3", "44.5", "big"},
                {"car", "red", "-3", "44.5", "big"},
                {"car", "red", "0", "44.5", "big"},
                {"car", "red", "5e12", "44.5", "big"},
                {"car", "red", "test", "44.5", "big"},
                {"car", "red", "3", "-44.5", "big"},
                {"car", "red", "3", "-0", "big"},
                {"car", "red", "3", "0", "big"},
                {"car", "red", "3", "NaN", "big"},
                {"doll", "red", "3", "45", "plastic"},
                {"doll", "red", "3", "45", "plastic", ""},
                {"doll", "red", "3", "45", "plastic", null},
                {"doll", "red", "3", "45", "metal", "55"},
                {"doll", "red", "3", "45", "plastic", "-1"},
                {"doll", "red", "3", "45", "plastic", "0"},
                {"doll", "red", "3", "45", "plastic", "-0.1e12"},
                {"doll", "red", "3", "45", "plastic", "NaN"},
                {"ball", "red", "3", "45", "4", ""},
                {"ball", "red", "3", "45", null},
                {"ball", "red", "3", "45", ""},
                {"ball", "red", "3", "45", "-1"},
                {"ball", "red", "3", "45", "-0.1e12"},
                {"ball", "red", "3", "45", "NaN"},
                {"block", "red", "3", "45", "block"},
                {"block", "red", "3", "45", "block", "plastic"},
                {"block", "red", "3", "45", "", "plastic"},
                {"block", "red", "3", "45", null, "plastic"},
                {"block", "red", "3", "45", "lego", ""},
                {"block", "red", "3", "45", "lego", null},
                {"block", "red", "3", "45", "lego", "metal"}
        };
    }

    @Test(dataProvider = "inputDataAddEntityException", expectedExceptions =
            ServiceException.class)
    public void testAddEntityException(String[] param) throws ServiceException {
        playRoomService.addEntity(param);
    }

    @DataProvider(name = "inputDataUpdateEntityException")
    public Object[][] getInputDataUpdateEntityException(){
        return new Object[][]{
                {-1, "car", "red", "3", "44.5", "big"},
                {0, "car", "red", "3", "44.5", "big"},
                {Long.MAX_VALUE, "car", "red", "3", "44.5", "big"},
                {1, "", "red", "3", "44.5"},
                {1, null, "red", "3", "44.5"},
                {1, "car", "", "3", "44.5"},
                {1, "car", null, "3", "44.5"},
                {1, "car", "test", "3", "44.5"},
                {1, "car", "red", "3", "44.5"},
                {1, "car", "red", "3", "44.5", "large"},
                {1, "car", "black", "3", "44.5", "big"},
                {1, "car", "", "3", "44.5", "big"},
                {1, "car", null, "3", "44.5", "big"},
                {1, "car", "red", "-3", "44.5", "big"},
                {1, "car", "red", "0", "44.5", "big"},
                {1, "car", "red", "5e12", "44.5", "big"},
                {1, "car", "red", "test", "44.5", "big"},
                {1, "car", "red", "3", "-44.5", "big"},
                {1, "car", "red", "3", "-0", "big"},
                {1, "car", "red", "3", "0", "big"},
                {1, "car", "red", "3", "NaN", "big"},
                {1, "doll", "red", "3", "45", "plastic"},
                {1, "doll", "red", "3", "45", "plastic", ""},
                {1, "doll", "red", "3", "45", "plastic", null},
                {1, "doll", "red", "3", "45", "metal", "55"},
                {1, "doll", "red", "3", "45", "plastic", "-1"},
                {1, "doll", "red", "3", "45", "plastic", "0"},
                {1, "doll", "red", "3", "45", "plastic", "-0.1e12"},
                {1, "doll", "red", "3", "45", "plastic", "NaN"},
                {1, "ball", "red", "3", "45", "4", ""},
                {1, "ball", "red", "3", "45", null},
                {1, "ball", "red", "3", "45", ""},
                {1, "ball", "red", "3", "45", "-1"},
                {1, "ball", "red", "3", "45", "-0.1e12"},
                {1, "ball", "red", "3", "45", "NaN"},
                {1, "block", "red", "3", "45", "block"},
                {1, "block", "red", "3", "45", "block", "plastic"},
                {1, "block", "red", "3", "45", "", "plastic"},
                {1, "block", "red", "3", "45", null, "plastic"},
                {1, "block", "red", "3", "45", "lego", ""},
                {1, "block", "red", "3", "45", "lego", null},
                {1, "block", "red", "3", "45", "lego", "metal"}
        };
    }

    @Test(dataProvider = "inputDataUpdateEntityException",
            expectedExceptions = ServiceException.class)
    public void testUpdateEntityException(long id, String[] param) throws
            ServiceException {
        playRoomService.updateEntity(id, param);
    }

    @DataProvider(name = "inputDataForFindAndRemoveByIDException")
    public Object[][] getInputDataForFindAndRemoveByIDException() {
        return new Object[][]{{-1}, {0}, {Long.MAX_VALUE}};
    }

    @Test(dataProvider = "inputDataForFindAndRemoveByIDException",
            expectedExceptions = ServiceException.class)
    public void testRemoveEntityException(long id) throws ServiceException {
        playRoomService.removeEntity(id);
    }

    @Test(dataProvider = "inputDataForFindAndRemoveByIDException",
            expectedExceptions = ServiceException.class)
    public void testFindEntityByIDException(long id) throws ServiceException {
        playRoomService.findEntityByID(id);
    }

    @DataProvider(name = "inputDataForFindEntityByTitleException")
    public Object[][] getInputDataFindEntityByTitleException() {
        return new Object[][]{{""}, {null}};
    }

    @Test(dataProvider = "getInputDataFindEntityByTitleException",
            expectedExceptions = ServiceException.class)
    public void testFindEntityByTitleException(String title)
            throws ServiceException {
        playRoomService.findEntityByTitle(title);
    }

    @DataProvider(name = "inputDataForFindEntityByFirstTitleLetterException")
    public Object[][] getInputDataForFindEntityByFirstTitleLetterException() {
        return new Object[][]{{' '}, {'0'}, {'$'}, {'\n'}, {'\\'}, {'.'}};
    }

    @Test(dataProvider = "inputDataForFindEntityByFirstTitleLetterException",
            expectedExceptions = ServiceException.class)
    public void testFindEntityByFirstTitleLetterException(char ch) throws
            ServiceException {
        playRoomService.findEntityByFirstTitleLetter(ch);
    }

    @DataProvider(name = "inputDataForFindEntityByRangeIdException")
    public Object[][] getInputDataForFindEntityByRangeIdException() {
        return new Object[][]{{-1, 5}, {0, 5}, {Long.MAX_VALUE, 5}, {5, -1},
                {5, 0}, {5, Long.MAX_VALUE}, {5, 4}};
    }

    @Test(dataProvider = "inputDataForFindEntityByRangeIdException",
            expectedExceptions = ServiceException.class)
    public void testFindEntityByRangeIdException(long start, long end) throws
            ServiceException {
        playRoomService.findEntityByRangeId(start, end);
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void testSortByAge() {
    }

    @Test
    public void testSortByColorAndPrice() {
    }

    @Test(dataProvider = "inputDataForFindEntityByTitleException",
            expectedExceptions = ServiceException.class)
    public void testSaveAll(String path) throws ServiceException {
        playRoomService.saveAll(path);
    }
}
