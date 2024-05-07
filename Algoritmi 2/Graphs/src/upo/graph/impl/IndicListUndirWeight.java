package upo.graph.impl;

import upo.graph.base.Edge;
import upo.graph.base.Vertex;
import upo.graph.base.VisitForest;
import upo.graph.base.WeightedGraph;
import util.WeightedEdge;

import java.util.*;

import static upo.graph.base.VisitForest.Color.*;
import static upo.graph.base.VisitForest.VisitType.*;
import static upo.graph.base.VisitForest.VisitType.DFS_TOT;

/**
 * Classe contenente l'implementazione dell'interfaccia Graph dedicata alle liste di incidenza.
 * La lista di incidenza e' rappresentata da una Lista di Set di WeightedEdge, i quali
 * consistono nel raccogliere la coppia Edge-Weight rispettivamente.
 * La lista è strutturata nel seguente modo:
 * <ul>
 *     <li>Gli indici dei Vertex all'interno della lista edgeList rispecchiano gli indici usati in incidList</li>
 *     <li>Ogni Set di WeightedEdge rappresenta gli Edge di incidenza sul proprio Vertex</li>
 * </ul>
 *
 * @author Alessio Cameroni - 20044488
 */
public class IndicListUndirWeight extends IndicListUndir implements WeightedGraph {
    private final List<Set<WeightedEdge<Edge, Double>>> incidList;
    private int time;

    public IndicListUndirWeight() {
        incidList = new ArrayList<>();
        time = 0;
    }

    @Override
    public double getEdgeWeight(Edge edge) throws IllegalArgumentException, NoSuchElementException {
        if (!getVertexList().contains(edge.getSource()) || !getVertexList().contains(edge.getTarget()))
            throw new IllegalArgumentException();

        boolean found = false;
        double wght = 0.0;

        for (WeightedEdge<Edge, Double> e:incidList.get(getVertexList().indexOf(edge.getSource()))) {
            if (e.getEdge().getTarget() == edge.getTarget()) {
                found = true;
                wght = e.getWeight();
                break;
            }
        }

        if (!found) throw new NoSuchElementException();
        return wght;
    }
    
    @Override
    public void setEdgeWeight(Edge edge, double weight) throws IllegalArgumentException, NoSuchElementException {
        if (!getVertexList().contains(edge.getSource()) || !getVertexList().contains(edge.getTarget()))
            throw new IllegalArgumentException();

        boolean found = false;

        for (WeightedEdge<Edge, Double> e:incidList.get(getVertexList().indexOf(edge.getSource()))) {
            if (e.getEdge().getTarget() == edge.getTarget()) {
                found = true;
                e.setWeight(weight);
                break;
            }
        }

        if (!found) throw new NoSuchElementException();

        for (WeightedEdge<Edge, Double> e:incidList.get(getVertexList().indexOf(edge.getTarget()))) {
            if (e.getEdge().getSource() == edge.getSource()) {
                e.setWeight(weight);
                break;
            }
        }
    }
    
    @Override
    public WeightedGraph getBellmanFordShortestPaths(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public WeightedGraph getDijkstraShortestPaths(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public WeightedGraph getPrimMST(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public WeightedGraph getKruskalMST() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public WeightedGraph getFloydWarshallShortestPaths() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int addVertex(Vertex vertex) {
        incidList.add(new HashSet<>());
        return addVertexIndex(vertex);
    }
    
    @Override
    public Set<Edge> getEdges() {
        Set<Edge> edges = new HashSet<>();
        for (Set<WeightedEdge<Edge, Double>> vertexEdges:incidList)
            for (WeightedEdge<Edge, Double> edge:vertexEdges) edges.add(edge.getEdge());
        return edges;
    }

    @Override
    public void removeVertex(Vertex vertex) throws NoSuchElementException {
        if (!getVertexList().contains(vertex)) throw new NoSuchElementException();

        incidList.remove(getVertexList().indexOf(vertex));
        for (Set<WeightedEdge<Edge, Double>> vertexEdges:incidList)
            vertexEdges.removeIf(edge -> edge.getEdge().getTarget() == vertex);
        getVertexList().remove(vertex);
    }
    
    @Override
    public void addEdge(Edge edge) throws IllegalArgumentException {
        if (!getVertexList().contains(edge.getSource()) || !getVertexList().contains(edge.getTarget()))
            throw new IllegalArgumentException();

        Set<WeightedEdge<Edge, Double>> vertexSource = incidList.get(getVertexList().indexOf(edge.getSource()));
        vertexSource.add(new WeightedEdge<>(edge, 0.0));

        Set<WeightedEdge<Edge, Double>> vertexTarget = incidList.get(getVertexList().indexOf(edge.getTarget()));
        vertexTarget.add(
            new WeightedEdge<>(
                Edge.getEdgeByVertexes(edge.getTarget(), edge.getSource()),
                0.0
            )
        );
    }

    @Override
    public boolean containsEdge(Edge edge) throws IllegalArgumentException {
        if (!getVertexList().contains(edge.getSource()) || !getVertexList().contains(edge.getTarget()))
            throw new IllegalArgumentException();

        for (WeightedEdge<Edge, Double> vertexEdges:incidList.get(getVertexList().indexOf(edge.getSource())))
            if (vertexEdges.getEdge() == edge) return true;
        return false;
    }
    
    @Override
    public void removeEdge(Edge edge) throws IllegalArgumentException, NoSuchElementException {
        if (!getVertexList().contains(edge.getSource()) || !getVertexList().contains(edge.getTarget()))
            throw new IllegalArgumentException();

        boolean found = false;

        for (Set<WeightedEdge<Edge, Double>> vertexEdges:incidList) {
            if(vertexEdges.removeIf(listEdge -> listEdge.getEdge() == edge)) found = true;
            if(vertexEdges.removeIf(listEdge -> listEdge.getEdge() == Edge.getEdgeByVertexes(edge.getTarget(), edge.getSource())))
                found = true;
        }

        if (!found) throw new NoSuchElementException();
    }
    
    @Override
    public Set<Vertex> getAdjacent(Vertex vertex) throws NoSuchElementException {
        if (!getVertexList().contains(vertex)) throw new NoSuchElementException();

        Set<Vertex> adjacentVertices = new HashSet<>();
        for (WeightedEdge<Edge, Double> vertexEdge:incidList.get(getVertexList().indexOf(vertex)))
            adjacentVertices.add(vertexEdge.getEdge().getTarget());
        return adjacentVertices;
    }
    
    @Override
    public boolean isAdjacent(Vertex targetVertex, Vertex sourceVertex) throws IllegalArgumentException {
        if (!getVertexList().contains(sourceVertex) || !getVertexList().contains(targetVertex))
            throw new IllegalArgumentException();

        for (WeightedEdge<Edge, Double> vertexEdges:incidList.get(getVertexList().indexOf(sourceVertex)))
            if (vertexEdges.getEdge().getSource() == sourceVertex && vertexEdges.getEdge().getTarget() == targetVertex)
                return true;
        return false;
    }
    
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
    public VisitForest getBFSTree(Vertex startingVertex) throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException();
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
    public Set<Set<Vertex>> connectedComponents() throws UnsupportedOperationException {
        Set<Set<Vertex>> cc = new HashSet<>();
        VisitForest dfs = new VisitForest(this, DFS);
        
        for (Vertex u:getVertexList()) {
            if (dfs.getColor(u) == WHITE) {
                dfs = getDFSTree(u);
                Set<Vertex> component = new HashSet<>();
                
                for (Vertex v:getVertexList())
                    if (dfs.getColor(v) == BLACK) component.add(v);
                
                cc.add(component);
            }
        }
        
        return cc;
    }
}
