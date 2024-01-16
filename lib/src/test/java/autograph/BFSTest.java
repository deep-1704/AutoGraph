package autograph;

import autograph.Algorithm.BFS;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class BFSTest {

    @Test
    void shouldProvideCorrectShortestPaths(){
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

        int[] sp = BFS.getShortestPathsFromSource(graph,0);
        assertThat(sp[7]).isEqualTo(2);
        assertThat(sp[1]).isEqualTo(1);
        assertThat(sp[2]).isEqualTo(2);
        assertThat(sp[3]).isEqualTo(1);
        assertThat(sp[4]).isEqualTo(2);
    }

}
