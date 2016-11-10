package ericguo.solution.edge.impl;

import ericguo.solution.edge.Edge;
import ericguo.solution.vertex.impl.NormalVertex;

/**
 * Created by eric567 [email:gyc567@126.com]
 * on 11/5/2016.
 */
public class WeightedEdge implements Edge {
    private final String id;
    private final NormalVertex source;
    private final NormalVertex destination;
    private final int weight;

    public WeightedEdge(String id, NormalVertex source, NormalVertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public NormalVertex getDestination() {
        return destination;
    }

    public NormalVertex getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}
