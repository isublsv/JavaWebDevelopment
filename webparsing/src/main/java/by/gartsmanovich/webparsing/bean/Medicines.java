package by.gartsmanovich.webparsing.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to store drug objects.
 *
 * @author Dmitry Gartsmanovich
 */
public class Medicines {

    /**
     * The list of the drugs.
     */
    private List<Drug> drugList;

    /**
     * Constructs the instance of medicines.
     */
    public Medicines() {
        drugList = new ArrayList<>();
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param drug the element to be appended to this list.
     * @return true if the list changed as a result of the call.
     */
    public boolean addDrug(final Drug drug) {
        return drugList.add(drug);
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.
     *
     * @param drug the element to be removed from this list, if present.
     * @return true if an element was removed as a result of this call
     */
    public boolean removeDrug(final Drug drug) {
        return drugList.remove(drug);
    }
}
