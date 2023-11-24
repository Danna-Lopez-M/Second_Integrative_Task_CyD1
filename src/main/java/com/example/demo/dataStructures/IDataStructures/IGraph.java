package com.example.demo.dataStructures.IDataStructures;

import com.example.demo.dataStructures.GenericMatrix;
import com.example.demo.dataStructures.Pair;
import com.example.demo.dataStructures.Vertex;
import com.example.demo.dataStructures.exception.EdgeNotFoundException;
import com.example.demo.dataStructures.exception.ExistenceVertexException;
import com.example.demo.dataStructures.exception.VertexNotFoundException;

import java.util.ArrayList;

public interface IGraph<V> {
    void addVertex(V value) throws ExistenceVertexException;

    void deleteVertex(V value) throws VertexNotFoundException;

    void addEdge(V source, V destination, int weight) throws Exception;

    void removeEdge(V source, V destination, int weight) throws VertexNotFoundException, EdgeNotFoundException;

    void BFS(V value) throws VertexNotFoundException;

    void DFS();

    Pair<ArrayList<Vertex<V>>, ArrayList<Integer>> dijkstra(V source) throws VertexNotFoundException;

    Pair<int[][], GenericMatrix<V>> floydWarshall();

    Pair<ArrayList<Vertex<V>>, ArrayList<Integer>> prim();

    ArrayList<Pair<Pair<Vertex<V>,Vertex<V>>,Integer>> kruskal();

}
