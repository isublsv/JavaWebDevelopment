package by.gartsmanovich.hitcher.service.impl;

import by.gartsmanovich.hitcher.bean.Role;
import by.gartsmanovich.hitcher.bean.Status;
import by.gartsmanovich.hitcher.bean.User;
import by.gartsmanovich.hitcher.dao.transaction.factory.TransactionFactoryImpl;
import by.gartsmanovich.hitcher.service.UserService;
import by.gartsmanovich.hitcher.service.exception.ServiceException;
import by.gartsmanovich.hitcher.service.factory.ServiceFactory;
import by.gartsmanovich.hitcher.service.factory.ServiceFactoryImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

public class UserServiceImplTest {

    private ServiceFactory factory;
    private UserService userService;

    @BeforeClass
    public void setUp() throws Exception {
        factory = new ServiceFactoryImpl(new TransactionFactoryImpl());
        userService = factory.getUserService();
    }

    @AfterClass
    public void tearDown() throws Exception {
        factory.close();
    }

    @Test(description = "Positive scenario of save user method", priority = 1)
    public void testPositiveSave() throws Exception {
        User actualUser = userService.save("login", "email@email.email",
                                           "aaZZa44@");
        User expectedUser = getExpectedUser();

        assertEquals(actualUser, expectedUser);
    }

    @DataProvider(name = "dataForSaveException")
    private Object[][] getDataForSaveException() {
        return new Object[][]{
                {"login", "any_email@email.email", "aaZZa44@"},
                {"login@", "any_email@email.email", "aaZZa44@"},
                {"log", "any_email@email.email", "aaZZa44@"},
                {"flex", "email@email.email", "aaZZa44@"},
                {"flex", "@email.email", "aaZZa44@"},
                {"flex", "flex@.emailemail", "aaZZa44@"},
                {"flex", "flex@email.email", "aaZZa445"},
                {"flex", "flex@email.email", "aaZZa@@@"},
                {"flex", "flex@email.email", "aaaaa44@"},
                {"flex", "flex@email.email", "aaa a44@"},
                {"flex", "flex@email.email", "aaZa4@"},
        };
    }

    @Test(description = "Tests a save method to throw a ServiceException",
            expectedExceptions = ServiceException.class,
            dataProvider = "dataForSaveException", priority = 2)
    public void testSaveException(String login, String email, String pass)
            throws ServiceException {
        userService.save(login, email, pass);
    }
    
    @Test(description = "Positive scenario of update personal data method",
            priority = 3)
    public void testPositiveUpdatePersonalData() throws Exception {
        User actual = userService.updatePersonalData(fillUserForUpdate());
        User expected = fillUserForUpdate();

        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForUpdatePersonalDataException")
    private Object[][] getDataForUpdatePersonalDataException() {
        return new Object[][]{
                {"a", "surname", "patronymic", "+375 (29) 222-55-55", "Slobodskoi pr., 122"},
                {null, "surname", "patronymic", "+375 (29) 222-55-55", "Slobodskoi pr., 122"},
                {"a1", "surname", "patronymic", "+375 (29) 222-55-55", "Slobodskoi pr., 122"},
                {"name", "surname1", "patronymic", "+375 (29) 222-55-55", "Slobodskoi pr., 122"},
                {"name", null, "patronymic", "+375 (29) 222-55-55", "Slobodskoi pr., 122"},
                {"name", "surname", "patronymic1", "+375 (29) 222-55-55", "Slobodskoi pr., 122"},
                {"name", "surname", null, "+375 (29) 222-55-55", "Slobodskoi pr., 122"},
                {"name", "surname", "patronymic1", "+375 (29) 222-55-5", "Slobodskoi pr., 122"},
                {"name", "surname", "patronymic1", null, "Slobodskoi pr., 122"},
                {"name", "surname", "patronymic1", "+375 (29) 222-55-55", "Slobodskoi pr., 122@"},
                {"name", "surname", "patronymic1", "+375 (29) 222-55-55", null},
        };
    }

    @Test(description = "Tests a update personal data method to throw a" 
                        + " ServiceException",
            expectedExceptions = ServiceException.class,
            dataProvider = "dataForUpdatePersonalDataException", priority = 4)
    public void testUpdatePersonalDataException(String name, String surname,
            String patronymic, String phone, String address)
            throws ServiceException {
        
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setPhoneNumber(phone);
        user.setAddress(address);

        userService.updatePersonalData(user);
    }

    @Test(description = "Positive scenario of update preferences method",
            priority = 5)
    public void testPositiveUpdatePreferences() throws Exception {
        User actual = userService.updatePreferences(3, "3", "2");
        User expected = fillUserForUpdate();

        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForUpdatePreferencesException")
    private Object[][] getDataForUpdatePreferencesException() {
        return new Object[][]{
                {5, "1", "1"},
                {3, "5", "1"},
                {3, "a", "1"},
                {3, null, "1"},
                {3, "1", "5"},
                {3, "1", "a"},
                {3, "1", null},
        };
    }

    @Test(description = "Tests a update preferences method to throw a"
                        + " ServiceException",
            expectedExceptions = ServiceException.class,
            dataProvider = "dataForUpdatePreferencesException", priority = 6)
    public void testUpdatePreferencesException(long id, String music,
            String communication) throws ServiceException {

        userService.updatePreferences(id, music, communication);
    }
    
    @Test(description = "Positive scenario of update email method",
            priority = 7)
    public void testPositiveUpdateEmail() throws Exception {
        User actual = userService.updateEmail(3, "solovei@gmail.com");
        User expected = fillUserForUpdate();

        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForUpdateEmailException")
    private Object[][] getDataForUpdateEmailException() {
        return new Object[][]{
                {5, "any_email@email.email"},
                {3, null},
                {3, "@email.email"},
        };
    }

    @Test(description = "Tests a update email method to throw a"
                        + " ServiceException",
            expectedExceptions = ServiceException.class,
            dataProvider = "dataForUpdateEmailException", priority = 8)
    public void testUpdateEmailException(long id, String email)
            throws ServiceException {

        userService.updateEmail(id, email);
    }

    @Test(description = "Positive scenario of update password method",
            priority = 9)
    @Ignore
    public void testPositiveUpdatePassword() throws Exception {
        
        //generate a new password and salt after each update
        User actual = userService.updatePassword(3, "aaZZa44@", "aaZZa44@");
        User expected = fillUserForUpdate();
        expected.setPassword(null);
        expected.setSalt(null);
        
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForUpdatePasswordException")
    private Object[][] getDataForUpdatePasswordException() {
        return new Object[][]{
                {5, "anna", "aaZZa44@"},
                {3, "anna1", "aaZZa44@"},
                {3, null, "aaZZa44@"},
                {3, "anna", "aaZZa@@@"},
                {3, "anna", "aaaaa44@"},
                {3, "anna", "aaa a44@"},
                {3, "anna", "aaZa4@"},
                {3, "anna", null},
        };
    }

    @Test(description = "Tests a update password method to throw a"
                        + " ServiceException",
            expectedExceptions = ServiceException.class,
            dataProvider = "dataForUpdatePasswordException", priority = 10)
    public void testUpdatePasswordException(long id, String pass,
            String newPass) throws ServiceException {

        userService.updatePassword(id, pass, newPass);
    }

    @Test(description = "Positive scenario of update driver info method",
            priority = 11)
    public void testPositiveUpdateDriverInfo() throws ServiceException {
        userService.updateDriverInfo(3, "1AA 123456", "LADA Calina", "BLUE");
    }

    @DataProvider(name = "dataForUpdateDriverInfoException")
    private Object[][] getDataForUpdateDriverInfoException() {
        return new Object[][]{
                {5, "1AA 123456", "LADA Calina", "BLUE"},
                {3, "11A 123456", "LADA Calina", "BLUE"},
                {3, null, "LADA Calina", "BLUE"},
                {3, "1AA 123456", "LADA_Calina", "BLUE"},
                {3, "1AA 123456", null, "BLUE"},
                {3, "1AA 123456", "LADA Calina", "BLUE!"},
                {3, "1AA 123456", "LADA Calina", null},
        };
    }

    @Test(description = "Tests a update driver info method to throw a"
                        + " ServiceException",
            expectedExceptions = ServiceException.class,
            dataProvider = "dataForUpdateDriverInfoException", priority = 12)
    public void testUpdateDriverInfoException(long id, String license,
            String carModel, String carColor) throws ServiceException {

        userService.updateDriverInfo(id, license, carModel, carColor);
    }

    @Test(description = "Positive scenario of find by ID method", priority = 13)
    public void testPositiveFindByID() throws Exception {
        User actual = userService.findByID(3);
        User expected = fillUserForUpdate();
        
        assertEquals(actual, expected);
    }

    @Test(description = "Tests a find by ID method to throw a ServiceException",
            expectedExceptions = ServiceException.class, priority = 14)
    public void testFindByIDException() throws ServiceException {
        userService.findByID(Long.MAX_VALUE);
    }

    @Test(description = "Positive scenario of find by login and pass method",
            priority = 15)
    public void testPositiveFindByLoginAndPassword() throws Exception {
        User actual = userService.findByLoginAndPassword("anna", "anna");
        User expected = fillUserForFindByLoginAndPass();
        
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFFindByLoginAndPasswordException")
    private Object[][] getDataForFindByLoginAndPasswordException() {
        return new Object[][]{
                {"anna1", "anna"},
                {null, "anna"},
                {"anna", "anna1"},
                {"anna", null},
        };
    }

    @Test(description = "Tests a find user by login and pass method to throw a"
                        + " ServiceException",
            expectedExceptions = ServiceException.class,
            dataProvider = "dataForFFindByLoginAndPasswordException", priority = 16)
    public void testFindByLoginAndPasswordException(String login, String pass)
            throws ServiceException {

        userService.findByLoginAndPassword(login, pass);
    }
    
    @Test(description = "Positive scenario of find by profile ID method",
            priority = 17)
    public void testPositiveFindUserProfileByID() throws Exception {
        User actual = userService.findUserProfileByID("3");
        User expected = fillUserForUpdate();

        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFindUserProfileByIDException")
    private Object[][] getDataForFindUserProfileByIDException() {
        return new Object[][]{
                {"a"}, {null}
        };
    }

    @Test(description = "Tests a find profile by ID to throw a"
                        + " ServiceException",
            expectedExceptions = ServiceException.class,
            dataProvider = "dataForFindUserProfileByIDException", priority = 18)
    public void testFindUserProfileByIDException(String id)
            throws ServiceException {

        userService.findUserProfileByID(id);
    }
    
    @Test
    @Ignore
    public void testDelete() {
    }

    @Test
    @Ignore
    public void testFindAll() {
    }

    private User getExpectedUser() {
        User expectedUser = new User(5);
        expectedUser.setLogin("login");
        expectedUser.setEmail("email@email.email");
        expectedUser.setRole(Role.USER);
        expectedUser.setStatus(Status.ACTIVE);
        expectedUser.setRegistrationDate(LocalDate.now());
        return expectedUser;
    }

    private User fillUserForUpdate() {
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

    private User fillUserForFindByLoginAndPass() {
        User user = new User(3);
        user.setLogin("anna");
        user.setEmail("solovei@gmail.com");
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);

        return user;
    }
}
