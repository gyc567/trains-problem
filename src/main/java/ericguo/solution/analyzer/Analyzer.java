package ericguo.solution.analyzer;

import ericguo.solution.exception.NoSuchRouteException;

/**
 * Created by eric567 [email:gyc567@126.com]
 * on 11/6/2016.
 */
public interface Analyzer {
    //get the total weight of specific path
    int pathWeight(String path) throws NoSuchRouteException;
    //get the count of paths from vertex a to vertex b with the limited vertex number
    int pathCount(String startVertex, String endVertex, int vertexLimit);
    //get the exact count of paths for vertex a to vertex b with specific length
    int pathCountExact(String startVertex, String endVertex, int length);
    //get the count of paths with weight limit
    int pathCountWeightLimit(String startVertex, String endVertex, int weightLimit);
}
