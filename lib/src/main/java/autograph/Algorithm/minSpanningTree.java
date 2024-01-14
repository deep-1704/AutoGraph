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
}
class edgeWeightComparator implements Comparator<Pair<Pair<Integer,Integer>,Integer>> {
    @Override
    public int compare(Pair<Pair<Integer,Integer>,Integer> p1, Pair<Pair<Integer,Integer>,Integer> p2){
        return p1.second.compareTo(p2.second);
    }
}