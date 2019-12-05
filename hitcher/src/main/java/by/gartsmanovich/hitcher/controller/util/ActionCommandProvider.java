package by.gartsmanovich.hitcher.controller.util;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.action.impl.BanActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditDriverInfoActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditEmailActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditPasswordActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditPersonalDataActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditPreferencesActionCommand;
import by.gartsmanovich.hitcher.action.impl.LoadTripInfoActionCommand;
import by.gartsmanovich.hitcher.action.impl.LoginActionCommand;
import by.gartsmanovich.hitcher.action.impl.LogoutActionCommand;
import by.gartsmanovich.hitcher.action.impl.MainActionCommand;
import by.gartsmanovich.hitcher.action.impl.ProfileActionCommand;
import by.gartsmanovich.hitcher.action.impl.RegisterActionCommand;
import by.gartsmanovich.hitcher.action.impl.TripListActionCommand;

/**
 * Utility class for Action filter used to find Action command by provided uri.
 *
 * @author Dmitry Gartsmanovich
 */
public enum ActionCommandProvider {

    /**
     * Singleton instance.
     */
    INSTANCE;

    /**
     * Finds and returns Action Commands. Generates a new Action Command
     * instances for each user action.
     *
     * @param uri the provided uri.
     * @return the appropriate Action Command instance.
     */
    public ActionCommand findAction(final String uri) {
        switch (uri) {
            case "/login":
                return new LoginActionCommand();
            case "/register":
                return new RegisterActionCommand();
            case "/trip/load":
                return new LoadTripInfoActionCommand();
            case "/trip/result_list":
                return new TripListActionCommand();
            case "/logout":
                return new LogoutActionCommand();
            case "/profile":
                return new ProfileActionCommand();
            case "/profile/edit/personal_data":
                return new EditPersonalDataActionCommand();
            case "/profile/edit/preferences":
                return new EditPreferencesActionCommand();
            case "/profile/edit/email":
                return new EditEmailActionCommand();
            case "/profile/edit/password":
                return new EditPasswordActionCommand();
            case "/profile/edit/driver_info":
                return new EditDriverInfoActionCommand();
            case "/ban":
                return new BanActionCommand();
            default:
                return new MainActionCommand();
        }
    }
}
