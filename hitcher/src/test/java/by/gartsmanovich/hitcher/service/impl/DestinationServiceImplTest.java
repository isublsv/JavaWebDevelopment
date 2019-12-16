package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.City;
import by.gartsmanovich.hitcher.bean.Destination;
import by.gartsmanovich.hitcher.dao.transaction.factory.TransactionFactoryImpl;
import by.gartsmanovich.hitcher.service.DestinationService;
import by.gartsmanovich.hitcher.service.factory.ServiceFactory;
import by.gartsmanovich.hitcher.service.factory.ServiceFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DestinationServiceImplTest {

    private List<Destination> destinations;
    private ServiceFactory factory;

    @BeforeMethod
    public void setUp() throws Exception {
        factory = new ServiceFactoryImpl(new TransactionFactoryImpl());

        destinations = fillDestinations();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        destinations.clear();
        factory.close();
    }

    @Test(description = "Positive scenario of Find All Destinations")
    public void testFindAll() throws Exception {
        DestinationService destinationService =
                factory.getDestinationService();
        List<Destination> actual = destinationService.findAll();

        Assert.assertEquals(actual, destinations);
    }

    private List<Destination> fillDestinations() {
        List<Destination> destinations = new ArrayList<>();

        Destination destination = new Destination();
        destination.setId(1);
        destination.setCountryName("Russia");

        List<City> cities = new ArrayList<>();

        City moscow = new City(1);
        moscow.setCountryID(1);
        moscow.setCityName("Moscow");
        cities.add(moscow);

        City spb = new City(2);
        spb.setCountryID(1);
        spb.setCityName("Saint Petersburg");
        cities.add(spb);

        destination.setCities(cities);
        destinations.add(destination);

        return destinations;
    }
}
