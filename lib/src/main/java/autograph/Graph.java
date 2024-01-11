package autograph;

import java.util.ArrayList;
import java.util.List;

import autograph.Util.CustomException;
import autograph.Util.Pair;

public class Graph {
    public Boolean isDirected;
    private int nodeCount;
    private List<List<Pair<Integer,Integer>>> adjacencyList;

    public int getNodeCount() {
        return nodeCount;
    }

    public List<List<Pair<Integer,Integer>>> getAdjacencyList() {
        return adjacencyList;
    }

    public Graph(Boolean isDirected){
        this.isDirected = isDirected;
        this.adjacencyList = new ArrayList<>();
    }
    public Graph(int totalNodes, Boolean isDirected){
        this.isDirected = isDirected;
        this.nodeCount = totalNodes;
        this.adjacencyList = new ArrayList<>();
        for(int i = 0;i<totalNodes;i++) adjacencyList.add(new ArrayList<>(0));
    }

    public void addEdge (int node1, int node2){
        try{
            if((node1 >= nodeCount) || (node2 >= nodeCount) || (node1 < 0) || (node2 < 0)){
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
            if((node1 >= nodeCount) || (node2 >= nodeCount) || (node1 < 0) || (node2 < 0)){
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
    public void addNode(){
        adjacencyList.add(new ArrayList<>());
    }
}
