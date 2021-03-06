package by.gartsmanovich.hitcher.bean;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class represents the specific person. Each User entity describes by several
 * parameters such as login, password, role, name, surname and other specific
 * characteristics.
 *
 * @author Dmitry Gartsmanovich
 */
public class User extends Entity {

    /**
     * The login value of user.
     */
    private String login;

    /**
     * The user email.
     */
    private String email;

    /**
     * The password hash value.
     */
    private String password;

    /**
     * The salt value.
     */
    private String salt;

    /**
     * The user role.
     */
    private Role role;

    /**
     * The user status.
     */
    private Status status;

    /**
     * The user registration date in the system.
     */
    private LocalDate registrationDate;

    /**
     * The user surname.
     */
    private String surname;

    /**
     * The user name.
     */
    private String name;

    /**
     * The user patronymic.
     */
    private String patronymic;

    /**
     * The user phone number.
     */
    private String phoneNumber;

    /**
     * The user address.
     */
    private String address;

    /**
     * The favorite user music type.
     */
    private int music;

    /**
     * The communication skill of the user.
     */
    private int communication;

    /**
     * The driver license number.
     */
    private String driverLicenseNumber;

    /**
     * The car model.
     */
    private String carModel;

    /**
     * The car color.
     */
    private String carColor;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructs the user instance with ID value.
     *
     * @param idValue the ID value.
     */
    public User(final long idValue) {
        super(idValue);
    }

    /**
     * Gets login.
     *
     * @return value of login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param loginValue value of login.
     */
    public void setLogin(final String loginValue) {
        login = loginValue;
    }

    /**
     * Gets email.
     *
     * @return value of email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param emailValue value of email.
     */
    public void setEmail(final String emailValue) {
        email = emailValue;
    }

    /**
     * Gets password.
     *
     * @return value of password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param passwordValue value of password.
     */
    public void setPassword(final String passwordValue) {
        password = passwordValue;
    }

    /**
     * Gets salt.
     *
     * @return value of salt.
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets salt.
     *
     * @param saltValue value of salt.
     */
    public void setSalt(final String saltValue) {
        salt = saltValue;
    }

    /**
     * Gets role.
     *
     * @return value of role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param roleValue value of role.
     */
    public void setRole(final Role roleValue) {
        role = roleValue;
    }

    /**
     * Gets status.
     *
     * @return value of status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param statusValue value of status.
     */
    public void setStatus(final Status statusValue) {
        status = statusValue;
    }

    /**
     * Gets registrationDate.
     *
     * @return value of registrationDate.
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets registrationDate.
     *
     * @param registrationDateValue value of registrationDate.
     */
    public void setRegistrationDate(final LocalDate registrationDateValue) {
        registrationDate = registrationDateValue;
    }

    /**
     * Gets surname.
     *
     * @return value of surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surnameValue value of surname.
     */
    public void setSurname(final String surnameValue) {
        surname = surnameValue;
    }

    /**
     * Gets name.
     *
     * @return value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param nameValue value of name.
     */
    public void setName(final String nameValue) {
        name = nameValue;
    }

    /**
     * Gets patronymic.
     *
     * @return value of patronymic.
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Sets patronymic.
     *
     * @param patronymicValue value of patronymic.
     */
    public void setPatronymic(final String patronymicValue) {
        patronymic = patronymicValue;
    }

    /**
     * Gets phoneNumber.
     *
     * @return value of phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phoneNumber.
     *
     * @param phoneNumberValue value of phoneNumber.
     */
    public void setPhoneNumber(final String phoneNumberValue) {
        phoneNumber = phoneNumberValue;
    }

    /**
     * Gets address.
     *
     * @return value of address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param addressValue value of address.
     */
    public void setAddress(final String addressValue) {
        address = addressValue;
    }

    /**
     * Gets music.
     *
     * @return value of music.
     */
    public int getMusic() {
        return music;
    }

    /**
     * Sets music.
     *
     * @param musicValue value of music.
     */
    public void setMusic(final int musicValue) {
        music = musicValue;
    }

    /**
     * Gets communication.
     *
     * @return value of communication.
     */
    public int getCommunication() {
        return communication;
    }

    /**
     * Sets communication.
     *
     * @param communicationValue value of communication.
     */
    public void setCommunication(final int communicationValue) {
        communication = communicationValue;
    }

    /**
     * Gets driverLicenseNumber.
     *
     * @return value of driverLicenseNumber.
     */
    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    /**
     * Sets driverLicenseNumber.
     *
     * @param driverLicenseNumberValue value of driverLicenseNumber.
     */
    public void setDriverLicenseNumber(final String driverLicenseNumberValue) {
        driverLicenseNumber = driverLicenseNumberValue;
    }

    /**
     * Gets carModel.
     *
     * @return value of carModel.
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * Sets carModel.
     *
     * @param carModelValue value of carModel.
     */
    public void setCarModel(final String carModelValue) {
        carModel = carModelValue;
    }

    /**
     * Gets carColor.
     *
     * @return value of carColor.
     */
    public String getCarColor() {
        return carColor;
    }

    /**
     * Sets carColor.
     *
     * @param carColorValue value of carColor.
     */
    public void setCarColor(final String carColorValue) {
        carColor = carColorValue;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + login.hashCode();
        result = PRIME * result + email.hashCode();
        result = PRIME * result + password.hashCode();
        result = PRIME * result + salt.hashCode();
        result = PRIME * result + role.hashCode();
        result = PRIME * result + status.hashCode();
        result = PRIME * result + registrationDate.hashCode();
        result = PRIME * result + surname.hashCode();
        result = PRIME * result + name.hashCode();
        result = getResultHash(result, patronymic);
        result = getResultHash(result, phoneNumber);
        result = getResultHash(result, address);
        result = PRIME * result + music;
        result = PRIME * result + communication;
        result = getResultHash(result, driverLicenseNumber);
        result = getResultHash(result, carModel);
        result = getResultHash(result, carColor);
        return result;
    }

    /**
     * Gets result hashcode value from provided value.
     *
     * @param result the current hashcode value.
     * @param value the parameter used to calculate a new hashcode.
     * @return a new hashcode value.
     */
    private int getResultHash(final int result, final String value) {
        int hash = result;
        if (value != null) {
            hash = PRIME * hash + value.hashCode();
        } else {
            hash = PRIME * hash;
        }
        return hash;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (music != user.music) {
            return false;
        }
        if (communication != user.communication) {
            return false;
        }
        if (!login.equals(user.login)) {
            return false;
        }
        if (!email.equals(user.email)) {
            return false;
        }
        if (!Objects.equals(password, user.password)) {
            return false;
        }
        if (!Objects.equals(salt, user.salt)) {
            return false;
        }
        if (role != user.role) {
            return false;
        }
        if (status != user.status) {
            return false;
        }
        if (!Objects.equals(registrationDate, user.registrationDate)) {
            return false;
        }
        if (!Objects.equals(surname, user.surname)) {
            return false;
        }
        if (!Objects.equals(name, user.name)) {
            return false;
        }
        if (!Objects.equals(patronymic, user.patronymic)) {
            return false;
        }
        if (!Objects.equals(phoneNumber, user.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(address, user.address)) {
            return false;
        }
        if (!Objects.equals(driverLicenseNumber, user.driverLicenseNumber)) {
            return false;
        }
        if (!Objects.equals(carModel, user.carModel)) {
            return false;
        }
        return Objects.equals(carColor, user.carColor);
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format(
                "User{id=%d, login='%s', email='%s', password='%s', salt='%s', "
                + "role=%s, status=%s, registrationDate=%s, surname='%s', "
                + "name='%s', patronymic='%s', phoneNumber='%s', "
                + "address='%s', music=%d, communication=%d, "
                + "driverLicenseNumber='%s', carModel='%s', carColor='%s'}",
                getId(), login, email, password, salt, role, status,
                registrationDate, surname, name, patronymic, phoneNumber,
                address, music, communication, driverLicenseNumber, carModel,
                carColor);
    }
}
