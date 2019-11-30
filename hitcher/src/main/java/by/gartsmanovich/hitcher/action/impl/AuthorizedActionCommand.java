package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.bean.Role;

import java.util.Arrays;

/**
 * Class used to define the list of authorized action commands.
 *
 * @author Dmitry Gartsmanovich
 */
abstract class AuthorizedActionCommand extends ActionCommand {

    /**
     * Default constructor. Sets the list of default roles.
     */
    AuthorizedActionCommand() {
        getAllowRoles().addAll(Arrays.asList(Role.values()));
    }
}
