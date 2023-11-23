package com.example.demo;

import com.example.demo.dataStructures.AdjacencyList;
import com.example.demo.dataStructures.AdjacencyMatrix;
import com.example.demo.dataStructures.IDataStructures.IGraph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HelloApplication extends Application {
    private static final double NODE_SIZE = 20.0;
    @Override
    public void start(Stage stage) throws IOException {
        // Instancia de grafo utilizando lista de adyacencia
        //IGraph<String> graph = new AdjacencyList<>();
        IGraph<String> graph = new AdjacencyMatrix<>();

        // Agregar 50 vértices
        for (int i = 1; i <= 50; i++) {
            graph.addVertex("V" + i);
        }

// Conectar todos los vértices entre sí
        for (int i = 1; i <= 50; i++) {
            for (int j = i + 1; j <= 50; j++) {
                int weight = (int) (Math.random() * 10) + 1; // Peso aleatorio entre 1 y 10
                graph.addEdge("V" + i, "V" + j, weight);
                graph.addEdge("V" + j, "V" + i, weight); // Asegurar conexión bidireccional
            }
        }
        // Aplicar el algoritmo de Prim
        Map<String, String> primResult = graph.primMST();

        // Crear una escena
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);

// Distribuir los RadioButton y conectarlos según el árbol de recubrimiento mínimo
        Map<String, RadioButton> nodeMap = new HashMap<>();
        for (String vertex : graph.getVertices()) {
            RadioButton radioButton = new RadioButton(vertex);
            nodeMap.put(vertex, radioButton);
            root.getChildren().add(radioButton);
        }

        for (String vertex : graph.getVertices()) {
            RadioButton radioButton = nodeMap.get(vertex);

            String parentVertex = primResult.get(vertex);
            if (parentVertex != null) {
                RadioButton parentRadioButton = nodeMap.get(parentVertex);

                // Posicionar el RadioButton actual al lado del padre y evitar superposiciones
                positionNodeAvoidOverlap(radioButton, parentRadioButton);
            } else {
                // Si no tiene padre, colócalo en una posición inicial y evita superposiciones
                positionNodeAvoidOverlap(radioButton, null);
            }
        }

        // Configurar y mostrar la ventana
        stage.setTitle("Graph Visualization");
        stage.setScene(scene);
        stage.show();
    }

    private RadioButton findButton(Pane root, String vertex) {
        return (RadioButton) root.getChildren().stream()
                .filter(node -> node instanceof RadioButton && ((RadioButton) node).getText().equals(vertex))
                .findFirst()
                .orElse(null);
    }
    private void positionNodeAvoidOverlap(RadioButton node, RadioButton parent) {
        double x = Math.random() * 800;
        double y = Math.random() * 600;

        if (parent != null) {
            double parentX = parent.getLayoutX();
            double parentY = parent.getLayoutY();

            // Evitar superposición
            while (Math.abs(x - parentX) < NODE_SIZE || Math.abs(y - parentY) < NODE_SIZE) {
                x = Math.random() * 800;
                y = Math.random() * 600;
            }
        }

        node.setLayoutX(x);
        node.setLayoutY(y);
    }
    public static void main(String[] args) {
        launch(args);
    }
}