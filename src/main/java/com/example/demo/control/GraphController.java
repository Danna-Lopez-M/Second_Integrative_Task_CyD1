package com.example.demo.control;

import com.example.demo.dataStructures.IDataStructures.IGraph;

public class GraphController {
    private IGraph<Integer> graph;

    public GraphController(IGraph<Integer> graph) {
        this.graph = graph;
        initialize();
    }

    public IGraph<Integer> getGraph() {
        return graph;
    }

    public void setGraph(IGraph<Integer> graph) {
        this.graph = graph;
    }

    public void initialize(){
        for (int i = 1; i <= 50; i++) {
            graph.addVertex(i);
        }
    }
    public void generateConnections() {
        if(graph.bfs(1).contains(50)) return;
        int numConnections = 0;
        // verificar que haya un c
        while (!graph.bfs(1).contains(50)) {
            int source = (int) (Math.random() * 51);
            int target;

            do {
                target = (int) (Math.random() * 51);
            } while (source == target);

            int weight = (int) (Math.random() * 7) + 1;

            graph.addEdge(source, target, weight);
            numConnections++;
            //System.out.println("Connection added: " + source + " -> " + target);
        }
    }
}
