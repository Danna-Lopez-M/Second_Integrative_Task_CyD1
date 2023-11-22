package com.example.demo.model;

import com.example.demo.dataStructures.Edge;
import com.example.demo.dataStructures.IDataStructures.IGraph;
import com.example.demo.dataStructures.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MazeGenerator {
    // Clase interna para representar una celda en el laberinto
    private static class Cell {
        private int row;
        private int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
/*    public static List<Edge<Cell>> generateMazeListAdjacency(IGraph<Cell> graph) {
        List<Edge<Cell>> mazeEdges = new ArrayList<>();

        Map<Cell, Cell> primMST = graph.primMST();
        for (Cell destination : primMST.keySet()) {
            Cell source = primMST.get(destination);
            int weight = graph.getEdgeWeight(source, destination);
            mazeEdges.add(new Edge<>(source, destination, weight));
        }

        return mazeEdges;
    }*/
}
