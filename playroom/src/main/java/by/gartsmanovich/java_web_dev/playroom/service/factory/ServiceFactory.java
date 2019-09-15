package by.gartsmanovich.java_web_dev.playroom.service.factory;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.service.PlayRoomService;
import by.gartsmanovich.java_web_dev.playroom.service.impl.PlayRoomServiceImpl;

public final class ServiceFactory {

    /**
     * The factory instance will be created at the start of the execution of
     * ServiceFactory class.
     */
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    /**
     * The variable provides the access to Play Room Service methods.
     */
    private final PlayRoomService<Toy> playRoomService = new
            PlayRoomServiceImpl();

    private ServiceFactory() {

    }

    /**
     * Global point for access to factory methods.
     *
     * @return the instance of Service factory.
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the implementation of the PlayRoomFactory interface.
     * An instance provide the access to Service application layer methods.
     *
     * @return an instance of PlayRoom Service
     */
    public PlayRoomService<Toy> getPlayRoomService() {
        return playRoomService;
    }
}
