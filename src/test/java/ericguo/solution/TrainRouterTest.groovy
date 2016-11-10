package ericguo.solution

import ericguo.solution.edge.impl.WeightedEdge
import ericguo.solution.exception.NoSuchRouteException
import ericguo.solution.graph.impl.DirectedGraph
import ericguo.solution.vertex.impl.NormalVertex
import spock.lang.Specification

/**
 * BDD test case for all the feature
 * Created by eric567 [email:gyc567@126.com] 
 * on 11/6/2016.
 */
class TrainRouterTest extends Specification {
    TrainRouter trainRouter = null;

    void setup() {
        List verties = new ArrayList<NormalVertex>();
        List weightedEdges = new ArrayList<WeightedEdge>();
        //Directed Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7

        Setup.addVertex(verties, 0, "A");
        Setup.addVertex(verties, 1, "B");
        Setup.addVertex(verties, 2, "C");
        Setup.addVertex(verties, 3, "D");
        Setup.addVertex(verties, 4, "E");


        Setup.addEdge(weightedEdges, verties, "Edge_0", 0, 1, 5);
        Setup.addEdge(weightedEdges, verties, "Edge_1", 1, 2, 4);
        Setup.addEdge(weightedEdges, verties, "Edge_2", 2, 3, 8);
        Setup.addEdge(weightedEdges, verties, "Edge_3", 3, 2, 8);
        Setup.addEdge(weightedEdges, verties, "Edge_4", 3, 4, 6);
        Setup.addEdge(weightedEdges, verties, "Edge_5", 0, 3, 5);
        Setup.addEdge(weightedEdges, verties, "Edge_6", 2, 4, 2);
        Setup.addEdge(weightedEdges, verties, "Edge_7", 4, 1, 3);
        Setup.addEdge(weightedEdges, verties, "Edge_8", 0, 4, 7);


        DirectedGraph graph = new DirectedGraph(verties, weightedEdges);
        graph.initialation();


        trainRouter = new TrainRouter(graph)

    }

    void cleanup() {

        trainRouter = null;
    }

    /*
     *scenario:
     *The distance of the route A-B-C shall be 9
     *The distance of the route A-D shall be 5
     *The distance of the route A-D-C shall be 13
     *The distance of the route A-E-B-C-D shall be 22
     */

    def "test PathWeight,given #path shall return #pathWeight in the graph"() {

        expect:
        pathWeight == trainRouter.pathWeight(path)
        where:
        pathWeight | path
        9          | "A-B-C"
        5          | "A-D"
        13         | "A-D-C"
        22         | "A-E-B-C-D"
        9          | "B-C-E-B"


    }

    /*
     * scenario:
     *The distance of the route A-E-D shall throw NO SUCH ROUTE Exception
    */

    def "test PathWeight with NO SUCH ROUTE Exception"() {
        when:
        trainRouter.pathWeight("A-E-D")
        then:
        thrown(NoSuchRouteException)


    }

    /*
     *scenario:
     *The number of trips starting at C and ending at C with a maximum of 3 stops shall be 2.
     *In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
     */

    def "test PathCount,given #startV,#endV,#stopsLimit shall return #pathCount in the graph"() {

        expect:
        pathCount == trainRouter.pathCount(startV, endV, stopsLimit)
        where:
        pathCount | startV | endV | stopsLimit
        2         | "C"    | "C"  | 3


    }

    /*
     *scenario:
     *The number of trips starting at A and ending at C with exactly 4 stops shall be 3
     *In the sample data below, there are three such trips:
     * A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
     */

    def "test PathCountExact,given #startV,#endV,#length shall return #pathCount in the graph"() {

        expect:
        pathCount == trainRouter.pathCountExact(startV, endV, length)
        where:
        pathCount | startV | endV | length
        3         | "A"    | "C"  | 4


    }

    /*
     *scenario:
     *The number of different routes from C to C with a distance of less than 30.
     *The number shall be 7
     *In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
     */

    def "test pathCountWeightLimit,given #startV,#endV,#weightLimit shall return #pathCount in the graph"() {

        expect:
        pathCount == trainRouter.pathCountWeightLimit(startV, endV, weightLimit)
        where:
        pathCount | startV | endV | weightLimit
        7         | "C"    | "C"  | 30


    }

    /*
   scenario:
   The length of the shortest route (in terms of distance to travel) from A to C shall be 9

    */

    def "The length of the shortest route (in terms of distance to travel) from #startV to #endV shall be #shortestPathWeight"() {


        expect:
        shortestPathWeight == trainRouter.getShortestPathWeight(startV, endV)
        where:
        shortestPathWeight | startV | endV
        9                  | "A"    | "C"
        7                  | "A"    | "E"
        6                  | "B"    | "E"


    }
    /*
   scenario:
  The length of the shortest route (in terms of distance to travel) from B to B shall be 9

    */

    def "The length of the shortest route (in terms of distance to travel) from #startV to #endV shall be #shortestPathWeight(on station thought other stations and back)"() {


        expect:
        shortestPathWeight == trainRouter.getShortestPathWeight(startV, endV)
        where:
        shortestPathWeight | startV | endV
        9                  | "B"    | "B"
        9                  | "E"    | "E"
        16                 | "D"    | "D"


    }
}
