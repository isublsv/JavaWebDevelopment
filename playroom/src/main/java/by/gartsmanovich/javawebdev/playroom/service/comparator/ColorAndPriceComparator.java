package by.gartsmanovich.javawebdev.playroom.service.comparator;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;

import java.util.Comparator;

public class ColorAndPriceComparator implements Comparator<Toy> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(final Toy o1, final Toy o2) {
        return o1.getColor().ordinal() - o2.getColor().ordinal();
    }

    /**
     * Returns a lexicographic-order comparator with another comparator.
     * If this {@code Comparator} considers two elements equal, i.e.
     * {@code compare(a, b) == 0}, {@code other} is used to determine the order.
     *
     * @param  other the other comparator to be used when this comparator
     *         compares two objects that are equal.
     * @return a lexicographic-order comparator composed of this and then the
     *         other comparator
     * @throws NullPointerException if the argument is null.
     */
    @Override
    public Comparator<Toy> thenComparing(final Comparator<? super Toy> other) {
        return Comparator.comparingDouble(Toy::getPrice);
    }
}
