package com.example.demo;

import com.example.demo.dataStructures.AdjacencyList;
import com.example.demo.dataStructures.AdjacencyMatrix;
import com.example.demo.dataStructures.IDataStructures.IGraph;
import com.example.demo.dataStructures.Pair;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.ChoiceBox;

public class HelloApplication extends Application {
    private static final double NODE_SIZE = 20.0;
    private  Rectangle redRectangle;
    private IGraph<Integer> graph;
    private static final double BUTTON_SIZE = 20.0;
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 10;
    private static final double DISTANCE_X = 800.0 / NUM_COLS;
    private static final double DISTANCE_Y = 600.0 / NUM_ROWS;

    private Group root;

    private int currentVertex = 1;
    private Rectangle rectanglePlayer;
    private int resistencia = 100;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Graph Selection");

        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");

        ChoiceBox<String> graphTypeChoiceBox = new ChoiceBox<>(
                FXCollections.observableArrayList("Adjacency List", "Adjacency Matrix"));
        graphTypeChoiceBox.getSelectionModel().selectFirst();
        graph = new AdjacencyList<>();

        Button startButton = new Button("Start Visualization");
        //startButton.setDisable(true);

        graphTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    startButton.setDisable(false);
                    // Selecciona la implementación del grafo según la elección del usuario
                    if (newValue.equals("Adjacency List")) {
                        graph = new AdjacencyList<>();
                        System.out.println("adjacency list selected");
                    } else if (newValue.equals("Adjacency Matrix")) {
                        graph = new AdjacencyMatrix<>();
                        System.out.println("adjacency matrix selected");
                    }
                });

        startButton.setOnAction(e -> {
            primaryStage.hide();
            Stage newStage = new Stage();
            startGraphVisualization(newStage);
        });

        vbox.getChildren().addAll(graphTypeChoiceBox, startButton);

        primaryStage.setScene(new Scene(vbox, 300, 150));
        primaryStage.show();
    }
    public void startGraphVisualization(Stage stage) {
        // Instancia de grafo utilizando lista de adyacencia
        //IGraph<String> graph = new AdjacencyList<>();
        //graph = new AdjacencyMatrix<>();

        // Agregar 50 vértices
        for (int i = 0; i <= 49; i++) {
            graph.addVertex(i);
        }


        generateConnections(49, 1);


        root = new Group();
        Scene scene = new Scene(root, 800, 600);

        Map<Integer, RadioButton> nodeMap = new HashMap<>();
        for (int i = 1; i <= NUM_ROWS * NUM_COLS; i++) {
            RadioButton radioButton = new RadioButton(String.valueOf(i));
            nodeMap.put(i - 1, radioButton);
            root.getChildren().add(radioButton);

            // Calcular las coordenadas para formar una cuadrícula
            double col = (i - 1) % NUM_COLS;
            double row = (i - 1) / NUM_COLS;

            double x = col * DISTANCE_X;
            double y = row * DISTANCE_Y;

            radioButton.setLayoutX(x);
            radioButton.setLayoutY(y);

            // Manejar el evento de clic en el RadioButton
            radioButton.setOnAction(event -> handleRadioButtonClick(radioButton));
        }

        //redRectangle = new Rectangle(20, 20, Color.RED); // Puedes ajustar el tamaño y color
        //root.getChildren().add(redRectangle);


        drawConnectionLines(root, nodeMap);

        rectanglePlayer = new Rectangle(20, 20, Color.RED);
        //root.getChildren().add(rectanglePlayer);
        //addRectangleAtVertex(1, root, rectanglePlayer);
        // Configurar y mostrar la ventana
        stage.setTitle("Graph Visualization");
        stage.setScene(scene);
        stage.show();
    }
    private void generateConnections(int numConnections, int n){

        for (int i = 0; i <= numConnections; i++) {
            graph.addEdge(((int) (Math.random() * 50)), (int) (Math.random() * 50), (int) (Math.random() * 7) + 1);
        }
       Map<Integer, Pair<Integer, Integer>> dijsktraResult = graph.dijkstra(0);
         /*for (int elem:
             dijsktraResult.keySet()) {
            System.out.println(elem);
        }*/
        if(dijsktraResult.containsKey(49)){
            System.out.println(dijsktraResult);
            return;
        }else {
            generateConnections((numConnections / n+1), n + 2);
        }
    }

    private void drawConnectionLines(Group group, Map<Integer, RadioButton> nodeMap) {
        int size = graph.getVertices().size();

        System.out.println("size: " + size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (graph.getEdgeWeight(i, j) != -1) {
                    RadioButton node1 = nodeMap.get(i);
                    RadioButton node2 = nodeMap.get(j);

                    if (node1 != null && node2 != null) {
                        drawConnectionLine(group, node1, node2);
                    }
                }
            }
        }
    }

    private void drawConnectionLine(Group group, RadioButton source, RadioButton destination) {
        Line line = new Line();
        line.setStrokeWidth(1.5);

        double startX = source.getLayoutX() + BUTTON_SIZE / 2;
        double startY = source.getLayoutY() + BUTTON_SIZE / 2;
        double endX = destination.getLayoutX() + BUTTON_SIZE / 2;
        double endY = destination.getLayoutY() + BUTTON_SIZE / 2;

        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);

        group.getChildren().add(line);
    }

    private void handleRadioButtonClick(RadioButton radioButton) {
        // Obtener el vértice asociado al RadioButton
        int vertex = Integer.parseInt(radioButton.getText());

        // Hacer lo que necesites con la referencia al RadioButton y al vértice
        System.out.println("Clic en RadioButton: " + radioButton.getText());
        //System.out.println("Vértice asociado: " + vertex);

        if (graph.bfs(currentVertex).contains(vertex)){
            addRectangleAtVertex(vertex, this.root, rectanglePlayer);
        }
        resistencia = resistencia - graph.getEdgeWeight(currentVertex, vertex);

        if (resistencia <= 0){
            // generar prim o kruskal para mostrar el camino minimo
        }else{
            //
            return;
        }
    }

    private void addRectangleAtVertex(int targetVertex, Group root, Rectangle rectangle) {

        for (Node node : root.getChildren()) {
            if (node instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) node;
                int vertex = Integer.parseInt(radioButton.getText()) - 1;

                System.out.println("vertex: " + vertex);

                if (vertex == targetVertex) {
                    System.out.println("entra");
                    rectangle.setLayoutX(radioButton.getLayoutX());
                    rectangle.setLayoutY(radioButton.getLayoutY());
                }
            }
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}