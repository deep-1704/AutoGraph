// Template is not yet completed
// Currently using this file for testing purposes


// Exclude below line
package autograph.CpGraphTemplate;

import autograph.Algorithm.BFS;
import autograph.Algorithm.dijkstra;
import autograph.Graph;

import java.util.ArrayList;
import java.util.List;

public class CpGraphTemplate {
    public static void main(String[] args) {

        Graph graph2 = new Graph(9,false);
        graph2.addEdge(0,1,4);
        graph2.addEdge(0,7,8);
        graph2.addEdge(1,7,11);
        graph2.addEdge(1,2,8);
        graph2.addEdge(2,8,2);
        graph2.addEdge(2,5,4);
        graph2.addEdge(2,3,7);
        graph2.addEdge(3,5,14);
        graph2.addEdge(3,4,9);
        graph2.addEdge(4,5,10);
        graph2.addEdge(5,6,2);
        graph2.addEdge(6,8,6);
        graph2.addEdge(6,7,1);
        graph2.addEdge(7,8,7);

        List<Integer> sp2 = dijkstra.getShortestPathsFromSource(graph2,0);
        System.out.println(sp2.get(1));
        System.out.println(sp2.get(2));
        System.out.println(sp2.get(3));

    }
}
