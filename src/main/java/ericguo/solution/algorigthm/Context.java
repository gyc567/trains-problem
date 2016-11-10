package ericguo.solution.algorigthm;

/**
 * Strategy Pattern
 * the context to run the specific algorithm
 * Created by eric567 [email:gyc567@126.com]
 * on 11/6/2016.
 */
public class Context {

    private Algorithm algorithm;

    public Context(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String calculate(String startVertex,String endVertex){
      return   algorithm.calculate(startVertex,endVertex);
    }
}
