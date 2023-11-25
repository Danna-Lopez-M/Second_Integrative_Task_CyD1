package com.example.demo.dataStructures.IDataStructures;

import com.example.demo.dataStructures.Edge;
import com.example.demo.dataStructures.Pair;

import java.util.List;
import java.util.Map;

public interface IGraph<T> {
    void addVertex(T vertex);
    void addEdge(T source, T destination, int weight);
    List<T> getVertices();
    List<T> getNeighbors(T vertex);
    List<T> bfs(T start);
    List<T> dfs(T start);
    void removeVertex(T value);
    void removeEdge(T source, T destination);
    void clear();
    Map<T, Pair<Integer, T>> dijkstra(T start);
    Map<T, Map<T, Integer>> floydWarshall();
    Map<T, T> primMST();
    List<Edge<T>> kruskalMST();

    int getEdgeWeight(T a, T b);
}
