package upo.graph.impl;

import upo.graph.base.Edge;
import upo.graph.base.Graph;
import upo.graph.base.Vertex;
import upo.graph.base.VisitForest;

import java.util.*;

import static upo.graph.base.VisitForest.VisitType.BFS;
import static upo.graph.base.VisitForest.VisitType.DFS;
import static util.SetConversion.getSetFromArray;

/**
 * @author Alessio Cameroni - 20044488
 */
public class IndicListUndir extends GraphVertexMapping implements Graph {
    private final List<Edge> edgeList;
    
    public IndicListUndir() { edgeList = new ArrayList<>(); }

    @Override
    public int addVertex(Vertex vertex) { return addVertexIndex(vertex); }
    
    @Override
    public Set<Vertex> getVertices() { return getSetFromArray(getVertexList()); }
    
    @Override
    public Set<Edge> getEdges() { return getSetFromArray(edgeList); }
    
    @Override
    public boolean containsVertex(Vertex vertex) { return getVertexList().contains(vertex); }
    
    @Override
    public void removeVertex(Vertex vertex) throws NoSuchElementException {
        if (!getVertexList().contains(vertex)) throw new NoSuchElementException();
        addVertex(vertex);
    }
    
    @Override
    public void addEdge(Edge edge) throws IllegalArgumentException {
        if (edgeList.contains(edge)) throw new UnsupportedOperationException();
        edgeList.add(edge);
    }
    
    @Override
    public boolean containsEdge(Edge edge) throws IllegalArgumentException { return edgeList.contains(edge); }
    
    @Override
    public void removeEdge(Edge edge) throws IllegalArgumentException, NoSuchElementException {
        if (!edgeList.contains(edge)) throw new IllegalArgumentException();
        edgeList.remove(edge);
    }
    
    @Override
    public Set<Vertex> getAdjacent(Vertex vertex) throws NoSuchElementException {
        if (!getVertexList().contains(vertex)) throw new NoSuchElementException();
        
        Set<Vertex> adjacentVertices = new HashSet<>();
        for (Edge edge:getEdges()) {
            if(vertex == edge.getSource()) adjacentVertices.add(edge.getSource());
            else if (vertex == edge.getTarget()) adjacentVertices.add(edge.getTarget());
        }
        return adjacentVertices;
    }
    
    @Override
    public boolean isAdjacent(Vertex targetVertex, Vertex sourceVertex) throws IllegalArgumentException {
        if (!getVertexList().contains(sourceVertex) || !getVertexList().contains(targetVertex))
            throw new IllegalArgumentException();
        return edgeList.contains(Edge.getEdgeByVertexes(sourceVertex, targetVertex));
    }
    
    @Override
    public int size() { return getVertexList().size(); }
    
    @Override
    public boolean isDirected() { return false; }

    // TODO
    @Override
    public boolean isCyclic() {
        return false;
    }

    @Override
    public boolean isDAG() { return false; }
    
    @Override
    public VisitForest getBFSTree(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        if (!getVertexList().contains(startingVertex)) throw new IllegalArgumentException();
        
        Queue<Vertex> D = new LinkedList<>();
        VisitForest bfs = new VisitForest(this, BFS);
        
        bfs.setDistance(startingVertex, 0);
        bfs.setColor(startingVertex, VisitForest.Color.GRAY);
        D.add(startingVertex);
        
        while (!D.isEmpty()) {
            Vertex u = D.element();
            
            for (Vertex v:getAdjacent(u)) {
                if (bfs.getColor(v) == VisitForest.Color.WHITE) {
                    bfs.setColor(v, VisitForest.Color.GRAY);
                    bfs.setParent(v, u);
                    bfs.setDistance(v, bfs.getDistance(u) + 1);
                }
            }
            
            bfs.setColor(u, VisitForest.Color.BLACK);
            D.remove();
        }
        
        return bfs;
    }
    
    @Override
    public VisitForest getDFSTree(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        if (!getVertexList().contains(startingVertex)) throw new IllegalArgumentException();
        
        Stack<Vertex> D = new Stack<>();
        VisitForest dfs = new VisitForest(this, DFS);
        
        dfs.setColor(startingVertex, VisitForest.Color.GRAY);
        D.push(startingVertex);
        
        while (!D.isEmpty()) {
            Vertex u = D.peek();
            boolean foundWhite = false;
            
            for (Vertex v:getAdjacent(u)) {
                if (dfs.getColor(v) == VisitForest.Color.WHITE) {
                    dfs.setColor(v, VisitForest.Color.GRAY);
                    dfs.setParent(v, u);
                    D.push(v);
                    foundWhite = true;
                    break;
                }
            }
            
            if (foundWhite) {
                dfs.setColor(u, VisitForest.Color.BLACK);
                D.pop();
            }
        }
        
        return dfs;
    }
    
    // TODO
    @Override
    public VisitForest getDFSTOTForest(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        
        
        return null;
    }
    
    // TODO
    @Override
    public VisitForest getDFSTOTForest(Vertex[] vertexOrdering) throws UnsupportedOperationException, IllegalArgumentException {
        return null;
    }
    
    // TODO
    @Override
    public Vertex[] topologicalSort() throws UnsupportedOperationException {
        return new Vertex[0];
    }
    
    // TODO
    @Override
    public Set<Set<Vertex>> stronglyConnectedComponents() throws UnsupportedOperationException {
        return Set.of();
    }
    
    // TODO
    @Override
    public Set<Set<Vertex>> connectedComponents() throws UnsupportedOperationException {
        return Set.of();
    }
}
