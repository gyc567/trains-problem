package ericguo.solution.edge;

import ericguo.solution.vertex.Vertex;

/**
 * Created by eric567 [email:gyc567@126.com]
 * on 11/6/2016.
 */
public interface Edge {
    String getId();

    Vertex getDestination();

    Vertex getSource();

    int getWeight();
}
