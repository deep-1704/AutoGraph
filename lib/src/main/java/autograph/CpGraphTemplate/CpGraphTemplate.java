// Template is not yet completed
// Currently using this file for testing purposes


// Exclude below line
package autograph.CpGraphTemplate;


import autograph.Algorithm.DFS;
import autograph.Graph;

public class CpGraphTemplate {
    public static void main(String[] args) {
        Graph g = new Graph(4,true);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,2);
        g.addEdge(2,3);

        System.out.println(DFS.isConnected(g));
    }
}
