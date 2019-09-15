package by.gartsmanovich.java_web_dev.playroom.dao.factory;

public final class DAOFactory {

    /**
     * The factory instance will be created at the start of the execution of
     * DAOFactory class.
     */
    private static final DAOFactory INSTANCE = new DAOFactory();

    private DAOFactory() {

    }

    /**
     * Global point for access to factory methods.
     *
     * @return the instance of Data Access Object factory.
     */
    public static DAOFactory getInstance() {
        return INSTANCE;
    }


}
