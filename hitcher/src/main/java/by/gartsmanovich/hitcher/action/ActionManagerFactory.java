package by.gartsmanovich.hitcher.action;

import by.gartsmanovich.hitcher.service.factory.ServiceFactory;

/**
 * Class factory used to create Action manager classes using the provided
 * Service factory..
 *
 * @author Dmitry Gartsmanovich
 */
public final class ActionManagerFactory {

    /**
     * Constructs Action Factory instance.
     */
    private ActionManagerFactory() {
    }

    /**
     * Returns Action Manager instance using the provided Service factory.
     *
     * @param factory the provided Service factory.
     * @return the Action Manager instance.
     */
    public static ActionManager getManager(final ServiceFactory factory) {
        return new ActionManagerImpl(factory);
    }
}

