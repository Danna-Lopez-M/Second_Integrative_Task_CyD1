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
import javafx.scene.shape.Line;
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
    private  Rectangle redRectangle;
    private IGraph<String> graph;
    @Override
    public void start(Stage stage) throws IOException {
        // Instancia de grafo utilizando lista de adyacencia
        //IGraph<String> graph = new AdjacencyList<>();
        graph = new AdjacencyMatrix<>();

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
        // Dibujar líneas de conexión
        for (Map.Entry<String, String> entry : primResult.entrySet()) {
            String vertex = entry.getKey();
            String parentVertex = entry.getValue();

            if (parentVertex != null) {
                RadioButton node = nodeMap.get(vertex);
                RadioButton parentNode = nodeMap.get(parentVertex);

                // Dibujar línea de conexión
                drawConnectionLine(root, node, parentNode);
            }
        }

        redRectangle = new Rectangle(20, 20, Color.RED); // Puedes ajustar el tamaño y color
        root.getChildren().add(redRectangle);
        // Encontrar el RadioButton más a la izquierda y más abajo
        RadioButton leftmostDownmostButton = findLeftmostDownmostButton(root);
        redRectangle.setLayoutX(leftmostDownmostButton.getLayoutX());
        redRectangle.setLayoutY(leftmostDownmostButton.getLayoutY());

        addClickHandlers(root, nodeMap, primResult, redRectangle);

        // Configurar y mostrar la ventana
        stage.setTitle("Graph Visualization");
        stage.setScene(scene);
        stage.show();
    }
    private void drawConnectionLine(Group group, RadioButton source, RadioButton destination) {
        Line line = new Line();
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(0.5);

        double startX = source.getLayoutX() + NODE_SIZE / 2;
        double startY = source.getLayoutY() + NODE_SIZE / 2;
        double endX = destination.getLayoutX() + NODE_SIZE / 2;
        double endY = destination.getLayoutY() + NODE_SIZE / 2;

        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);

        group.getChildren().add(line);
    }

    private RadioButton findButton(Group root, String vertex) {
        return (RadioButton) root.getChildren().stream()
                .filter(node -> node instanceof RadioButton && ((RadioButton) node).getText().equals(vertex))
                .findFirst()
                .orElse(null);
    }
    private void positionNodeAvoidOverlap(RadioButton node, RadioButton parent) {
        double x = Math.random() * 700;
        double y = Math.random() * 500;

        if (parent != null) {
            double parentX = parent.getLayoutX();
            double parentY = parent.getLayoutY();

            // Evitar superposición
            while (Math.abs(x - parentX) < NODE_SIZE || Math.abs(y - parentY) < NODE_SIZE) {
                x = Math.random() * 700;
                y = Math.random() * 500;
            }
        }

        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    private RadioButton findLeftmostDownmostButton(Group root) {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        RadioButton leftmostDownmostButton = null;

        for (Node node : root.getChildren()) {
            if (node instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) node;
                double x = radioButton.getLayoutX();
                double y = radioButton.getLayoutY();

                if (x < minX || (x == minX && y > minY)) {
                    minX = x;
                    minY = y;
                    leftmostDownmostButton = radioButton;
                }
            }
        }

        return leftmostDownmostButton;
    }
    private void handleRadioButtonClick(Group root, RadioButton destinationRadioButton, Map<String, String> primResult, Rectangle redRectangle) {
        double layoutX = destinationRadioButton.localToParent(destinationRadioButton.getBoundsInLocal()).getMinX();
        double layoutY = destinationRadioButton.localToParent(destinationRadioButton.getBoundsInLocal()).getMinY();

        RadioButton currentRadioButton = findRadioButtonAt(redRectangle, root);

        System.out.println("(" + layoutX + "," + layoutY + ")");

        // Obtener los vértices asociados a los RadioButtons
        String vertex1 = currentRadioButton.getText();
        String vertex2 = destinationRadioButton.getText();

        if (graph.getNeighbors(vertex1).contains(vertex2)){
            System.out.println("neighbors");
        }else {
            System.out.println("not connected...");
        }
    }

    private void addClickHandlers(Group root, Map<String, RadioButton> nodeMap, Map<String, String> primResult, Rectangle redRectangle) {
        for (RadioButton radioButton : nodeMap.values()) {
            radioButton.setOnAction(event -> handleRadioButtonClick(root, radioButton, primResult, redRectangle));
        }
    }

    private RadioButton findRadioButtonAt(Rectangle redRectangle, Group root) {
        double epsilon = 0.5; // Puedes ajustar el valor de epsilon según sea necesario

        for (Node node : root.getChildren()) {
            if (node instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) node;

                double nodeX = radioButton.localToParent(radioButton.getBoundsInLocal()).getMinX();
                double nodeY = radioButton.localToParent(radioButton.getBoundsInLocal()).getMinY();

                // Verificar si las coordenadas están dentro del rango epsilon
                if (Math.abs(nodeX - redRectangle.getLayoutX()) < epsilon
                        && Math.abs(nodeY - redRectangle.getLayoutY()) < epsilon) {
                    return radioButton;
                }
            }
        }

        return null; // Si no se encuentra ningún RadioButton en esas coordenadas
    }


    public static void main(String[] args) {
        launch(args);
    }
}