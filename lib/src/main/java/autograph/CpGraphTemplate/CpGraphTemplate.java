package autograph.CpGraphTemplate;

import java.util.*;

public class CpGraphTemplate {
    public static void main(String[] args) {

    }
}

class Graph {
    public Boolean isDirected;
    private int nodeCount;
    private final List<List<Pair<Integer,Integer>>> adjacencyList;

    public int getNodeCount() {
        return nodeCount;
    }

    public List<List<Pair<Integer,Integer>>> getAdjacencyList() {
        return adjacencyList;
    }

    public Graph(Boolean isDirected){
        this.isDirected = isDirected;
        this.adjacencyList = new ArrayList<>();
    }
    public Graph(int totalNodes, Boolean isDirected){
        this.isDirected = isDirected;
        this.nodeCount = totalNodes;
        this.adjacencyList = new ArrayList<>();
        for(int i = 0;i<totalNodes;i++) adjacencyList.add(new ArrayList<>(0));
    }

    public void addEdge (int node1, int node2){
        try{
            if((node1 >= nodeCount) || (node2 >= nodeCount) || (node1 < 0) || (node2 < 0)){
                throw new Exception("Index out of bounds");
            }
        }catch (Exception exception){
            exception.printStackTrace();
            System.exit(0);
        }

        adjacencyList.get(node1).add(new Pair<>(node2,1));
        if(!isDirected){
            adjacencyList.get(node2).add(new Pair<>(node1,1));
        }
    }
    public void addEdge (int node1, int node2,int weight){
        try{
            if((node1 >= nodeCount) || (node2 >= nodeCount) || (node1 < 0) || (node2 < 0)){
                throw new Exception("Index out of bounds");
            }
        }catch (Exception exception){
            exception.printStackTrace();
            System.exit(0);
        }
        adjacencyList.get(node1).add(new Pair<>(node2,weight));
        if(!isDirected){
            adjacencyList.get(node2).add(new Pair<>(node1,weight));
        }
    }
    public void addNode(){
        adjacencyList.add(new ArrayList<>());
    }
}
class BFS {
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
class dijkstra {
    public static List<Integer> getShortestPathsFromSource(Graph graph, int src){
        List<List<Pair<Integer,Integer>>> adjacencyList = graph.getAdjacencyList();
        int n = adjacencyList.size();

//        Initialize result
        List<Integer> shortestPath = new ArrayList<>();
        for(int i = 0;i<n;i++) shortestPath.add(Integer.MAX_VALUE);
        shortestPath.set(src,0);

//        Algorithm
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited,false);
        Queue<Pair<Integer,Integer>> pq = new PriorityQueue<>(new distanceComparator());
        pq.add(new Pair<>(src,0));

        while(!(pq.isEmpty())){
            Pair<Integer,Integer> p = pq.remove();
            if(visited[p.first]) continue;
            for(Pair<Integer,Integer> nei:adjacencyList.get(p.first)){
                if((!visited[nei.first]) && (shortestPath.get(nei.first) > (p.second + nei.second))){
                    shortestPath.set(nei.first,p.second + nei.second);
                    pq.add(new Pair<>(nei.first, shortestPath.get(nei.first)));
                }
            }
            visited[p.first] = true;
        }

        return shortestPath;
    }
}
class distanceComparator implements Comparator<Pair<Integer,Integer>>{
    @Override
    public int compare(Pair<Integer,Integer> p1, Pair<Integer,Integer> p2){
        return p1.second.compareTo(p2.second);
    }
}
class Pair<T1,T2> {
    public T1 first;
    public T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
class DFS {
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
                throw new Exception("Cannot find articulation points for directed graph.");
            }
            if(!isConnected(graph)){
                throw new Exception("Graph is not connected. Graph should be connected in order to have articulation points.");
            }
        }
        catch (Exception exception){
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
class floydWarshall {
    public static List<List<Integer>> getAllPairsShortestPaths(Graph graph){
        List<List<Pair<Integer,Integer>>> adjacencyList = graph.getAdjacencyList();
        int n = adjacencyList.size();

//        Initializing results
        List<List<Integer>> sp = new ArrayList<>();
        for(int i = 0;i<n;i++){
            sp.add(new ArrayList<>());
            for(int j = 0;j<n;j++) {
                if(i == j) sp.get(i).add(0);
                else sp.get(i).add(Integer.MAX_VALUE);
            }
        }
        for(int i = 0;i<n;i++){
            for(Pair<Integer,Integer> p:adjacencyList.get(i)){
                sp.get(i).set(p.first,p.second);
            }
        }

//        Algorithm
        for(int k = 0;k<n;k++){
            for(int i = 0;i<n;i++){
                for(int j = 0;j<n;j++){
                    if((sp.get(i).get(k) != Integer.MAX_VALUE)
                            && (sp.get(k).get(j) != Integer.MAX_VALUE)
                            && (sp.get(i).get(j) > (sp.get(i).get(k) + sp.get(k).get(j)))){

//                        Update the distance
                        sp.get(i).set(j,sp.get(i).get(k) + sp.get(k).get(j));
                    }
                }
            }
        }
        return sp;
    }
}