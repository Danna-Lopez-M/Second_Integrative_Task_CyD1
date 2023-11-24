package com.example.demo.dataStructures;

import com.example.demo.dataStructures.IDataStructures.IGraph;
import com.example.demo.dataStructures.exception.EdgeNotFoundException;
import com.example.demo.dataStructures.exception.ExistenceVertexException;
import com.example.demo.dataStructures.exception.VertexNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Graph<V> implements IGraph<V> {
    public void addVertex(V value) throws ExistenceVertexException{}

    public void deleteVertex(V value) throws VertexNotFoundException{}

    public void addEdge(V source, V destination, int weight) throws Exception{}

    public void removeEdge(V source, V destination, int weight) throws VertexNotFoundException, EdgeNotFoundException{}

    public void BFS(V value) throws VertexNotFoundException{}

    public void DFS(){}

    public Pair<ArrayList<Vertex<V>>, ArrayList<Integer>> dijkstra(V source) throws VertexNotFoundException{}

    public Pair<int[][], GenericMatrix<V>> floydWarshall(){}

    public Pair<ArrayList<Vertex<V>>, ArrayList<Integer>> prim(){}

    public ArrayList<Pair<Pair<Vertex<V>,Vertex<V>>,Integer>> kruskal(){}
}
