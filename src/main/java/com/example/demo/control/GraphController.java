package com.example.demo.control;

import com.example.demo.dataStructures.Graph;
import com.example.demo.dataStructures.IDataStructures.IGraph;
import com.example.demo.dataStructures.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GraphController {
    private Graph<Integer> graph;

    public GraphController(Graph<Integer> graph) {
        this.graph = graph;
        initialize();
    }
    public GraphController() {

    }

    public Graph<Integer> getGraph() {
        return graph;
    }

    public void setGraph(Graph<Integer> graph) {
        this.graph = graph;
    }

    public void initialize(){
        for (int i = 1; i <= 50; i++) {
            graph.addVertex(i);
        }
    }
    public void generateConnections(int start, int end) {
        if (!graph.getVertices().contains(start) || !graph.getVertices().contains(end)) return;
        if(graph.bfs(start).contains(end)) return;

        int numConnections = 0;
        // verificar que haya un c
        while (!graph.bfs(start).contains(end)) {
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
    public List<Integer> minimumPath(Integer source, Integer destination) {
        Map<Integer, Pair<Integer, Integer>> dijkstraResult = graph.dijkstra(source);

        if (dijkstraResult.containsKey(destination)  && dijkstraResult.get(destination).getFirst() != Integer.MAX_VALUE) {
            int distanceToDestination = dijkstraResult.get(destination).getFirst();

            List<Integer> pathToDestination = new ArrayList<>();
            Integer currentVertex = destination;

            while (currentVertex != null && !currentVertex.equals(source)) {
                pathToDestination.add(currentVertex);
                Pair<Integer, Integer> pair = dijkstraResult.get(currentVertex);

                // Verificar si pair o su segundo componente es nulo
                if (pair != null && pair.getSecond() != null) {
                    currentVertex = pair.getSecond();
                } else {
                    // Manejar el caso cuando pair o su segundo componente son nulos
                    // Puedes lanzar una excepción, devolver un valor predeterminado o hacer lo que sea apropiado para tu caso
                    // En este ejemplo, simplemente se rompe el bucle para evitar el NullPointerException
                    break;
                }
            }

            // Añadir el vértice inicial al camino
            pathToDestination.add(source);

            // Invertir el camino para que esté en el orden correcto (de source a destination)
            Collections.reverse(pathToDestination);

            return pathToDestination;
        }
        // No hay camino desde el vértice source hasta el vértice destination
        return null;
    }

}
