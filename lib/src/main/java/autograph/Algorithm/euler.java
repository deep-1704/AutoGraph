package autograph.Algorithm;

import autograph.Graph;
import autograph.Util.Pair;

import java.util.List;

public class euler {
    public static Boolean checkIfEulerUndirected(Graph graph){
        if(graph.getNodeCount() == 0 || graph.isDirected) return false;

//        Should have at most 1 non-trivial component
        if(graph.getNonTrivialComponents() > 1) return false;

//        All the vertices should be even
        int n = graph.getNodeCount();
        List<List<Pair<Integer,Integer>>> adj = graph.getAdjacencyList();
        for(int i = 0;i<n;i++){
            if(adj.get(i).size()%2 != 0) return false;
        }

//        Graph is Euler
        return true;
    }
}
