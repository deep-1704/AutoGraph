package autograph.Algorithm;

import autograph.Graph;
import autograph.Util.Pair;

import java.util.*;

public class BFS {
    private static Boolean[] visited;
    private static Boolean[] partite;
    public static int[] getShortestPathsFromSource(Graph graph, int src){
        List<List<Pair<Integer,Integer>>> adjacencyList = graph.getAdjacencyList();
        int n = adjacencyList.size();

//        Initialize result
        int[] shortestPath = new int[n];
        Arrays.fill(shortestPath,Integer.MAX_VALUE);
        shortestPath[src] = 0;

//        Algorithm
        visited = new Boolean[n];
        Arrays.fill(visited, false);
        visited[src] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        while(!(q.isEmpty())){
            int s = q.remove();
            for(Pair<Integer,Integer> nei:adjacencyList.get(s)){
                if(!visited[nei.first]){
                    visited[nei.first] = true;
                    shortestPath[nei.first] = shortestPath[s]+1;
                    q.add(nei.first);
                }
            }
        }
        return shortestPath;
    }

    public static Boolean isBipartite(Graph graph){
        int n = graph.getNodeCount();
        List<List<Pair<Integer,Integer>>> adj = graph.getAdjacencyList();

        partite = new Boolean[n];
        visited = new Boolean[n];

        Arrays.fill(visited,false);

        for(int i = 0;i<n;i++){
            if(!visited[i]) bipartiteBfs(i,adj);
        }
        for(int i = 0;i<n;i++){
            for(Pair<Integer,Integer> nei: adj.get(i)){
                if(partite[i] == partite[nei.first]) return false;
            }
        }
        return true;
    }
    private static void bipartiteBfs(int src, List<List<Pair<Integer,Integer>>> adj){
        visited[src] = true;
        partite[src] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(src);

        while(!q.isEmpty()){
            int s = q.remove();
            for(Pair<Integer,Integer> nei:adj.get(s)){
                if(!visited[nei.first]){
                    partite[nei.first] = !partite[s];
                    visited[nei.first] = true;
                    q.add(nei.first);
                }
            }
        }
    }
}
