package com.example.demo.dataStructures;

import com.example.demo.dataStructures.IDataStructures.IGraph;

import java.util.List;
import java.util.Map;

public abstract class Graph<T> implements IGraph<T> {
    @Override
    public void addVertex(T vertex) {}

    @Override
    public void addEdge(T source, T destination, int weight) {}

    @Override
    public List<T> getVertices() {
        return null;
    }

    @Override
    public List<T> getNeighbors(T  vertex) {
        return null;
    }

    @Override
    public List<T> bfs(T start) {
        return null;
    }

    @Override
    public List<T> dfs(T start) {return null;}

    @Override
    public void removeVertex(T value) {}

    @Override
    public void removeEdge(T source, T destination) {}

    @Override
    public void clear() {}

    @Override
    public Map<T, Pair<Integer, T>> dijkstra(T start) {
        return null;
    }

    @Override
    public Map<T, Map<T, Integer>> floydWarshall() {
        return null;
    }

    @Override
    public Map<T, T> primMST() {
        return null;
    }

    @Override
    public List<Edge<T>> kruskalMST() {
        return null;
    }

    @Override
    public int getEdgeWeight(T a, T b) {
        return 0;
    }
}
