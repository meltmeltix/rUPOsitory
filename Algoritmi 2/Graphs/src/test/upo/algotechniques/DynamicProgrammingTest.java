package upo.algotechniques;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upo.graph.base.Edge;
import upo.graph.base.Vertex;
import upo.graph.impl.IndicListUndir;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingTest {
    IndicListUndir fun;
    Vertex v1;
    Vertex v2;
    Vertex v3;
    Vertex v4;
    Vertex v5;
    
    @BeforeEach
    void init() {
        fun = new IndicListUndir();
        v1 = Vertex.getVertexByLabel("1");
        v2 = Vertex.getVertexByLabel("2");
        v3 = Vertex.getVertexByLabel("3");
        v4 = Vertex.getVertexByLabel("4");
        v5 = Vertex.getVertexByLabel("5");
        
        fun.addVertex(v1);
        fun.addVertex(v2);
        fun.addVertex(v3);
        fun.addVertex(v4);
        fun.addVertex(v5);
        
        fun.addEdge(Edge.getEdgeByVertexes(v1, v2));
        fun.addEdge(Edge.getEdgeByVertexes(v1, v3));
        fun.addEdge(Edge.getEdgeByVertexes(v3, v4));
        fun.addEdge(Edge.getEdgeByVertexes(v2, v5));
    }
    
    @Test
    void getMSI() {
    
    }
}