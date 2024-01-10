package autograph;

import autograph.Algorithm.BFS;
import autograph.Algorithm.dijkstra;
import org.junit.jupiter.api.Test;

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

        List<Integer> sp = BFS.getShortestPathsFromSource(graph,0);
        assertThat(sp.get(7)).isEqualTo(2);
        assertThat(sp.get(1)).isEqualTo(1);
        assertThat(sp.get(2)).isEqualTo(2);
        assertThat(sp.get(3)).isEqualTo(1);
        assertThat(sp.get(4)).isEqualTo(2);

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
        assertThat(sp2.get(1)).isEqualTo(4);
        assertThat(sp2.get(2)).isEqualTo(12);
        assertThat(sp2.get(4)).isEqualTo(21);
    }
}
