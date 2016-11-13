package ericguo.solution.algorigthm.impl;

import ericguo.solution.algorigthm.Algorithm;
import ericguo.solution.edge.Edge;
import ericguo.solution.graph.Graph;
import ericguo.solution.vertex.Vertex;

import java.util.*;

/**
 * Created by eric567 [email:gyc567@126.com]
 * on 11/5/2016.
 */
public class DijkstraAlgorithm implements Algorithm {

    private Graph graph;
    private final List<Vertex> nodes;
    private final List<Edge> weightedEdges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array from graph
        this.graph = graph;
        this.nodes = new ArrayList<Vertex>(graph.getVertices());
        this.weightedEdges = new ArrayList<Edge>(graph.getWeightedEdges());
    }

    public void loadAndFindPredecessors(Vertex source, boolean isSameNode) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node, isSameNode, source);
        }
    }

    private void findMinimalDistances(Vertex node, boolean isSameNode, Vertex source) {
        List<Vertex> adjacentNodes = getNeighbors(node, isSameNode);
        for (Vertex target : adjacentNodes) {
            //the distance of node to target.
            int distance = getDistance(node, target);
            //the shortest distance from source to target
            int distanceFromSourceToTarget = getShortestDistance(node)
                    + distance;
            // find the adjacent node of current node  is source node
            if (isSameNode && target.getName().equals(source.getName())) {

                this.distance.put(target, distanceFromSourceToTarget);
                predecessors.put(target, node);
                //when the target is source,clear unsettled nodes and return
                unSettledNodes.clear();
                return;
            }
            //the adjacentNodes of node didn't contain the source node
            if (!adjacentNodes.contains(source)) {

                //the distance of source to target is smaller than the shortest distance in cache
                if (getShortestDistance(target) > distanceFromSourceToTarget) {
                    this.distance.put(target, distanceFromSourceToTarget);
                    predecessors.put(target, node);
                    unSettledNodes.add(target);
                }
            }
        }

    }

    //get the distance from vertex source to vertex target
    private int getDistance(Vertex source, Vertex target) {
        for (Edge weightedEdge : weightedEdges) {
            if (weightedEdge.getSource().equals(source)
                    && weightedEdge.getDestination().equals(target)) {
                return weightedEdge.getWeight();
            }
        }
        //if there no edge from vertex source to vertex target,the distance shall be infinite far
        return Integer.MAX_VALUE;
    }

    //get the adjacent list of unsettled vertex
    private List<Vertex> getNeighbors(Vertex vertex, boolean isSameNode) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge weightedEdge : weightedEdges) {
            if (weightedEdge.getSource().equals(vertex)
                    ) {
                //if the source vertex and target vertex is the same vertex
                if (isSameNode) {
                    neighbors.add(weightedEdge.getDestination());
                } else if (!isSettled(weightedEdge.getDestination())) {
                    neighbors.add(weightedEdge.getDestination());
                }
            }
        }
        return neighbors;
    }

    // find the vertex of  minimum distance in vertexes set
    private Vertex getMinimum(Set<Vertex> normalVertices) {
        Vertex minimum = null;
        for (Vertex normalVertex : normalVertices) {
            if (minimum == null) {
                minimum = normalVertex;
            } else {
                if (getShortestDistance(normalVertex) < getShortestDistance(minimum)) {
                    minimum = normalVertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Vertex normalVertex) {

        return settledNodes.contains(normalVertex);
    }

    //get distance value from cache
    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the shortest path from the source to the  target
     */
    public LinkedList<Vertex> getShortestPath(Vertex target, Vertex source) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null && predecessors.get(step).getName() != source.getName()) {
            step = predecessors.get(step);
            path.add(step);
        }
        // reverse the order
        Collections.reverse(path);
        return path;
    }

    //get the shortest path from vertex a to vertex b
    public String calculate(String startVertex, String endVertex) {
        Map<String, Vertex> vertexesMap = graph.getVertexesMap();
        loadAndFindPredecessors(vertexesMap.get(startVertex), endVertex.equals(startVertex));
        LinkedList<Vertex> paths = getShortestPath(vertexesMap.get(endVertex), vertexesMap.get(startVertex));

        return buildPathString(paths, vertexesMap.get(startVertex));
    }

    //build the path of resulting vertexes
    public String buildPathString(LinkedList<Vertex> paths, Vertex source) {
        //add the source vertex into path
        StringBuffer path = new StringBuffer(source.getName() + "-");
        for (int i = 0; i < paths.size(); i++) {
            path.append(paths.get(i));
            if (i < paths.size() - 1) {
                path.append("-");
            }
        }
        return path.toString();
    }
}