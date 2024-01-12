package autograph;

import autograph.Algorithm.DFS;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
public class ArticulationPointsTest {
    @Test
    void shouldReturnCorrectArticulationPoints(){
//        TC-01
        Graph g = new Graph(5,false);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(3,4);

        Set<Integer> ap = DFS.getArticulationPoints(g);
        Set<Integer> apCheck = new HashSet<>();
        apCheck.add(2);
        apCheck.add(3);

        assertThat(ap).isEqualTo(apCheck);
    }
}
