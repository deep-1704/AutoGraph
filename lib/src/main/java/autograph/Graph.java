package autograph;

import java.util.ArrayList;
import java.util.List;

import autograph.Algorithm.BFS;
import autograph.Algorithm.DFS;
import autograph.Util.CustomException;
import autograph.Util.Pair;

public class Graph {
    public Boolean isDirected;
    private int nodeCount;
    private List<List<Pair<Integer,Integer>>> adjacencyList;
    private List<Pair<Pair<Integer,Integer>,Integer>> edgeList;

    public List<Pair<Pair<Integer, Integer>, Integer>> getEdgeList() {
        return edgeList;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public List<List<Pair<Integer,Integer>>> getAdjacencyList() {
        return adjacencyList;
    }

    public Graph(Boolean isDirected){
        this.isDirected = isDirected;
        this.adjacencyList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
    }
    public Graph(int totalNodes, Boolean isDirected){
        this.isDirected = isDirected;
        this.nodeCount = totalNodes;
        this.edgeList = new ArrayList<>();
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
            System.exit(0);
        }

        edgeList.add(new Pair<>(new Pair<>(node1,node2),1));
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
            System.exit(0);
        }

        edgeList.add(new Pair<>(new Pair<>(node1,node2),weight));
        adjacencyList.get(node1).add(new Pair<>(node2,weight));
        if(!isDirected){
            adjacencyList.get(node2).add(new Pair<>(node1,weight));
        }
    }
    public void addNode(){
        adjacencyList.add(new ArrayList<>());
    }

//    Some utility methods ::

    public Boolean isBipartite(){
        return BFS.isBipartite(this);
    }

    public Boolean isConnected(){
        return DFS.isConnected(this);
    }

    public int getNonTrivialComponents(){
        return BFS.getNonTrivialComponents(this);
    }
}
