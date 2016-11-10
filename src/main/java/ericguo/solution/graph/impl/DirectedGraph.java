package ericguo.solution.graph.impl;


import ericguo.solution.edge.Edge;
import ericguo.solution.graph.Graph;
import ericguo.solution.vertex.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eric567 [email:gyc567@126.com]
 * on 11/5/2016.
 */
public class DirectedGraph implements Graph {
    private final List<Vertex> normalVertices;
    private final List<Edge> weightedEdges;
    private final Map<String, Vertex> vertexesMap = new HashMap();

    public Map getVertexesMap() {
        return vertexesMap;
    }

    /**
     * A Map is used to map each vertex to its list of adjacent edge.
     */

    private Map<String,List<Edge>> neighbors = new HashMap<String, List<Edge>>();


    public Map<String, List<Edge>> getNeighbors() {
        return neighbors;
    }

    public DirectedGraph(List<Vertex> normalVertices, List<Edge> weightedEdges) {
        this.normalVertices = normalVertices;
        this.weightedEdges = weightedEdges;
    }

    public List<Vertex> getVertices() {
        return normalVertices;
    }

    public List<Edge> getWeightedEdges() {
        return weightedEdges;
    }


    public Edge findEdge(Object from, Object to) {
        Edge result = null;
        for (Edge e : weightedEdges) {
            if (e.getSource().getName().equals(from) && e.getDestination().getName().equals(to)) {
                result = e;
                break;
            }
        }
        return result;
    }


    //initial the normalVertices map and adjacent edge list
    public void initialation() {

        neighbors.clear();
        vertexesMap.clear();
        for (int i = 0; i < normalVertices.size(); i++) {
            vertexesMap.put(normalVertices.get(i).getName(), normalVertices.get(i));
        }
        for (Edge e : weightedEdges) {
            if (!neighbors.containsKey(e.getSource().getName())) {
                List vertexes = new ArrayList();
                vertexes.add(e);
                neighbors.put(e.getSource().getName(), vertexes);
            } else {
                neighbors.get(e.getSource().getName()).add((e));
            }
        }
    }

}
