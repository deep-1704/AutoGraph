package autograph;

import autograph.Algorithm.minSpanningTree;
import autograph.Util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class MstTest {
    @Test
    void primsTest(){
//        TC-01
        Graph g1 = new Graph(9,false);
        g1.addEdge(0,1,4);
        g1.addEdge(0,7,8);
        g1.addEdge(1,7,11);
        g1.addEdge(1,2,8);
        g1.addEdge(7,8,7);
        g1.addEdge(7,6,1);
        g1.addEdge(2,8,2);
        g1.addEdge(8,6,6);
        g1.addEdge(2,3,7);
        g1.addEdge(2,5,4);
        g1.addEdge(6,5,2);
        g1.addEdge(3,5,14);
        g1.addEdge(3,4,9);
        g1.addEdge(5,4,10);

        Graph mst1 = minSpanningTree.primsAlgo(g1);
        List<List<Pair<Integer,Integer>>> adj = mst1.getAdjacencyList();
        int netEdgeWeight = 0;
        for(int i = 0;i<mst1.getNodeCount();i++){
            for(Pair<Integer,Integer> nei:adj.get(i)) netEdgeWeight+=nei.second;
        }

        assertThat(netEdgeWeight/2).isEqualTo(37);
    }

    @Test
    void krushkalTest(){

    }
}
