package util;

public class WeightedEdge<Edge, Double> {
    private final Edge edge;
    private Double weight;

    public WeightedEdge(Edge edge, Double weight) {
        this.edge = edge;
        this.weight = weight;
    }

    public Edge getEdge() { return edge; }

    public Double getWeight() { return weight; }

    public void setWeight(Double weight) { this.weight = weight; }
}
