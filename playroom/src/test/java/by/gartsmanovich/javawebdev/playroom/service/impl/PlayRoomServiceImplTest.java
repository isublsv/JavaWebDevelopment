package by.gartsmanovich.javawebdev.playroom.service.impl;

import by.gartsmanovich.javawebdev.playroom.bean.param.BlockType;
import by.gartsmanovich.javawebdev.playroom.bean.param.Color;
import by.gartsmanovich.javawebdev.playroom.bean.param.Material;
import by.gartsmanovich.javawebdev.playroom.bean.param.Size;
import by.gartsmanovich.javawebdev.playroom.bean.toy.Doll;
import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.bean.toy.ToyBall;
import by.gartsmanovich.javawebdev.playroom.bean.toy.ToyBlock;
import by.gartsmanovich.javawebdev.playroom.bean.toy.ToyCar;
import by.gartsmanovich.javawebdev.playroom.service.PlayRoomService;
import by.gartsmanovich.javawebdev.playroom.service.comparator.AgeComparator;
import by.gartsmanovich.javawebdev.playroom.service.comparator.ColorComparator;
import by.gartsmanovich.javawebdev.playroom.service.comparator.PriceComparator;
import by.gartsmanovich.javawebdev.playroom.service.exception.ServiceException;
import by.gartsmanovich.javawebdev.playroom.service.factory.ServiceFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class PlayRoomServiceImplTest {

    private ServiceFactory serviceFactory;
    private PlayRoomService<Toy> playRoomService;

    private List<Toy> toyList;
    private List<Toy> sortedByAge;
    private List<Toy> sortedByColorAndPrice;

    private String pathToRead = "src\\test\\resources\\input.txt";
    private String pathToWrite = "src\\test\\resources\\output.txt";

    @BeforeClass
    public void setUpBeforeClass() throws ServiceException {
        serviceFactory = ServiceFactory.getInstance();
        playRoomService = serviceFactory.getPlayRoomService();

        toyList = new ArrayList<>();
        sortedByAge = new ArrayList<>();
        sortedByColorAndPrice = new ArrayList<>();

        Doll doll = new Doll(3, "doll", Color.BLUE,3, 42, Material.PLASTIC, 22.0);
        ToyCar car = new ToyCar(1, "car", Color.RED, 2, 21, Size.SMALL);
        ToyBall ball = new ToyBall(2, "ball", Color.WHITE, 4, 55, 22.0);
        ToyBlock block = new ToyBlock(5, "block", Color.BLUE, 4, 40, BlockType.LEGO, Material.PLASTIC);

        toyList.add(doll);
        toyList.add(car);
        toyList.add(ball);
        toyList.add(block);

        sortedByAge.add(car);
        sortedByAge.add(doll);
        sortedByAge.add(ball);
        sortedByAge.add(block);

        sortedByColorAndPrice.add(block);
        sortedByColorAndPrice.add(doll);
        sortedByColorAndPrice.add(car);
        sortedByColorAndPrice.add(ball);

        playRoomService.createPlayRoom(1000, pathToRead, ",");
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

    @DataProvider(name = "inputDataPositiveAddEntity")
    public Object[][] getInputDataPositiveAddEntity() {
        return new Object[][]{
                {"car", "red", "3", "145.0", "big"},
                {"doll", "white", "5", "1", "plastic", "220.0"},
                {"block", "blue", "2", "88.0", "lego", "wood"},
                {"ball", "yellow", "9", "22.0", "15.5"},
        };
    }

    //There are no negative tests because ArrayList.add always returns true
    @Test(dataProvider = "inputDataPositiveAddEntity")
    public void testPositiveAddEntity(String[] param) throws ServiceException {
        assertTrue(playRoomService.addEntity(param));
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

    @DataProvider(name = "inputDataPositiveUpdateEntity")
    public Object[][] getInputDataPositiveUpdateEntity() {
        return new Object[][]{
                {5, "car", "red", "3", "145.0", "big"},
                {6, "doll", "white", "5", "200.0", "plastic", "220.0"},
        };
    }

    @Test(dataProvider = "inputDataPositiveUpdateEntity")
    public void testPositiveUpdateEntity(long id, String[] param) throws
            ServiceException {
        assertTrue(playRoomService.updateEntity(id, param));
    }

    @DataProvider(name = "inputDataNegativeUpdateEntity")
    public Object[][] getInputDataNegativeUpdateEntity() {
        return new Object[][]{
                {100, "car", "red", "3", "145.0", "big"},
                {Long.MAX_VALUE - 1, "doll", "white", "5", "1", "plastic", "220.0"},
        };
    }

    @Test(dataProvider = "inputDataNegativeUpdateEntity")
    public void testNegativeUpdateEntity(long id, String[] param) throws
            ServiceException {
        assertFalse(playRoomService.updateEntity(id, param));
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

    @Test()
    public void testPositiveRemoveEntity() throws ServiceException {
        assertTrue(playRoomService.removeEntity(1));
        assertTrue(playRoomService.removeEntity(2));
    }

    @Test()
    public void testNegativeRemoveEntity() throws ServiceException {
        assertFalse(playRoomService.removeEntity(100));
        assertFalse(playRoomService.removeEntity(Long.MAX_VALUE - 1));
    }

    @Test(dataProvider = "inputDataForFindAndRemoveByIDException",
            expectedExceptions = ServiceException.class)
    public void testFindEntityByIDException(long id) throws ServiceException {
        playRoomService.findEntityByID(id);
    }

    @Test()
    public void testPositiveFindEntityByID() throws ServiceException {
        List<Toy> entityByID = playRoomService.findEntityByID(5);
        System.out.println("POSITIVE Find by id: " + entityByID);
        assertEquals(entityByID.size(), 1);
    }

    @Test()
    public void testNegativeFindEntityByID() throws ServiceException {
        List<Toy> entityByID = playRoomService.findEntityByID(1000);
        System.out.println("NEGATIVE Find by id: " + entityByID);
        assertEquals(entityByID.size(), 0);
    }

    @DataProvider(name = "inputDataForFindEntityByTitleException")
    public Object[][] getInputDataFindEntityByTitleException() {
        return new Object[][]{{""}, {null}};
    }

    @Test(dataProvider = "inputDataForFindEntityByTitleException",
            expectedExceptions = ServiceException.class)
    public void testFindEntityByTitleException(String title)
            throws ServiceException {
        playRoomService.findEntityByTitle(title);
    }

    @Test()
    public void testPositiveFindEntityByTitle()
            throws ServiceException {
        List<Toy> entityByTitle = playRoomService.findEntityByTitle("car");
        System.out.println("POSITIVE Find by title: " + entityByTitle);
        assertEquals(entityByTitle.size(), 3);
    }

    @Test()
    public void testNegativeFindEntityByTitle()
            throws ServiceException {
        List<Toy> entityByTitle = playRoomService.findEntityByTitle("ball");
        System.out.println("NEGATIVE Find by title: " + entityByTitle);
        assertEquals(entityByTitle.size(), 0);
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

    @Test()
    public void testPositiveFindEntityByFirstTitleLetter() throws
            ServiceException {
        List<Toy> entityByFirstTitleLetter
                = playRoomService.findEntityByFirstTitleLetter('D');
        System.out.println("POSITIVE Find by first letter: "
                           + entityByFirstTitleLetter);
        assertEquals(entityByFirstTitleLetter.size(), 4);
    }

    @Test()
    public void testNegativeFindEntityByFirstTitleLetter() throws
            ServiceException {
        List<Toy> entityByFirstTitleLetter
                = playRoomService.findEntityByFirstTitleLetter('f');
        System.out.println("NEGATIVE Find by first letter: "
                           + entityByFirstTitleLetter);
        assertEquals(entityByFirstTitleLetter.size(), 0);
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

    @Test()
    public void testPositiveFindEntityByRangeId() throws ServiceException {
        List<Toy> entityByRangeId = playRoomService.findEntityByRangeId(1, 10);
        System.out.println("POSITIVE Find by ID range: "
                           + entityByRangeId);
        assertEquals(entityByRangeId.size(), 7);

    }

    @Test()
    public void testNegativeFindEntityByRangeId() throws ServiceException {
        List<Toy> entityByRangeId = playRoomService.findEntityByRangeId(50, 100);
        System.out.println("NEGATIVE Find by ID range: "
                           + entityByRangeId);
        assertEquals(entityByRangeId.size(), 0);

    }

    @Test
    public void testFindAll() throws ServiceException {
        List<Toy> all = playRoomService.findAll();
        assertEquals(all.size(), 7);
    }

    @Test
    public void testSortByAge() {
        System.out.println("Before Sorting : " + toyList);
        toyList.sort(new AgeComparator());

        System.out.println("After Sorting : " + toyList);

        assertEquals(toyList.get(0).getTitle(), "car");
        assertEquals(toyList.get(2).getColor(), Color.WHITE);

        assertEquals(toyList, sortedByAge);
    }

    @Test
    public void testSortByColorAndPrice() {
        System.out.println("Before Sorting : " + toyList);
        toyList.sort(new ColorComparator().thenComparing(new PriceComparator()));

        System.out.println("After Sorting : " + toyList);

        assertEquals(toyList.get(0).getTitle(), "block");
        assertEquals(toyList.get(3).getPrice(), 55.0);

        assertEquals(toyList, sortedByColorAndPrice);
    }

    @Test(dataProvider = "inputDataForFindEntityByTitleException",
            expectedExceptions = ServiceException.class)
    public void testSaveAllException(String path) throws ServiceException {
        playRoomService.saveAll(path);
    }

    @Test()
    public void testSaveAll() throws ServiceException, IOException {
        long before = Files.size(Paths.get(pathToWrite));
        playRoomService.saveAll(pathToWrite);
        long after = Files.size(Paths.get(pathToWrite));

        assertNotEquals(before, after);

        //file clearing
        FileChannel.open(Paths.get(pathToWrite), StandardOpenOption.WRITE)
                   .truncate(0).close();
    }
}
