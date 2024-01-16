package autograph.Algorithm;

import autograph.Graph;
import autograph.Util.Pair;

import java.util.*;

public class dijkstra {
    public static int[] getShortestPathsFromSource(Graph graph, int src){
        List<List<Pair<Integer,Integer>>> adjacencyList = graph.getAdjacencyList();
        int n = adjacencyList.size();

//        Initialize result
        int[] shortestPath = new int[n];
        Arrays.fill(shortestPath,Integer.MAX_VALUE);
        shortestPath[src] = 0;

//        Algorithm
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited,false);
        Queue<Pair<Integer,Integer>> pq = new PriorityQueue<>(new distanceComparator());
        pq.add(new Pair<>(src,0));

        while(!(pq.isEmpty())){
            Pair<Integer,Integer> p = pq.remove();
            if(visited[p.first]) continue;
            for(Pair<Integer,Integer> nei:adjacencyList.get(p.first)){
                if((!visited[nei.first]) && (shortestPath[nei.first] > (p.second + nei.second))){
                    shortestPath[nei.first] = p.second + nei.second;
                    pq.add(new Pair<>(nei.first, shortestPath[nei.first]));
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
