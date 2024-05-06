package upo.graph.impl;

import upo.graph.base.Edge;
import upo.graph.base.Vertex;
import upo.graph.base.VisitForest;
import upo.graph.base.WeightedGraph;
import util.WeightedEdge;

import java.util.*;

/**
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
        return Set.of();
    }
    
    @Override
    public boolean isAdjacent(Vertex targetVertex, Vertex sourceVertex) throws IllegalArgumentException {
        return false;
    }
    
    @Override
    public boolean isCyclic() {
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
    public Set<Set<Vertex>> connectedComponents() throws UnsupportedOperationException {
        return Set.of();
    }
}
