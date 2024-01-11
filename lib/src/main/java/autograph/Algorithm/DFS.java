package autograph.Algorithm;

import autograph.Graph;
import autograph.Util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFS {
    private static Boolean[] visited;
    public static Boolean isConnected(Graph graph){
        int nodeCount = graph.getNodeCount();

        if((nodeCount == 0) || graph.isDirected) return false;

        List<List<Pair<Integer,Integer>>> adjacencyList = graph.getAdjacencyList();

        visited = new Boolean[nodeCount];
        Arrays.fill(visited, false);

        List<Integer>[] newAdj = new List[nodeCount];
        for(int i = 0;i<nodeCount;i++){
            newAdj[i] = new ArrayList<>();
            for(Pair<Integer,Integer> nei:adjacencyList.get(i)){
                newAdj[i].add(nei.first);
            }
        }

        isConnectedDfs(newAdj,0);

        for(int i = 0;i<nodeCount;i++){
            if(!visited[i]) return false;
        }
        return true;
    }
    private static void isConnectedDfs(List<Integer>[] adjacencyList, int src){
        visited[src] = true;
        for(Integer nei:adjacencyList[src]){
            if(!visited[nei]) isConnectedDfs(adjacencyList,nei);
        }
    }
}
