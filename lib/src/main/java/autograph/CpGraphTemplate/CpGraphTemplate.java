// Template is not yet completed
// Currently using this file for testing purposes


// Exclude below line
package autograph.CpGraphTemplate;


import autograph.Algorithm.DFS;
import autograph.Graph;

import java.util.Set;

public class CpGraphTemplate {
    public static void main(String[] args) {
        Graph g = new Graph(5,false);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(3,4);

        Set<Integer> ap = DFS.getArticulationPoints(g);

        for(int i:ap){
            System.out.println(i);
        }
    }
}
