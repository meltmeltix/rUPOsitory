package upo.graph.impl;

import upo.graph.base.Edge;
import upo.graph.base.Graph;
import upo.graph.base.Vertex;
import upo.graph.base.VisitForest;

import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author Alessio Cameroni - 20044488
 */
public class IndicListUndir implements Graph {
    @Override
    public int addVertex(Vertex vertex) {
        return 0;
    }
    
    @Override
    public Set<Vertex> getVertices() {
        return Set.of();
    }
    
    @Override
    public Set<Edge> getEdges() {
        return Set.of();
    }
    
    @Override
    public boolean containsVertex(Vertex vertex) {
        return false;
    }
    
    @Override
    public void removeVertex(Vertex vertex) throws NoSuchElementException {
    
    }
    
    @Override
    public void addEdge(Edge edge) throws IllegalArgumentException {
    
    }
    
    @Override
    public boolean containsEdge(Edge edge) throws IllegalArgumentException {
        return false;
    }
    
    @Override
    public void removeEdge(Edge edge) throws IllegalArgumentException, NoSuchElementException {
    
    }
    
    @Override
    public Set<Vertex> getAdjacent(Vertex vertex) throws NoSuchElementException {
        return Set.of();
    }
    
    @Override
    public boolean isAdjacent(Vertex targetVertex, Vertex sourceVertex) throws IllegalArgumentException {
        return false;
    }
    
    @Override
    public int size() {
        return 0;
    }
    
    @Override
    public boolean isDirected() {
        return false;
    }
    
    @Override
    public boolean isCyclic() {
        return false;
    }
    
    @Override
    public boolean isDAG() {
        return false;
    }
    
    @Override
    public VisitForest getBFSTree(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        return null;
    }
    
    @Override
    public VisitForest getDFSTree(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        return null;
    }
    
    @Override
    public VisitForest getDFSTOTForest(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        return null;
    }
    
    @Override
    public VisitForest getDFSTOTForest(Vertex[] vertexOrdering) throws UnsupportedOperationException, IllegalArgumentException {
        return null;
    }
    
    @Override
    public Vertex[] topologicalSort() throws UnsupportedOperationException {
        return new Vertex[0];
    }
    
    @Override
    public Set<Set<Vertex>> stronglyConnectedComponents() throws UnsupportedOperationException {
        return Set.of();
    }
    
    @Override
    public Set<Set<Vertex>> connectedComponents() throws UnsupportedOperationException {
        return Set.of();
    }
}