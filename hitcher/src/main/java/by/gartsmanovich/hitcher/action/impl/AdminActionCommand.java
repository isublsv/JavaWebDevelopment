package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.bean.Role;

/**
 * Class used to define the list of admin action commands.
 *
 * @author Dmitry Gartsmanovich
 */
public abstract class AdminActionCommand extends ActionCommand {

    /**
     * Default constructor. Sets the list of default roles.
     */
    public AdminActionCommand() {
        getAllowRoles().add(Role.ADMIN);
    }
}
