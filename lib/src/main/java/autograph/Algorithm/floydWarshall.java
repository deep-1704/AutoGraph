package autograph.Algorithm;

import autograph.Graph;
import autograph.Util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class floydWarshall {
    public static int[][] getAllPairsShortestPaths(Graph graph){
        List<List<Pair<Integer,Integer>>> adjacencyList = graph.getAdjacencyList();
        int n = adjacencyList.size();

//        Initializing results
        int[][] sp = new int[n][n];
        for(int i = 0;i<n;i++) Arrays.fill(sp[i],Integer.MAX_VALUE);
        for(int i = 0;i<n;i++){
            sp[i][i] = 0;
            for(Pair<Integer,Integer> p:adjacencyList.get(i)){
                sp[i][p.first] = p.second;
            }
        }

//        Algorithm
        for(int k = 0;k<n;k++){
            for(int i = 0;i<n;i++){
                for(int j = 0;j<n;j++){
                    if((sp[i][k] != Integer.MAX_VALUE)
                            && (sp[k][j] != Integer.MAX_VALUE)
                            && (sp[i][j] > (sp[i][k] + sp[k][j]))){

//                        Update the distance
                        sp[i][j] = sp[i][k]+sp[k][j];
                    }
                }
            }
        }
        return sp;
    }
}
