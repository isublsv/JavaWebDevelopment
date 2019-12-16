package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.Review;
import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.bean.Status;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.transaction.factory.TransactionFactoryImpl;
import by.gartsmanovich.hitcher.service.ReviewService;
import by.gartsmanovich.hitcher.service.factory.ServiceFactory;
import by.gartsmanovich.hitcher.service.factory.ServiceFactoryImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ReviewServiceImplTest {

    private ServiceFactory factory;
    private ReviewService reviewService;

    @BeforeClass
    public void setUp() throws Exception {
        factory = new ServiceFactoryImpl(new TransactionFactoryImpl());
        reviewService = factory.getReviewService();
    }

    @AfterClass
    public void tearDown() throws Exception {
        factory.close();
    }

    @DataProvider(name = "PositiveDataForFindReviewsByWhoID")
    private Object[][] getPositiveDataForFindReviewsByWhoID() {
        return new Object[][]{{fillExpectedList(), 3},
                {Collections.emptyList(), 100}};
    }

    @DataProvider(name = "NegativeDataForFindReviewsByWhoID")
    private Object[][] getNegativeDataForFindReviewsByWhoID() {
        return new Object[][]{{fillExpectedList(), 2},};
    }

    @Test(dataProvider = "PositiveDataForFindReviewsByWhoID", description = 
            "Positive scenario of Find Reviews By 'Who' ID")
    public void testPositiveFindReviewsByWhoID(List<Review> reviews,
            long id) throws Exception {
        List<Review> actual = reviewService.findReviewsByWhoID(id);

        //content are identical but assertEquals(actual, expected) is failed
        assertEquals(actual.toString(), reviews.toString());
    }

    @Test(dataProvider = "NegativeDataForFindReviewsByWhoID", description = 
            "Negative scenario of Find Reviews By 'Who' ID")
    public void testNegativeFindReviewsByWhoID(List<Review> reviews,
            long id) throws Exception {
        List<Review> actual = reviewService.findReviewsByWhoID(id);

        assertNotEquals(actual, reviews);
    }

    @DataProvider(name = "PositiveDataForFindReviewsByAboutID")
    private Object[][] getPositiveDataForFindReviewsByAboutID() {
        return new Object[][]{{fillExpectedList(), 2},
                {Collections.emptyList(), 100}};
    }

    @DataProvider(name = "NegativeDataForFindReviewsByAboutID")
    private Object[][] getNegativeDataForFindReviewsBAboutID() {
        return new Object[][]{{fillExpectedList(), 3},};
    }

    @Test(dataProvider = "PositiveDataForFindReviewsByAboutID", description 
            = "Positive scenario of Find Reviews By 'About' ID")
    public void testPositiveFindReviewsByAboutID(List<Review> reviews,
            long id) throws Exception {

        List<Review> actual = reviewService.findReviewsByAboutID(id);

        //same as testPositiveFindReviewsByWhoID test
        assertEquals(actual.toString(), reviews.toString());
    }

    @Test(dataProvider = "NegativeDataForFindReviewsByAboutID", description 
            = "Negative scenario of Find Reviews By 'About' ID")
    public void testNegativeFindReviewsByAboutID(List<Review> reviews,
            long id) throws Exception {
        List<Review> actual = reviewService.findReviewsByWhoID(id);

        assertNotEquals(actual, reviews);
    }

    private List<Review> fillExpectedList() {

        List<Review> reviews = new ArrayList<>();

        User about = new User(3);
        about.setLogin("anna");
        about.setEmail("solovei@gmail.com");
        about.setPassword("4BL8sAw1ZR81rkQihuIex6QDA5RD8eyz+Aa46q7MD4Y=");
        about.setSalt("vggBdm1I60lE7YpK88Ywf5X0EsAGrR");
        about.setRole(Role.USER);
        about.setStatus(Status.ACTIVE);
        about.setRegistrationDate(LocalDate.parse("2018-07-15"));
        about.setSurname("Solovei");
        about.setName("Anna");
        about.setPatronymic("Alexeevna");
        about.setPhoneNumber("+375 (29) 222-55-55");
        about.setAddress("Slobodskoi pr., 122");
        about.setMusic(3);
        about.setCommunication(2);
        about.setDriverLicenseNumber("1AA 123456");
        about.setCarModel("LADA Calina");
        about.setCarColor("BLUE");

        User who = new User(2);
        who.setLogin("smith");
        who.setEmail("smith@gmail.com");
        who.setPassword("AyAurJOVwkp75h4/vlw1Rni/jI+km5uw+c9b9rHi1Ws=");
        who.setSalt("3Hfzs5SzuDLg3uD12X9CnyFD4rb5jB");
        who.setRole(Role.USER);
        who.setStatus(Status.ACTIVE);
        who.setRegistrationDate(LocalDate.parse("2018-07-08"));
        who.setSurname("Smith");
        who.setName("John");
        who.setPhoneNumber("+375 (29) 123-45-67");
        who.setAddress("Sovetskaya st., 13, 1");
        who.setMusic(1);
        who.setCommunication(3);

        Review review = new Review(1, about, who, "Приятный человек!", 5);
        reviews.add(review);

        return reviews;
    }
}
