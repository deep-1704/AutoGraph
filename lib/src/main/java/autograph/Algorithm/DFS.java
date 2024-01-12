package autograph.Algorithm;

import autograph.Graph;
import autograph.Util.CustomException;
import autograph.Util.Pair;

import java.util.*;

public class DFS {
    private static Boolean[] visited;
    private static int time;
    private static int[] discoveryTime;
    private static int[] lowestDiscoveryTime;
    private static Set<Integer> articulationPoints;
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

    public static Set<Integer> getArticulationPoints(Graph graph){
//        Remove edge cases
        try{
            if(graph.isDirected){
                throw new CustomException("Cannot find articulation points for directed graph.");
            }
            if(!isConnected(graph)){
                throw new CustomException("Graph is not connected. Graph should be connected in order to have articulation points.");
            }
        }
        catch (CustomException exception){
            exception.printStackTrace();
            System.exit(0);
        }

        int nodeCount = graph.getNodeCount();

        if(nodeCount == 0) return new HashSet<>();

        List<List<Pair<Integer,Integer>>> adjacencyList = graph.getAdjacencyList();

        List<Integer>[] newAdj = new List[nodeCount];
        for(int i = 0;i<nodeCount;i++){
            newAdj[i] = new ArrayList<>();
            for(Pair<Integer,Integer> nei:adjacencyList.get(i)){
                newAdj[i].add(nei.first);
            }
        }

        visited = new Boolean[nodeCount];
        discoveryTime = new int[nodeCount];
        lowestDiscoveryTime = new int[nodeCount];
        time = 0;

        Arrays.fill(visited, false);
        Arrays.fill(discoveryTime, Integer.MAX_VALUE);

        articulationPoints = new HashSet<>();

        cutVertexDfs(newAdj,0,-1);

        return articulationPoints;
    }
    private static void cutVertexDfs(List<Integer>[] newAdj, int u,int parent){
        visited[u] = true;
        discoveryTime[u] = time;
        lowestDiscoveryTime[u] = time;
        time++;
        int children = 0;
        for(int nei : newAdj[u]){
            if(!visited[nei]){
                children++;
                cutVertexDfs(newAdj,nei,u);
                lowestDiscoveryTime[u] = Math.min(lowestDiscoveryTime[u], lowestDiscoveryTime[nei]);
            }
            else if(nei != parent){
                lowestDiscoveryTime[u] = Math.min(lowestDiscoveryTime[u], discoveryTime[nei]);
            }

//            If no back-edge in subtree rooted with `nei` then `u` is an AP
            if(lowestDiscoveryTime[nei] > discoveryTime[u]) articulationPoints.add(u);
        }

//        If root node and has more than 1 child in dfs-tree then root node is an AP
        if((parent == -1) && (children > 1)) articulationPoints.add(u);
    }
}
