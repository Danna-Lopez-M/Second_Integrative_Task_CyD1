package com.example.demo;

import com.example.demo.dataStructures.AdjacencyList;
import com.example.demo.dataStructures.AdjacencyMatrix;
import com.example.demo.dataStructures.IDataStructures.IGraph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Instancia de grafo utilizando lista de adyacencia
        IGraph<String> adjacencyListGraph = new AdjacencyList<>();

        // Agregar 50 vértices
        for (int i = 1; i <= 50; i++) {
            adjacencyListGraph.addVertex("V" + i);
        }

        // Agregar 50 aristas de manera aleatoria
        for (int i = 0; i < 50; i++) {
            int sourceVertex = (int) (Math.random() * 50) + 1;
            int destinationVertex = (int) (Math.random() * 50) + 1;
            int weight = (int) (Math.random() * 10) + 1; // Peso aleatorio entre 1 y 10

            adjacencyListGraph.addEdge("V" + sourceVertex, "V" + destinationVertex, weight);
        }
        // despues aplicamos prim para generar el laberinto

        // Imprimir vértices y aristas (solo para verificación)
        System.out.println("Vertices: " + adjacencyListGraph.getVertices());
        System.out.println("Aristas: " + adjacencyListGraph);

        // Instancia de grafo utilizando matriz de adyacencia
        IGraph<String> adjacencyMatrixGraph = new AdjacencyMatrix<>();

        // Agregar 50 vértices
        for (int i = 1; i <= 50; i++) {
            adjacencyMatrixGraph.addVertex("V" + i);
        }

        // Agregar 50 aristas de manera aleatoria
        for (int i = 0; i < 50; i++) {
            int sourceVertex = (int) (Math.random() * 50) + 1;
            int destinationVertex = (int) (Math.random() * 50) + 1;
            int weight = (int) (Math.random() * 10) + 1; // Peso aleatorio entre 1 y 10

            adjacencyMatrixGraph.addEdge("V" + sourceVertex, "V" + destinationVertex, weight);
        }

        // Imprimir vértices y aristas (solo para verificación)
        System.out.println("Vertices: " + adjacencyMatrixGraph.getVertices());
        System.out.println("Aristas: " + adjacencyMatrixGraph);

        /*
        *
        *
        * */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("labyrinth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        // stage es la ventana del sistema operativo
        stage.setTitle("Hello World!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}