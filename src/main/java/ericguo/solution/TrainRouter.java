package ericguo.solution;


import ericguo.solution.algorigthm.Context;
import ericguo.solution.algorigthm.impl.DijkstraAlgorithm;
import ericguo.solution.analyzer.Analyzer;
import ericguo.solution.analyzer.impl.GraphAnalyzer;
import ericguo.solution.exception.NoSuchRouteException;
import ericguo.solution.graph.Graph;


/**
 * Created by eric567 [email:gyc567@126.com]
 * on 11/6/2016.
 */
public class TrainRouter {


    private Graph graph;

    private Analyzer graphAnalyzer;

    public TrainRouter( Graph graph) {
        this.graph = graph;
        this.graphAnalyzer=new GraphAnalyzer(graph);
    }


    public int pathWeight(String path) throws NoSuchRouteException {

        return graphAnalyzer.pathWeight(path);
    }


    public int pathCount(String startVertex, String endVertex, int vertexLimit) {

        return graphAnalyzer.pathCount(startVertex,endVertex,vertexLimit);
    }

    public int pathCountExact(String startVertex, String endVertex, int length) {

        return graphAnalyzer.pathCountExact(startVertex,endVertex,length);
    }

    public int pathCountWeightLimit(String startVertex, String endVertex, int weightLimit) {

        return graphAnalyzer.pathCountWeightLimit(startVertex,endVertex,weightLimit);
    }









    public int getShortestPathWeight(String startVertex, String endVertex ) throws NoSuchRouteException {
        String path = loadAlgorithmAndGetShortestPath(startVertex, endVertex );
        return pathWeight(path);
    }

    private String loadAlgorithmAndGetShortestPath(String startVertex, String endVertex ) {

        Context context =new Context(new DijkstraAlgorithm(graph));
        return context.calculate(startVertex,endVertex);
    }

}
