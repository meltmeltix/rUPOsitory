package upo.graph.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upo.graph.base.Edge;
import upo.graph.base.Vertex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IndicListUndirTest {
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
    void addVertex() {
        // New vertices
        assertEquals(5, fun.addVertex(Vertex.getVertexByLabel("6")));
        assertEquals(6, fun.addVertex(Vertex.getVertexByLabel("7")));
        System.out.println(fun.getVertices());

        // Already existing vertices
        assertThrows(UnsupportedOperationException.class, () -> fun.addVertex(v1));
        assertThrows(UnsupportedOperationException.class, () -> fun.addVertex(v5));
    }

    @Test
    void getVertices() {
        Set<Vertex> vertices = new HashSet<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);

        assertEquals(vertices, fun.getVertices());
    }

    @Test
    void getEdges() {
        Set<Edge> edges = new HashSet<>();
        edges.add(Edge.getEdgeByVertexes(v1, v2));
        edges.add(Edge.getEdgeByVertexes(v2, v1));
        edges.add(Edge.getEdgeByVertexes(v1, v3));
        edges.add(Edge.getEdgeByVertexes(v3, v1));
        edges.add(Edge.getEdgeByVertexes(v3, v4));
        edges.add(Edge.getEdgeByVertexes(v4, v3));
        edges.add(Edge.getEdgeByVertexes(v2, v5));
        edges.add(Edge.getEdgeByVertexes(v5, v2));

        assertEquals(edges, fun.getEdges());
    }

    @Test
    void containsVertex() {
        // Vertex is in list
        assertTrue(fun.containsVertex(v2));
        assertTrue(fun.containsVertex(v3));

        // Vertex isn't in the list
        assertFalse(fun.containsVertex(Vertex.getVertexByLabel("98")));
        assertFalse(fun.containsVertex(Vertex.getVertexByLabel("99")));
    }

    @Test
    void removeVertex() {
        // Successfully remove vertex
        assertDoesNotThrow(() -> fun.removeVertex(Vertex.getVertexByLabel("2")));
        assertDoesNotThrow(() -> fun.removeVertex(Vertex.getVertexByLabel("3")));
        System.out.println(fun.getVertices());

        // Requested vertex does not exist
        assertThrows(NoSuchElementException.class, () -> fun.removeVertex(Vertex.getVertexByLabel("2")));
        assertThrows(NoSuchElementException.class, () -> fun.removeVertex(Vertex.getVertexByLabel("3")));
    }

    @Test
    void addEdge() {
        // Adding edges
        assertDoesNotThrow(() -> fun.addEdge(Edge.getEdgeByVertexes(v2, v4)));
        assertDoesNotThrow(() -> fun.addEdge(Edge.getEdgeByVertexes(v3, v5)));
        System.out.println(fun.getEdges());

        // Adding edges with non-existent vertices
        assertThrows(
            IllegalArgumentException.class,
            () -> fun.addEdge(Edge.getEdgeByVertexes(Vertex.getVertexByLabel("6"), Vertex.getVertexByLabel("7")))
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> fun.addEdge(Edge.getEdgeByVertexes(Vertex.getVertexByLabel("2"), Vertex.getVertexByLabel("7")))
        );
    }

    @Test
    void containsEdge() {
        // Edge is in list
        assertTrue(fun.containsEdge(Edge.getEdgeByVertexes(v1, v2)));
        assertTrue(fun.containsEdge(Edge.getEdgeByVertexes(v1, v3)));

        // Edge isn't in the list
        assertFalse(fun.containsEdge(Edge.getEdgeByVertexes(v1, v5)));
        assertFalse(fun.containsEdge(Edge.getEdgeByVertexes(v1, v4)));
    }

    @Test
    void removeEdge() {
        // Successfully remove edge
        assertDoesNotThrow(() -> fun.removeEdge(Edge.getEdgeByVertexes(v1, v2)));
        assertDoesNotThrow(() -> fun.removeEdge(Edge.getEdgeByVertexes(v1, v3)));
        System.out.println(fun.getEdges());

        // Requested edge does not exist
        assertThrows(NoSuchElementException.class, () -> fun.removeEdge(Edge.getEdgeByVertexes(v1, v5)));
        assertThrows(NoSuchElementException.class, () -> fun.removeEdge(Edge.getEdgeByVertexes(v1, v4)));

        // Requested edge does not contain vertices within graph
        assertThrows(
            IllegalArgumentException.class,
            () -> fun.removeEdge(Edge.getEdgeByVertexes(v1, Vertex.getVertexByLabel("97")))
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> fun.removeEdge(Edge.getEdgeByVertexes(Vertex.getVertexByLabel("98"), Vertex.getVertexByLabel("99")))
        );
    }

    @Test
    void getAdjacent() {
        Set<Vertex> first = new HashSet<>(Arrays.asList(v2, v3));
        Set<Vertex> second = new HashSet<>(Arrays.asList(v1, v5));

        // Adjacent vertex exists
        assertEquals(first, fun.getAdjacent(v1));
        assertEquals(second, fun.getAdjacent(v2));

        // Vertex isn't in the list
        assertThrows(NoSuchElementException.class, () -> fun.getAdjacent(Vertex.getVertexByLabel("98")));
        assertThrows(NoSuchElementException.class, () -> fun.getAdjacent(Vertex.getVertexByLabel("99")));
    }

    @Test
    void isAdjacent() {
        // Vertices are adjacent
        assertTrue(fun.isAdjacent(v2, v1));
        assertTrue(fun.isAdjacent(v3, v1));

        // Vertices are not adjacent
        assertFalse(fun.isAdjacent(v5, v1));
        assertFalse(fun.isAdjacent(v4, v1));

        // Target or source vertex are not in the list
        assertThrows(
            IllegalArgumentException.class,
            () -> fun.isAdjacent(v1, Vertex.getVertexByLabel("97"))
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> fun.isAdjacent(Vertex.getVertexByLabel("98"), Vertex.getVertexByLabel("99"))
        );
    }

    @Test
    void size() { assertEquals(5, fun.size()); }

    @Test
    void isDirected() { assertFalse(fun.isDirected()); }

    @Test
    void isCyclic() {
        // Graph is not cyclic
        assertFalse(fun.isCyclic());

        // Graph is cyclic
        fun.addEdge(Edge.getEdgeByVertexes(v4, v5));
        assertTrue(fun.isCyclic());
    }

    @Test
    void isDAG() { assertFalse(fun.isDAG()); }

    @Test
    void getBFSTree() {


        // startingVertex is not in the list
        assertThrows(IllegalArgumentException.class, () -> fun.getBFSTree(Vertex.getVertexByLabel("98")));
        assertThrows(IllegalArgumentException.class, () -> fun.getBFSTree(Vertex.getVertexByLabel("99")));
    }

    @Test
    void getDFSTree() {
        // startingVertex is not in the list
        assertThrows(IllegalArgumentException.class, () -> fun.getDFSTree(Vertex.getVertexByLabel("98")));
        assertThrows(IllegalArgumentException.class, () -> fun.getDFSTree(Vertex.getVertexByLabel("99")));
    }

    @Test
    void getDFSTOTForest() {
        // startingVertex is not in the list
        assertThrows(IllegalArgumentException.class, () -> fun.getDFSTOTForest(Vertex.getVertexByLabel("98")));
        assertThrows(IllegalArgumentException.class, () -> fun.getDFSTOTForest(Vertex.getVertexByLabel("99")));
    }

    @Test
    void getDFSTOTForestOrdered() {

    }

    @Test
    void topologicalSort() { assertThrows(UnsupportedOperationException.class, () -> fun.topologicalSort()); }

    @Test
    void stronglyConnectedComponents() { assertThrows(UnsupportedOperationException.class, () -> fun.topologicalSort()); }

    @Test
    void connectedComponents() {

    }
}