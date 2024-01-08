// Exclude below line
package autograph.CpGraphTemplate;

import autograph.Graph;

import java.util.ArrayList;
import java.util.List;

public class CpGraphTemplate {
    public static void main(String[] args) {
        List<Integer> nodes = new ArrayList<>();
        nodes.add(1);
        nodes.add(2);
        nodes.add(3);
        nodes.add(4);

        Graph<Integer> graph = new Graph<>(nodes,false);
        graph.addEdge(4,5);
    }
}
