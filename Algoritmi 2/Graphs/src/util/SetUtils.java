package util;

import upo.graph.base.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Helper class containing a bunch of utility methods
 * to streamline operations common operations.
 *
 * @author Cameroni Alessio
 */
public class SetUtils {

    /**
     * Creates a new Set of vertex and adds every element of the
     * array to said set.
     *
     * @param array         Array of Vertices
     * @return vertexSet    Set of Vertices
     */
    public static Set<Vertex> getVerticesFromArray(List<Vertex> array) {
        Set<Vertex> vertexSet = new HashSet<>();
        vertexSet.addAll(array);
        return vertexSet;
    }
}
