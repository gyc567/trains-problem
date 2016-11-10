package ericguo.solution;

import ericguo.solution.edge.impl.WeightedEdge;
import ericguo.solution.vertex.impl.NormalVertex;

import java.util.List;

/**
 * setup test ENV tools
 * Created by eric567 [email:gyc567@126.com]
 * on 11/6/2016.
 */
public class Setup {
    public static List addVertex(List<NormalVertex> verties, int index, String name) {
        NormalVertex location = new NormalVertex("Node_" + index, name );
        verties.add(location);
        return verties;
    }

    public static List addEdge(List<WeightedEdge> weightedEdges, List<NormalVertex> verties, String laneId, int sourceLocNo, int destLocNo,
                               int duration) {
        WeightedEdge lane = new WeightedEdge(laneId, verties.get(sourceLocNo), verties.get(destLocNo), duration );
        weightedEdges.add(lane);

        return weightedEdges;
    }
}
