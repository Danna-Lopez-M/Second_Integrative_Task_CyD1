package Graph;

import dataStructures.AdjacencyList;
import dataStructures.AdjacencyMatrix;
import dataStructures.IDataStructures.IGraph;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {
    IGraph<String> graph;
    public void setUpAddVertex1(){
        graph = new AdjacencyMatrix<>();
        //graph = new AdjacencyList<>();
    }
    @Test
    public void addVertexTestStandard(){
        setUpAddVertex1();

        graph.addVertex("A");

        List<String> vertices = graph.getVertices();

        assertEquals(1, vertices.size());
        assertTrue(vertices.contains("A"));

    }

    @Test
    public void addVertexTestLimit(){
        setUpAddVertex1();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        List<String> vertices = graph.getVertices();

        assertEquals(3, vertices.size());
        assertTrue(vertices.contains("A"));
        assertTrue(vertices.contains("B"));
        assertTrue(vertices.contains("C"));

    }
    @Test
    public void addVertexTestInteresting(){
        setUpAddVertex1();
        graph.addVertex("A");
        graph.addVertex("A");
        graph.addVertex("A");

        List<String> vertices = graph.getVertices();

        assertEquals(1, vertices.size());
    }

    public void setUpAddEdge1(){
        graph = new AdjacencyMatrix<>();
        //graph = new AdjacencyList<>();
    }

    @Test
    public void addEdgeTestStandard(){
        setUpAddEdge1();
        // Agregar vértices
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");

        graph.addEdge("1", "2", 5);
        graph.addEdge("2", "3", 8);
        graph.addEdge("1", "3", 3);

        // Verificar si las aristas se crean correctamente
        List<String> neighbors1 = graph.getNeighbors("1");
        List<String> neighbors2 = graph.getNeighbors("2");

        assertTrue(neighbors1.contains("2"));
        assertTrue(neighbors1.contains("3"));
        assertTrue(neighbors2.contains("3"));
    }
    @Test
    public void addEdgeTestLimit(){
        setUpAddEdge1();
        graph.addVertex("1");
        graph.addVertex("2");

        graph.addEdge("1", "3", 5);

        assertEquals(5, graph.getEdgeWeight("1", "3"));
    }
    @Test
    public void addEdgeTestInteresting(){
        setUpAddEdge1();
        graph.addVertex("A");
        graph.addVertex("B");


        graph.addEdge("A", "B", 0);


        List<String> neighbors = graph.getNeighbors("A");
        assertEquals(1, neighbors.size());
        assertEquals("B", neighbors.get(0));


        int storedWeight = graph.getEdgeWeight("A", "B");
        assertEquals(0, storedWeight);
    }

    public void setUpGetNeighbors1(){
        graph = new AdjacencyMatrix<>();
        //graph = new AdjacencyList<>();
    }
    @Test
    public void getNeighborsTestStandard(){
        // arreglar la confusion
    }

    @Test
    public void getNeighborsTestLimit(){
        setUpGetNeighbors1();
        graph.addVertex("A");

        List<String> neighbors = graph.getNeighbors("A");

        assertTrue(neighbors.isEmpty());
    }
}
