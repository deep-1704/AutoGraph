package autograph.Algorithm;

import autograph.Graph;
import autograph.Util.Pair;

import java.util.*;

public class BFS {
    public static List<Integer> getShortestPathsFromSource(Graph graph, int src){
        List<List<Pair<Integer,Integer>>> adjacencyList = graph.getAdjacencyList();
        int n = adjacencyList.size();

//        Initialize result
        List<Integer> shortestPath = new ArrayList<>();
        for(int i = 0;i<n;i++) shortestPath.add(-1);
        shortestPath.set(src,0);

//        Algorithm
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);
        visited[src] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        while(!(q.isEmpty())){
            int s = q.remove();
            for(Pair<Integer,Integer> nei:adjacencyList.get(s)){
                if(!visited[nei.first]){
                    visited[nei.first] = true;
                    shortestPath.set(nei.first, shortestPath.get(s)+1);
                    q.add(nei.first);
                }
            }
        }
        return shortestPath;
    }
}
