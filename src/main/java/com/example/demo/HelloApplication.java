package com.example.demo;

import com.example.demo.dataStructures.AdjacencyList;
import com.example.demo.dataStructures.AdjacencyMatrix;
import com.example.demo.dataStructures.IDataStructures.IGraph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

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

// Conectar todos los vértices entre sí
        for (int i = 1; i <= 50; i++) {
            for (int j = i + 1; j <= 50; j++) {
                int weight = (int) (Math.random() * 10) + 1; // Peso aleatorio entre 1 y 10
                adjacencyListGraph.addEdge("V" + i, "V" + j, weight);
                adjacencyListGraph.addEdge("V" + j, "V" + i, weight); // Asegurar conexión bidireccional
            }
        }
        // Aplicar el algoritmo de Prim
        Map<String, String> primResult = adjacencyListGraph.primMST();

        // Crear una interfaz gráfica para visualizar el árbol de recubrimiento mínimo
        Pane root = new Pane();

        // Crear RadioButtons para cada vértice
        ToggleGroup toggleGroup = new ToggleGroup();
        for (String vertex : adjacencyListGraph.getVertices()) {
            RadioButton radioButton = new RadioButton(vertex);
            radioButton.setToggleGroup(toggleGroup);
            root.getChildren().add(radioButton);
        }

        // Dibujar las aristas del árbol de recubrimiento mínimo
        for (Map.Entry<String, String> entry : primResult.entrySet()) {
            String sourceVertex = entry.getKey();
            String destinationVertex = entry.getValue();

            // Encuentra los RadioButtons correspondientes a los vértices de la arista
            RadioButton sourceButton = findButton(root, sourceVertex);
            RadioButton destinationButton = findButton(root, destinationVertex);

            // Dibujar la arista entre los RadioButtons
            // Aquí podrías dibujar una línea, unir los vértices visualmente, etc.
        }

        Scene scene = new Scene(root,800, 600);
        stage.setTitle("Labyrinth Visualization");
        stage.setScene(scene);
        stage.show();
    }

    private RadioButton findButton(Pane root, String vertex) {
        return (RadioButton) root.getChildren().stream()
                .filter(node -> node instanceof RadioButton && ((RadioButton) node).getText().equals(vertex))
                .findFirst()
                .orElse(null);
    }
    public static void main(String[] args) {
        launch(args);
    }
}