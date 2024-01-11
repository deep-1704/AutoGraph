package autograph;

import autograph.Algorithm.dijkstra;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DijkstraTest {
    @Test
    void shortestPathsTestForWeightedGraphs(){
//        TC-01
        Graph graph = new Graph(8,false);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(3,4);
        graph.addEdge(3,7);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(4,7);
        graph.addEdge(5,6);
        graph.addEdge(6,7);

        List<Integer> sp = dijkstra.getShortestPathsFromSource(graph,0);
        List<Integer> spCheck = new ArrayList<>(Arrays.asList(0,1,2,1,2,3,3,2));
        assertThat(sp).isEqualTo(spCheck);

//        TC-02
        Graph graph2 = new Graph(9,false);
        graph2.addEdge(0,1,4);
        graph2.addEdge(0,7,8);
        graph2.addEdge(1,7,11);
        graph2.addEdge(1,2,8);
        graph2.addEdge(2,8,2);
        graph2.addEdge(2,5,4);
        graph2.addEdge(2,3,7);
        graph2.addEdge(3,5,14);
        graph2.addEdge(3,4,9);
        graph2.addEdge(4,5,10);
        graph2.addEdge(5,6,2);
        graph2.addEdge(6,8,6);
        graph2.addEdge(6,7,1);
        graph2.addEdge(7,8,7);

        List<Integer> sp2 = dijkstra.getShortestPathsFromSource(graph2,0);
        List<Integer> sp2Check = new ArrayList<>(Arrays.asList(0,4,12,19,21,11,9,8,14));
        assertThat(sp2).isEqualTo(sp2Check);
    }
}
