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
 * Classe contenente l'implementazione dell'interfaccia Graph dedicata alle liste di incidenza.
 * La lista di incidenza e' rappresentata da una Lista di Set di Edge, strutturata nel
 * seguente modo:
 * <ul>
 *     <li>Gli indici dei Vertex all'interno della lista edgeList rispecchiano gli indici usati in incidList</li>
 *     <li>Ogni Set di Edge rappresenta gli Edge di incidenza sul proprio Vertex</li>
 * </ul>
 *
 * @author Alessio Cameroni - 20044488
 */
public class IndicListUndir extends GraphVertexMapping implements Graph {
    private final List<Set<Edge>> incidList;
    private int time;
    
    public IndicListUndir() {
        incidList = new ArrayList<>();
        time = 0;
    }

    @Override
    public int addVertex(Vertex vertex) {
        incidList.add(new HashSet<>());
        return addVertexIndex(vertex);
    }
    
    @Override
    public Set<Vertex> getVertices() { return getSetFromArray(getVertexList()); }
    
    @Override
    public Set<Edge> getEdges() {
        Set<Edge> edges = new HashSet<>();
        for(Set<Edge> vertex:incidList) { edges.addAll(vertex); }
        return edges;
    }
    
    @Override
    public boolean containsVertex(Vertex vertex) { return getVertexList().contains(vertex); }
    
    @Override
    public void removeVertex(Vertex vertex) throws NoSuchElementException {
        if (!getVertexList().contains(vertex)) throw new NoSuchElementException();

        incidList.remove(getVertexList().indexOf(vertex));
        for (Set<Edge> vertexEdges:incidList) vertexEdges.removeIf(edge -> edge.getTarget() == vertex);
        getVertexList().remove(vertex);
    }
    
    @Override
    public void addEdge(Edge edge) throws IllegalArgumentException {
        if (!getVertexList().contains(edge.getSource()) || !getVertexList().contains(edge.getTarget()))
            throw new IllegalArgumentException();

        Set<Edge> vertexSource = incidList.get(getVertexList().indexOf(edge.getSource()));
        vertexSource.add(edge);

        Set<Edge> vertexTarget = incidList.get(getVertexList().indexOf(edge.getTarget()));
        vertexTarget.add(Edge.getEdgeByVertexes(edge.getTarget(), edge.getSource()));
    }

    @Override
    public boolean containsEdge(Edge edge) throws IllegalArgumentException {
        if (!getVertexList().contains(edge.getSource()) || !getVertexList().contains(edge.getTarget()))
            throw new IllegalArgumentException();

        return incidList
                .get(getVertexList().indexOf(edge.getSource()))
                .contains(edge);
    }
    
    @Override
    public void removeEdge(Edge edge) throws IllegalArgumentException, NoSuchElementException {
        if (!getVertexList().contains(edge.getSource()) || !getVertexList().contains(edge.getTarget()))
            throw new IllegalArgumentException();

        boolean vertexFound = false;

        for (Set<Edge> vertexEdges:incidList) {
            if (vertexEdges.contains(edge)) {
                vertexEdges.remove(edge);
                vertexFound = true;
            } else vertexEdges.remove(Edge.getEdgeByVertexes(edge.getTarget(), edge.getSource()));
        }

        if (!vertexFound) throw new NoSuchElementException();
    }
    
    @Override
    public Set<Vertex> getAdjacent(Vertex vertex) throws NoSuchElementException {
        if (!getVertexList().contains(vertex)) throw new NoSuchElementException();
        
        Set<Vertex> adjacentVertices = new HashSet<>();
        for (Edge edge:incidList.get(getVertexList().indexOf(vertex))) adjacentVertices.add(edge.getTarget());
        return adjacentVertices;
    }
    
    @Override
    public boolean isAdjacent(Vertex targetVertex, Vertex sourceVertex) throws IllegalArgumentException {
        if (!getVertexList().contains(sourceVertex) || !getVertexList().contains(targetVertex))
            throw new IllegalArgumentException();

        return incidList
                .get(getVertexList().indexOf(sourceVertex))
                .contains(Edge.getEdgeByVertexes(sourceVertex, targetVertex));
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
     * Metodo ricorsivo che si occupa di controllare
     * che il grafo sia ciclico.
     *
     * @param cyc       L'albero di visita che rappresenta la (A)ciclita
     * @param u         Nodo iniziale di ogni ricorsione
     * @return          Booleano in base alla condizione
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
                    D.add(v);
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
            boolean hasWhiteAdjacent = false;

            for (Vertex v:getAdjacent(u)) {
                if (dfs.getColor(v) == WHITE) {
                    dfs.setColor(v, GRAY);
                    dfs.setParent(v, u);
                    D.push(v);
                    hasWhiteAdjacent = true;
                    break;
                }
            }

            if (!hasWhiteAdjacent) {
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
        if (vertexOrdering == null) throw new IllegalArgumentException();
        for (Vertex v:vertexOrdering)
            if (!getVertexList().contains(v)) throw new IllegalArgumentException();

        VisitForest dfs = new VisitForest(this, DFS_TOT);

        for (Vertex u:vertexOrdering) {
            if (!getVertexList().contains(u)) throw new IllegalArgumentException();
            if (dfs.getColor(u) == WHITE) dfs = visitDFSTOTForest(dfs, u);
        }

        return dfs;
    }

    /**
     * Metodo che implementa la visita DFS totale
     * in modo ricorsivo.
     *
     * @param dfs       L'albero di visita
     * @param u         Nodo iniziale di ogni ricorsione
     * @return          Lo stato dell'albero della ricorsione
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

    @Override
    public Set<Set<Vertex>> connectedComponents() throws UnsupportedOperationException {
        Set<Set<Vertex>> cc = new HashSet<>();
        VisitForest bfs = new VisitForest(this, BFS);

        for (Vertex u:getVertexList()) {
            if (bfs.getColor(u) == WHITE) {
                bfs = getBFSTree(u);
                Set<Vertex> component = new HashSet<>();

                for (Vertex v:getVertexList())
                    if (bfs.getColor(v) == BLACK) component.add(v);

                cc.add(component);
            }
        }

        return cc;
    }
}
