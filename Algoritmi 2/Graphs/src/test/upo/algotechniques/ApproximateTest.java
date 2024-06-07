package upo.algotechniques;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upo.graph.base.Edge;
import upo.graph.base.Vertex;
import upo.graph.impl.IndicListUndirWeight;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApproximateTest {
    IndicListUndirWeight graph;
    Vertex v1;
    Vertex v2;
    Vertex v3;
    Vertex v4;
    Vertex v5;
    
    @BeforeEach
    void init() {
        graph = new IndicListUndirWeight();
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
        
        graph.setEdgeWeight(Edge.getEdgeByVertexes(v1, v2), 1.0);
        graph.setEdgeWeight(Edge.getEdgeByVertexes(v1, v3), 2.0);
        graph.setEdgeWeight(Edge.getEdgeByVertexes(v3, v4), 3.0);
        graph.setEdgeWeight(Edge.getEdgeByVertexes(v2, v5), 4.0);
    }
    
    @Test
    void approxTSP() {
        List<Vertex> expectedResults = Arrays.asList(v2, v1, v4, v3);
        assertEquals(expectedResults, Approximate.approxTSP(graph));
    }
}