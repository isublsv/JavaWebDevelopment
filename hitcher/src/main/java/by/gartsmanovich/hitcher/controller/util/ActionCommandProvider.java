package by.gartsmanovich.hitcher.controller.util;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.action.impl.BanActionCommand;
import by.gartsmanovich.hitcher.action.impl.DeleteTripActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditDriverInfoActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditEmailActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditPasswordActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditPersonalDataActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditPreferencesActionCommand;
import by.gartsmanovich.hitcher.action.impl.EditTripActionCommand;
import by.gartsmanovich.hitcher.action.impl.LoadTripInfoActionCommand;
import by.gartsmanovich.hitcher.action.impl.LoginActionCommand;
import by.gartsmanovich.hitcher.action.impl.LogoutActionCommand;
import by.gartsmanovich.hitcher.action.impl.MainActionCommand;
import by.gartsmanovich.hitcher.action.impl.MyTripsActionCommand;
import by.gartsmanovich.hitcher.action.impl.OfferTripActionCommand;
import by.gartsmanovich.hitcher.action.impl.ProfileActionCommand;
import by.gartsmanovich.hitcher.action.impl.RegisterActionCommand;
import by.gartsmanovich.hitcher.action.impl.TripRegistrationActionCommand;
import by.gartsmanovich.hitcher.action.impl.ShowProfileActionCommand;
import by.gartsmanovich.hitcher.action.impl.ShowTripActionCommand;
import by.gartsmanovich.hitcher.action.impl.TripListActionCommand;
import by.gartsmanovich.hitcher.action.impl.TripUnregisteringActionCommand;
import by.gartsmanovich.hitcher.action.impl.UserListActionCommand;

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
            case "/trip/my_trips":
                return new MyTripsActionCommand();
            case "/trip/offer":
                return new OfferTripActionCommand();
            case "/trip/show":
                return new ShowTripActionCommand();
            case "/trip/delete":
                return new DeleteTripActionCommand();
            case "/trip/edit":
                return new EditTripActionCommand();
            case "/trip/register":
                return new TripRegistrationActionCommand();
            case "/trip/unregister":
                return new TripUnregisteringActionCommand();
            case "/logout":
                return new LogoutActionCommand();
            case "/profile":
                return new ProfileActionCommand();
            case "/profile/show":
                return new ShowProfileActionCommand();
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
            case "/admin/ban":
                return new BanActionCommand();
            case "/admin/user_list":
                return new UserListActionCommand();
            default:
                return new MainActionCommand();
        }
    }
}
