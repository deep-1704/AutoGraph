package autograph.Algorithm;

import autograph.Graph;
import autograph.Util.Pair;

import java.util.ArrayList;
import java.util.List;

public class floydWarshall {
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
