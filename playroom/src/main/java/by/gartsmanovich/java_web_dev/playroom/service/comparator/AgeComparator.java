package by.gartsmanovich.java_web_dev.playroom.service.comparator;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;

import java.util.Comparator;

public class AgeComparator implements Comparator<Toy> {

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
        return o1.getAge() - o2.getAge();
    }
}
