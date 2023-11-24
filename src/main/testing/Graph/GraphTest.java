package Graph;


import com.example.demo.dataStructures.AdjacentListGraph;
import com.example.demo.dataStructures.AdjacentMatrixGraph;
import com.example.demo.dataStructures.Edge;
import com.example.demo.dataStructures.Graph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    Graph<String> graph;

    /*public void setUp1(){
        //graph = new AdjacencyMatrix<>();
        //graph = new AdjacencyList<>();
    }

    @Test
    public void addVertexTestStandard(){
        setUp1();

        graph.addVertex("A");
        List<String> vertices = graph.getVertices();

        assertEquals(1, vertices.size());
        assertTrue(vertices.contains("A"));
    }

    @Test
    public void addVertexTestLimit2() {
        setUp1();
        for (int i = 0; i < 1000; i++) {
            graph.addVertex(String.valueOf(i));
        }
        assertEquals(1000, graph.getVertices().size());
    }

    @Test
    public void addVertexTestInteresting(){
        setUp1();
        graph.addVertex("A");
        graph.addVertex("A");

        List<String> vertices = graph.getVertices();

        assertEquals(1, vertices.size());
    }

    @Test
    public void addEdgeTestStandard(){
        setUp1();
        // Agregar vértices
        graph.addEdge("1", "2", 5);
        graph.addEdge("2", "3", 8);
        graph.addEdge("1", "3", 3);

        List<String> ver = graph.getVertices();
        List<String> edges = graph.getEdges("1","2");

        List<String> neighbors1 = graph.getNeighbors("1");
        List<String> neighbors2 = graph.getNeighbors("2");

        assertTrue(neighbors1.contains("2"));
        assertTrue(neighbors1.contains("3"));
        assertTrue(neighbors2.contains("3"));
    }
    @Test
    public void addEdgeTestLimit(){
        setUp1();
        graph.addVertex("1");
        graph.addVertex("3");

        graph.addEdge("1", "3", 5);

        assertEquals(5, graph.getEdgeWeight("1", "3"));
    }
    @Test
    public void addEdgeTestInteresting(){
        setUp1();
        graph.addVertex("A");
        graph.addVertex("B");


        graph.addEdge("A", "B", 0);


        List<String> neighbors = graph.getNeighbors("A");
        assertEquals(1, neighbors.size());
        assertEquals("B", neighbors.get(0));


        int storedWeight = graph.getEdgeWeight("A", "B");
        assertEquals(0, storedWeight);
    }

    @Test
    public void getNeighborsTestStandard(){
        setUp1();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 2);
        graph.addEdge("A", "D", 3);

        List<String> neighbors = graph.getNeighbors("A");

        assertEquals(3, neighbors.size());
        assertTrue(neighbors.contains("B"));
        assertTrue(neighbors.contains("C"));
        assertTrue(neighbors.contains("D"));
    }

    @Test
    public void getNeighborsTestLimit(){
        setUp1();
        graph.addVertex("A");

        List<String> neighbors = graph.getNeighbors("A");

        assertTrue(neighbors.isEmpty());
    }

    @Test
    public void getNeighborsTestInteresting(){
        setUp1();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 2);

        List<String> neighbors = graph.getNeighbors("A");

        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains("B"));
        assertTrue(neighbors.contains("C"));
        assertFalse(neighbors.contains("A"));
    }

    @Test
    public void bfsTestStandard(){
        setUp1();
        String vertex1 = "A";
        String vertex2 = "B";
        String vertex3 = "C";
        String vertex4 = "D";
        String vertex5 = "E";

        graph.addEdge(vertex1, vertex2, 1);
        graph.addEdge(vertex1, vertex3, 1);
        graph.addEdge(vertex2, vertex4, 1);
        graph.addEdge(vertex2, vertex5, 1);

        // Act
        List<String> bfsTraversal = graph.bfs(vertex1);

        // Assert
        assertEquals(5, bfsTraversal.size());
        assertEquals(vertex1, bfsTraversal.get(0));
        assertEquals(vertex2, bfsTraversal.get(1));
        assertEquals(vertex3, bfsTraversal.get(2));
        assertEquals(vertex4, bfsTraversal.get(3));
        assertEquals(vertex5, bfsTraversal.get(4));

    }
    // revisar
    @Test
    public void bfsTestLimit(){
        setUp1();
        // Obtén el recorrido BFS desde un vértice en un grafo vacío
        List<String> bfsTraversal = graph.bfs("A");

        assertNull(bfsTraversal);
    }
    @Test
    public void bfsTestIntersting(){

    }*/

}
