package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.City;
import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.bean.Status;
import by.gartsmanovich.hitcher.bean.Trip;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.transaction.factory.TransactionFactoryImpl;
import by.gartsmanovich.hitcher.service.TripService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import by.gartsmanovich.hitcher.service.factory.ServiceFactory;
import by.gartsmanovich.hitcher.service.factory.ServiceFactoryImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TripServiceImplTest {

    private ServiceFactory factory;
    private TripService tripService;

    @BeforeClass
    public void setUp() throws Exception {
        factory = new ServiceFactoryImpl(new TransactionFactoryImpl());
        tripService = factory.getTripService();
    }

    @AfterClass
    public void tearDown() throws Exception {
        factory.close();
    }

    @Test(description = "Positive scenario of find list by values method",
            priority = 1)
    public void testPositiveFindTripsByValues() throws Exception {
        List<Trip> actual = tripService.findTripsByValues("1", "2",
                                                          "2019-12-23");
        List<Trip> expected = getExpectedTripList();

        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFindTripsByValuesException")
    private Object[][] getDataForFindTripsByValuesException() {
        return new Object[][]{
                {"d", "2", "2019-12-23"},
                {null, "2", "2019-12-23"},
                {"1", "d", "2019-12-23"},
                {"1", null, "2019-12-23"},
                {"1", "2", "2018-12-23"},
                {"1", "2", "d"},
        };
    }

    @Test(description = "Tests a find trips by values method to throw a" 
                        + " ServiceException",
            expectedExceptions = ServiceException.class,
            dataProvider = "dataForFindTripsByValuesException", priority = 2)
    public void testFindTripsByValuesException(String from, String to,
            String date) throws ServiceException {

        tripService.findTripsByValues(from, to, date);
    }
    
    @Test(description = "Positive scenario of find trips by driver id method",
            priority = 3)
    public void testPositiveFindTripsByUserId() throws ServiceException {
        List<Trip> actual = tripService.findTripsByUserId(3);
        List<Trip> expected = getExpectedTripList();

        assertEquals(actual, expected);
    }

    @Test(description = "Tests a find trips by driver id method to throw" 
                        + " a ServiceException",
            priority = 4, expectedExceptions = ServiceException.class)
    public void testFindTripsByUserId() throws ServiceException {
        tripService.findTripsByUserId(Long.MAX_VALUE);
    }

    @Test(description = "Positive scenario of save trip method", priority = 5)
    @Ignore
    public void testPositiveSave() {
    }

    @Test(description = "Tests a save trip method to throw a ServiceException",
            priority = 6)
    @Ignore
    public void testSaveException() {
    }

    @Test(description = "Positive scenario of update trip method", priority = 7)
    @Ignore
    public void testPositiveUpdate() {
    }

    @Test(description = "Tests a update trip method to throw a ServiceException",
            priority = 8)
    @Ignore
    public void testUpdateException() {
    }
    
    @Test
    @Ignore
    public void testFindTripById() {
    }

    @Test
    @Ignore
    public void testDeleteTripById() {
    }

    @Test
    @Ignore
    public void testAddPassenger() {
    }

    @Test
    @Ignore
    public void testDeletePassenger() {
    }

    private List<Trip> getExpectedTripList() {
        List<Trip> list = new ArrayList<>();
        list.add(getExpectedTrip());
        
        return list;
    }

    private Trip getExpectedTrip() {
        Trip trip = new Trip(1);
        trip.setDriver(fillExpectedUser());

        City from = new City(1);
        from.setCountryID(1);
        from.setCityName("Moscow");
        trip.setFrom(from);

        City to = new City(2);
        to.setCountryID(1);
        to.setCityName("Saint Petersburg");
        trip.setTo(to);

        trip.setDepartureDatetime(LocalDate.of(2019, 12, 23));
        trip.setArrivalDatetime(LocalDate.of(2019, 12, 24));
        trip.setFreeSeats(4);
        trip.setPrice(50);
        trip.setSmokingAllowed(true);
        trip.setPetsAllowed(true);
        
        List<User> passengers = new ArrayList<>();
        passengers.add(addPassenger());
        trip.setPassengers(passengers);
        
        return trip;
    }

    private User fillExpectedUser() {
        User user = new User(3);
        user.setLogin("anna");
        user.setEmail("solovei@gmail.com");
        user.setPassword("4BL8sAw1ZR81rkQihuIex6QDA5RD8eyz+Aa46q7MD4Y=");
        user.setSalt("vggBdm1I60lE7YpK88Ywf5X0EsAGrR");
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        user.setRegistrationDate(LocalDate.parse("2018-07-15"));
        user.setSurname("Solovei");
        user.setName("Anna");
        user.setPatronymic("Alexeevna");
        user.setPhoneNumber("+375 (29) 222-55-55");
        user.setAddress("Slobodskoi pr., 122");
        user.setMusic(3);
        user.setCommunication(2);
        user.setDriverLicenseNumber("1AA 123456");
        user.setCarModel("LADA Calina");
        user.setCarColor("BLUE");

        return user;
    }
    
    private User addPassenger() {
        User user = new User(2);
        user.setLogin("smith");
        user.setEmail("smith@gmail.com");
        user.setPassword("AyAurJOVwkp75h4/vlw1Rni/jI+km5uw+c9b9rHi1Ws=");
        user.setSalt("3Hfzs5SzuDLg3uD12X9CnyFD4rb5jB");
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        user.setRegistrationDate(LocalDate.parse("2018-07-08"));
        user.setSurname("Smith");
        user.setName("John");
        user.setPhoneNumber("+375 (29) 123-45-67");
        user.setAddress("Sovetskaya st., 13, 1");
        user.setMusic(1);
        user.setCommunication(3);
        
        return user;
    }
}
