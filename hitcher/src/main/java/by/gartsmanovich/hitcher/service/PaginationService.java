package by.gartsmanovich.hitcher.service;

import by.gartsmanovich.hitcher.bean.Entity;

/**
 * Interface used to describe the common structure of services that support
 * pagination.
 *
 * @param <T> the type of elements which the service can support.
 * @author Dmitry Gartsmanovich
 */
public interface PaginationService<T extends Entity> {
}
