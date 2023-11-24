package com.example.demo.dataStructures;

public class Edge<V> {

    private Vertex<V> source;
    private Vertex<V> node;
    private int weight;

    public Edge(Vertex<V> source, Vertex<V> neighbor, int weight) {
        this.source= source;
        this.node = neighbor;
        this.weight = weight;
    }

    public Vertex<V> getNode() {
        return node;
    }

    public void setNode(Vertex<V> neighbor) {
        this.node = neighbor;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex<V> getSource() {
        return source;
    }

    public void setSource(Vertex<V> source) {
        this.source = source;
    }
}

