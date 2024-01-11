package autograph;

import autograph.Algorithm.floydWarshall;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class FWTest {
    @Test
    void allPairsShortestPathTest(){
        Graph graph = new Graph(4,true);
        graph.addEdge(0,1,5);
        graph.addEdge(1,2,3);
        graph.addEdge(2,3,1);
        graph.addEdge(0,3,10);

        List<List<Integer>> sp = floydWarshall.getAllPairsShortestPaths(graph);
        List<List<Integer>> spCheck = new ArrayList<>();

        spCheck.add(new ArrayList<>(Arrays.asList(0,5,8,9)));
        spCheck.add(new ArrayList<>(Arrays.asList(Integer.MAX_VALUE,0,3,4)));
        spCheck.add(new ArrayList<>(Arrays.asList(Integer.MAX_VALUE,Integer.MAX_VALUE,0,1)));
        spCheck.add(new ArrayList<>(Arrays.asList(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0)));

        assertThat(sp).isEqualTo(spCheck);
    }
}
