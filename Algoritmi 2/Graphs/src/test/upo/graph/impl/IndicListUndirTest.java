package upo.graph.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndicListUndirTest {
    IndicListUndir list;

    @BeforeEach
    void init() {
        list = new IndicListUndir();
    }

    @Test
    void addVertex() {
    }

    @Test
    void getVertices() {
    }

    @Test
    void getEdges() {
    }

    @Test
    void containsVertex() {
    }

    @Test
    void removeVertex() {
    }

    @Test
    void addEdge() {
    }

    @Test
    void containsEdge() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void getAdjacent() {
    }

    @Test
    void isAdjacent() {
    }

    @Test
    void size() {
    }

    @Test
    void isDirected() { assertFalse(list.isDirected()); }

    @Test
    void isCyclic() {
    }

    @Test
    void isDAG() { assertFalse(list.isDAG()); }

    @Test
    void getBFSTree() {
    }

    @Test
    void getDFSTree() {
    }

    @Test
    void getDFSTOTForest() {
    }

    @Test
    void getDFSTOTForestOrdered() {
    }

    @Test
    void topologicalSort() { assertThrows(UnsupportedOperationException.class, () -> list.topologicalSort()); }

    @Test
    void stronglyConnectedComponents() { assertThrows(UnsupportedOperationException.class, () -> list.topologicalSort()); }

    @Test
    void connectedComponents() {

    }
}