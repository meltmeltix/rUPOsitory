package upo.algotechniques;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upo.graph.base.Edge;
import upo.graph.base.Vertex;
import upo.graph.impl.IndicListUndir;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicProgrammingTest {
    IndicListUndir graph;
    Vertex v1;
    Vertex v2;
    Vertex v3;
    Vertex v4;
    Vertex v5;
    
    @BeforeEach
    void init() {
        graph = new IndicListUndir();
        v1 = Vertex.getVertexByLabel("1");
        v2 = Vertex.getVertexByLabel("2");
        v3 = Vertex.getVertexByLabel("3");
        v4 = Vertex.getVertexByLabel("4");
        v5 = Vertex.getVertexByLabel("5");
        
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        
        graph.addEdge(Edge.getEdgeByVertexes(v1, v2));
        graph.addEdge(Edge.getEdgeByVertexes(v1, v3));
        graph.addEdge(Edge.getEdgeByVertexes(v3, v4));
        graph.addEdge(Edge.getEdgeByVertexes(v2, v5));
    }
    
    @Test
    void getMSI() {
        Map<Vertex, Integer> vertexWeights = new HashMap<>() {{
            put(v1, 2);
            put(v2, 7);
            put(v3, 3);
            put(v4, 4);
            put(v5, 9);
        }};

        Set<Vertex> expectedResult = new HashSet<>(Arrays.asList(v2, v5));
        assertEquals(expectedResult, DynamicProgramming.getMSI(graph, vertexWeights));
    }
}