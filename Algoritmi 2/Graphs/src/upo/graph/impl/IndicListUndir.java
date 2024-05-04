package upo.graph.impl;

import upo.graph.base.Edge;
import upo.graph.base.Graph;
import upo.graph.base.Vertex;
import upo.graph.base.VisitForest;

import java.util.*;

import static upo.graph.base.VisitForest.VisitType.*;
import static upo.graph.base.VisitForest.Color.*;
import static util.SetConversion.getSetFromArray;

/**
 * @author Alessio Cameroni - 20044488
 */
public class IndicListUndir extends GraphVertexMapping implements Graph {
    private final List<Edge> edgeList;
    private int time;
    
    public IndicListUndir() {
        edgeList = new ArrayList<>();
        time = 0;
    }

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
        getVertexList().remove(vertex);
    }
    
    @Override
    public void addEdge(Edge edge) throws IllegalArgumentException {
        if (!getVertexList().contains(edge.getSource()) || !getVertexList().contains(edge.getTarget()))
            throw new IllegalArgumentException();
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

    @Override
    public boolean isCyclic() {
        VisitForest cyc = new VisitForest(this, OTHER);

        for (Vertex u:getVertexList())
            if (cyc.getColor(u) == WHITE && visitTreeCycle(cyc, u))
                return true;
        return false;
    }

    /**
     * Recursive method used to visit the tree
     * and check whether the tree is indeed cyclic
     *
     * @param cyc       VisitForest object holding the Graph information
     * @param u         The currently investigated node, updates on recursion
     * @return          Boolean
     */
    private boolean visitTreeCycle(VisitForest cyc, Vertex u) {
        cyc.setColor(u, GRAY);

        for (Vertex v:getAdjacent(u)) {
            if (cyc.getColor(v) == WHITE) {
                cyc.setParent(v, u);
                if (visitTreeCycle(cyc, v)) return true;
            } else if (!v.equals(cyc.getPartent(u))) return true;
        }

        cyc.setColor(u, BLACK);
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
        bfs.setColor(startingVertex, GRAY);
        D.add(startingVertex);
        
        while (!D.isEmpty()) {
            Vertex u = D.element();
            
            for (Vertex v:getAdjacent(u)) {
                if (bfs.getColor(v) == WHITE) {
                    bfs.setColor(v, GRAY);
                    bfs.setParent(v, u);
                    bfs.setDistance(v, bfs.getDistance(u) + 1);
                }
            }
            
            bfs.setColor(u, BLACK);
            D.remove();
        }
        
        return bfs;
    }
    
    @Override
    public VisitForest getDFSTree(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        if (!getVertexList().contains(startingVertex)) throw new IllegalArgumentException();
        
        Stack<Vertex> D = new Stack<>();
        VisitForest dfs = new VisitForest(this, DFS);
        
        dfs.setColor(startingVertex, GRAY);
        D.push(startingVertex);
        
        while (!D.isEmpty()) {
            Vertex u = D.peek();
            boolean foundWhite = false;
            
            for (Vertex v:getAdjacent(u)) {
                if (dfs.getColor(v) == WHITE) {
                    dfs.setColor(v, GRAY);
                    dfs.setParent(v, u);
                    D.push(v);
                    foundWhite = true;
                    break;
                }
            }
            
            if (foundWhite) {
                dfs.setColor(u, BLACK);
                D.pop();
            }
        }
        
        return dfs;
    }

    @Override
    public VisitForest getDFSTOTForest(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        if (!getVertexList().contains(startingVertex)) throw new IllegalArgumentException();
        VisitForest dfs = new VisitForest(this, DFS_TOT);
        return visitDFSTOTForest(dfs, startingVertex);
    }

    @Override
    public VisitForest getDFSTOTForest(Vertex[] vertexOrdering) throws UnsupportedOperationException, IllegalArgumentException {
        if (vertexOrdering == null || vertexOrdering.length != getVertexList().size()) throw new IllegalArgumentException();

        VisitForest dfs = new VisitForest(this, DFS_TOT);
        for (Vertex v:vertexOrdering) {
            if (!getVertexList().contains(v)) throw new IllegalArgumentException();
            dfs = visitDFSTOTForest(dfs, v);
        }

        return dfs;
    }

    /**
     * Method containing the main DFS algorithm
     *
     * @param dfs       VisitForest object holding the Graph information
     * @param u         The currently investigated node
     * @return          dfs after visiting the forest
     */
    private VisitForest visitDFSTOTForest(VisitForest dfs, Vertex u) {
        dfs.setColor(u, GRAY);
        dfs.setStartTime(u, time);
        time += 1;

        for (Vertex v:getAdjacent(u)) {
            if (dfs.getColor(v) == WHITE) {
                dfs.setParent(v, u);
                dfs = visitDFSTOTForest(dfs, v);
            }
        }

        dfs.setColor(u, BLACK);
        dfs.setEndTime(u, time);
        time += 1;

        return dfs;
    }

    @Override
    public Vertex[] topologicalSort() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Set<Vertex>> stronglyConnectedComponents() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    // TODO
    @Override
    public Set<Set<Vertex>> connectedComponents() throws UnsupportedOperationException {
        Set<Set<Vertex>> cc = new HashSet<>();
        VisitForest visit = new VisitForest(this, OTHER);



        return cc;
    }
}
