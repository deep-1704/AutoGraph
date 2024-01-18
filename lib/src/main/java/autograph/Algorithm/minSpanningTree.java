package autograph.Algorithm;

import autograph.Graph;
import autograph.Util.CustomException;
import autograph.Util.Pair;

import java.util.*;

public class minSpanningTree {
    public static Graph primsAlgo(Graph graph){
//        Check if the graph is connected
        try{
            if(!DFS.isConnected(graph)){
                throw new CustomException("Graph is disconnected.");
            }
        }catch (CustomException e){
            e.printStackTrace();
            System.exit(0);
        }

        int n = graph.getNodeCount();
        List<List<Pair<Integer,Integer>>> adjacencyList= graph.getAdjacencyList();
        Graph mst = new Graph(n,false);

//        Utils
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited,false);
        Queue<Pair<Pair<Integer,Integer>,Integer>> pq = new PriorityQueue<>(new edgeWeightComparator()); //Quite a mess here. Basically it's a pair of edge and edge-weight.

//        Initialization
        visited[0] = true;
        for(Pair<Integer,Integer> nei:adjacencyList.getFirst()){
            pq.add(new Pair<>(new Pair<>(0, nei.first), nei.second));
        }

        while(!pq.isEmpty()){
            Pair<Pair<Integer,Integer>,Integer> s = pq.remove();
            if(visited[s.first.second]) continue;

//            Update MST
            mst.addEdge(s.first.first, s.first.second, s.second);

            for(Pair<Integer,Integer> nei:adjacencyList.get(s.first.second)){
                if(!visited[nei.first]) pq.add(new Pair<>(new Pair<>(s.first.second,nei.first),nei.second));
            }
            visited[s.first.second] = true;
        }
        return mst;
    }

    public static Graph kruskalAlgo(Graph graph){

//        Check if the graph is connected
        try{
            if(!DFS.isConnected(graph)){
                throw new CustomException("Graph is disconnected.");
            }
        }catch (CustomException e){
            e.printStackTrace();
            System.exit(0);
        }

        int n = graph.getNodeCount();
        Graph mst = new Graph(n,false);

//        Utils
        List<Pair<Pair<Integer,Integer>,Integer>> edges = graph.getEdgeList();
        int[] parent = new int[n];
        int[] rank = new int[n];

        for(int i = 0;i<n;i++) parent[i] = i;
        Arrays.fill(rank,1);
        edges.sort(new edgeWeightComparator());

        int edgeCount = 0,itr = 0;
        while(edgeCount < (n-1)){
            int p1 = findParent(parent, edges.get(itr).first.first);
            int p2 = findParent(parent, edges.get(itr).first.second);
            if(p1 != p2){
//                Update MST
                mst.addEdge(edges.get(itr).first.first,edges.get(itr).first.second,edges.get(itr).second);

//                Update parents and union-heights
                if(rank[p1] > rank[p2]){
                    parent[p2] = p1;
                }
                else if(rank[p1] < rank[p2]){
                    parent[p1] = p2;
                }
                else{
                    parent[p2] = p1;
                    rank[p1]++;
                }

//                Update edge-count
                edgeCount++;
            }
            itr++;
        }

        return mst;
    }
//    KruskalUtil
    private static int findParent(int[] parent, int u){
        if(parent[u] == u) return u;
        return findParent(parent,parent[u]);
    }
}
class edgeWeightComparator implements Comparator<Pair<Pair<Integer,Integer>,Integer>> {
    @Override
    public int compare(Pair<Pair<Integer,Integer>,Integer> p1, Pair<Pair<Integer,Integer>,Integer> p2){
        return p1.second.compareTo(p2.second);
    }
}