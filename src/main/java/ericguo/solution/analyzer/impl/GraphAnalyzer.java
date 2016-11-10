package ericguo.solution.analyzer.impl;

import ericguo.solution.analyzer.Analyzer;
import ericguo.solution.edge.Edge;
import ericguo.solution.exception.NoSuchRouteException;
import ericguo.solution.graph.Graph;
import ericguo.solution.vertex.Vertex;
import ericguo.solution.vertex.impl.NormalVertex;

import java.util.LinkedList;
import java.util.List;

/**
 * concrete biz class
 * Created by eric567 [email:gyc567@126.com]
 * on 11/6/2016.
 */
public class GraphAnalyzer implements Analyzer {

    private Graph graph;
    private int count;

    public GraphAnalyzer(Graph graph) {
        this.graph = graph;
    }

    public int pathWeight(String path) throws NoSuchRouteException {
        String[] stops = path.split("-");
        int pathWeight = 0;
        for (int i = 0; i < stops.length - 1; i++) {

            Edge currentNormalEdge = graph.findEdge(stops[i], stops[i + 1]);
            if (currentNormalEdge != null) {
                pathWeight = pathWeight + currentNormalEdge.getWeight();
            } else {
                throw new NoSuchRouteException("NO SUCH ROUTE");
            }
        }
        return pathWeight;
    }

    private void resetCount() {
        this.count = 0;
    }

    public int pathCount(String startVertex, String endVertex, int vertexLimit) {
        resetCount();
        Vertex startV = getVertex(startVertex);
        Vertex endV = getVertex(endVertex);
        dfs(startV, 0, endV, vertexLimit);
        return this.count;
    }

    public int pathCountExact(String startVertex, String endVertex, int length) {
        resetCount();
        Vertex startV = getVertex(startVertex);
        Vertex endV = getVertex(endVertex);
        dfsExact(startV, 1, endV, length);
        return this.count;
    }

    public int pathCountWeightLimit(String startVertex, String endVertex, int weightLimit) {
        resetCount();
        Vertex startV = getVertex(startVertex);
        Vertex endV = getVertex(endVertex);
        dfsExactWeight(startV, endV, weightLimit, 0);
        return this.count;
    }







    private void dfsExact(Vertex normalVertexIndex, int depth, Vertex search, int length) {
        if (depth == length) {
            return;
        }
        LinkedList<List< Edge>> queue = new LinkedList<List< Edge>>();
        queue.add(graph.getNeighbors().get(normalVertexIndex.getName()));
        while (!queue.isEmpty()) {
            for ( Edge normalEdge : queue.poll()) {
                if (containsVertex(normalEdge, search) && (depth == length)) {
                    this.count++;
                }
                dfs(normalEdge.getDestination(), depth + 1, search, length);
            }
        }
    }

    public void dfsExactWeight(Vertex normalVertexIndex, Vertex search, int weightLimit, int pathWeight) {
        if (pathWeight >= weightLimit) {
            return;
        }
        LinkedList<List< Edge>> queue = new LinkedList<List< Edge>>();
        queue.add(graph.getNeighbors().get(normalVertexIndex.getName()));
        while (!queue.isEmpty()) {
            for ( Edge normalEdge : queue.poll()) {
                int currentWeight = pathWeight + normalEdge.getWeight();
                if (containsVertex(normalEdge, search) && (currentWeight < weightLimit)) {
                    this.count++;
                }
                dfsExactWeight(normalEdge.getDestination(), search, weightLimit, currentWeight);
            }
        }
    }

    private Vertex getVertex(String startVertex) {
        Vertex startV = new NormalVertex("vertex"+startVertex,startVertex);

        return startV;
    }

    //depth-first search
    private void dfs(Vertex normalVertexIndex, int depth, Vertex search, int limit) {
        if (depth == limit) {
            return;
        }
        LinkedList<List< Edge>> queue = new LinkedList<List< Edge>>();
        queue.add(graph.getNeighbors().get(normalVertexIndex.getName()));
        while (!queue.isEmpty()) {
            for ( Edge normalEdge : queue.poll()) {
                if (containsVertex(normalEdge, search)) {
                    this.count++;
                }
                dfs(normalEdge.getDestination(), depth + 1, search, limit);
            }
        }
    }

    //determine the  Edge include the vertex
    private boolean containsVertex( Edge normalEdge, Vertex normalVertexIndex) {
        return normalEdge.getDestination().getName().equals(normalVertexIndex.getName());
    }

}
