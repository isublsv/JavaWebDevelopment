package by.gartsmanovich.hitcher.action.impl;

import by.gartsmanovich.hitcher.action.ActionCommand;
import by.gartsmanovich.hitcher.bean.Role;

import java.util.Arrays;

/**
 * @author Dmitry Gartsmanovich
 */
public abstract  class AuthorizedActionCommand extends ActionCommand {

    /**
     *
     */
    public AuthorizedActionCommand() {
        getAllowRoles().addAll(Arrays.asList(Role.values()));
    }
}
