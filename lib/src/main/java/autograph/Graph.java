package autograph;

import java.util.ArrayList;
import java.util.List;

import autograph.Util.CustomException;
import autograph.Util.Pair;

public class Graph<Node> {
    public Boolean isDirected;
    private List<Node> nodes;
    private List<List<Pair<Integer,Integer>>> adjacencyList;

    public List<List<Pair<Integer,Integer>>> getAdjacencyList() {
        return adjacencyList;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Graph(Boolean isDirected){
        this.isDirected = isDirected;
        this.nodes = new ArrayList<>(0);
        this.adjacencyList = new ArrayList<>(0);
    }
    public Graph(List<Node> nodes,Boolean isDirected){
        this.isDirected = isDirected;
        this.nodes = nodes;
        this.adjacencyList = new ArrayList<>(nodes.size());
    }

    public void addEdge (int node1, int node2){
        try{
            if((node1 >= nodes.size()) || (node2 >= nodes.size()) || (node1 < 0) || (node2 < 0)){
                throw new CustomException("Index out of bounds");
            }
        }catch (CustomException exception){
            exception.printStackTrace();
            return;
        }

        adjacencyList.get(node1).add(new Pair<>(node2,1));
        if(!isDirected){
            adjacencyList.get(node2).add(new Pair<>(node1,1));
        }

    }
    public void addEdge (int node1, int node2,int weight){
        try{
            if((node1 >= nodes.size()) || (node2 >= nodes.size()) || (node1 < 0) || (node2 < 0)){
                throw new CustomException("Index out of bounds");
            }
        }catch (CustomException exception){
            exception.printStackTrace();
            return;
        }
        adjacencyList.get(node1).add(new Pair<>(node2,weight));
        if(!isDirected){
            adjacencyList.get(node2).add(new Pair<>(node1,weight));
        }
    }
    public void addNode(Node node){
        nodes.add(node);
        adjacencyList.add(new ArrayList<>(0));
    }

}
