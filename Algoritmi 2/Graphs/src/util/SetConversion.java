package util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Helper class containing a bunch of utility methods
 * to streamline operations common operations.
 *
 * @author Cameroni Alessio
 */
public class SetConversion {
    
    /**
     * Given a List of type T, adds all elements to
     * a Set of type T
     *
     * @param array         Array containing T elements
     * @return objectSet    Set containing T elements of array
     * @param <T>           Generic type T
     */
    public static <T> Set<T> getSetFromArray(List<T> array) {
        Set<T> objectSet = new HashSet<>();
        objectSet.addAll(array);
        return objectSet;
    }
}
