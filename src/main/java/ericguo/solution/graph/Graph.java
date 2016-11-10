package ericguo.solution.graph;

import ericguo.solution.edge.Edge;
import ericguo.solution.vertex.Vertex;

import java.util.List;
import java.util.Map;

/**
 * Created by eric567 [email:gyc567@126.com]
 * on 11/4/2016.
 */
public interface Graph {

    Map<String, List<Edge>> getNeighbors();

    void initialation();

    Edge findEdge(Object from, Object to);

    Map getVertexesMap();

    List<Vertex> getVertices();

    List<Edge> getWeightedEdges();


}
