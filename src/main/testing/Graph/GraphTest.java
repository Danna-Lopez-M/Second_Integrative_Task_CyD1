package Graph;


import com.example.demo.dataStructures.AdjacencyList;
import com.example.demo.dataStructures.AdjacencyMatrix;

import com.example.demo.dataStructures.Edge;
import com.example.demo.dataStructures.Graph;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    Graph<String> graph;

    public void setUp1(){
        graph = new AdjacencyMatrix<>();
        //graph = new AdjacencyList<>();
    }

    public void setUp2(){
        graph = new AdjacencyList<>();
        //graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
    }

    public void setUp3(){
        graph = new AdjacencyList<>();
        //graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 8);
    }

    public void setUp4(){
        graph = new AdjacencyList<>();
        //graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 5);
        graph.addEdge("B", "D", 5);
        graph.addEdge("B", "E", 5);
        graph.addEdge("F", "G", 5);
    }

    public void setUp5(){
        graph = new AdjacencyList<>();
        //graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
    }

    @Test
    public void addVertexTestStandard(){
        //Add a vertex to an empty graph
        setUp1();

        graph.addVertex("A");
        List<String> vertices = graph.getVertices();

        assertEquals(1, vertices.size());
        assertTrue(vertices.contains("A"));
    }

    @Test
    public void addVertexTestLimit() {
        //Add 1000 vertices to an empty graph
        setUp1();
        for (int i = 0; i < 1000; i++) {
            graph.addVertex(String.valueOf(i));
        }
        assertEquals(1000, graph.getVertices().size());
    }

    @Test
    public void addVertexTestInteresting(){
        //Add a duplicated vertex to a graph with vertices
        setUp2();
        graph.addVertex("A");
        List<String> vertices = graph.getVertices();

        assertEquals(3, vertices.size());
    }
    @Test
    public void removeVertexTestStandard(){
        //Remove from a graph with vertices
        setUp2();

        graph.removeVertex("A");
        List<String> vertices = graph.getVertices();

        assertEquals(2, vertices.size());
        assertFalse(vertices.contains("A"));
    }

    @Test
    public void removeVertexTestLimit(){
        //Remove from an empty graph
        setUp1();

        graph.removeVertex("A");
        List<String> vertices = graph.getVertices();

        assertEquals(0, vertices.size());
    }

    @Test
    public void removeVertexTestInteresting(){
        //Remove a vertex with edges
        setUp3();

        List<String> initialVertices = graph.getVertices();
        List<String> initialNeighborsA = graph.getNeighbors("A");
        graph.removeVertex("A");
        List<String> vertices = graph.getVertices();
        List<String> neighborsA = graph.getNeighbors("A");

        assertEquals(3, initialVertices.size());
        assertTrue(initialVertices.contains("A"));
        assertTrue(initialNeighborsA.contains("B"));
        assertEquals(2, vertices.size());
        assertFalse(vertices.contains("A"));
        assertEquals(0, neighborsA.size());
    }

    @Test
    public void addEdgeTestStandard(){
        //Add an edge to a graph with vertices
        setUp2();
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 8);
        graph.addEdge("A", "C", 3);

        List<String> neighborsA = graph.getNeighbors("A");
        List<String> neighborsB = graph.getNeighbors("B");

        assertTrue(neighborsA.contains("B"));
        assertTrue(neighborsA.contains("C"));
        assertTrue(neighborsB.contains("C"));
    }

    @Test
    public void addEdgeTestLimit(){
        //Add an edge to an empty graph
        setUp1();
        graph.addEdge("A", "B", 5);

        assertEquals(5, graph.getEdgeWeight("A", "B"));
        assertTrue(graph.getNeighbors("A").contains("B"));
    }
    @Test
    public void addEdgeTestInteresting(){
        //Add a duplicated edge to a graph with edges
        setUp3();
        List<String> initialNeighborsA = graph.getNeighbors("A");
        graph.addEdge("A", "B", 3);
        List<String> neighborsA = graph.getNeighbors("A");

        assertEquals(1, initialNeighborsA.size());
        assertEquals(1, neighborsA.size());
    }

    @Test
    public void removeEdgeTestStandard(){
        //Remove edge from a graph with edges
        setUp3();

        graph.removeEdge("A", "B");
        List<String> neighborsA = graph.getNeighbors("A");

        assertFalse(neighborsA.contains("B"));
    }

    @Test
    public void removeEdgeTestLimit(){
        //Remove edge from an empty graph
        setUp1();

        graph.removeEdge("A", "B");
        List<String> neighborsA = graph.getNeighbors("A");

        assertEquals(0, neighborsA.size());
    }

    @Test
    public void removeEdgeTestInteresting(){
        //Remove an edge from a graph with vertices
        setUp4();

        List<String> initialNeighborsA = graph.getNeighbors("A");
        graph.removeEdge("A", "B");
        List<String> neighborsA = graph.getNeighbors("A");

        assertEquals(2, initialNeighborsA.size());
        assertTrue(initialNeighborsA.contains("B"));
        assertEquals(1, neighborsA.size());
        assertFalse(neighborsA.contains("B"));
    }

    @Test
    public void bfsTestStandard(){
        setUp4();

        // Act
        List<String> bfsTraversal = graph.bfs("A");

        // Assert
        assertEquals(5, bfsTraversal.size());
        assertEquals("A", bfsTraversal.get(0));
        assertEquals("B", bfsTraversal.get(1));
        assertEquals("C", bfsTraversal.get(2));
        assertEquals("D", bfsTraversal.get(3));
        assertEquals("E", bfsTraversal.get(4));

    }
    // revisar
    @Test
    public void bfsTestLimit(){
        // BFS from a vertex that does not exist
        setUp1();
        List<String> bfsTraversal = graph.bfs("A");

        assertNull(bfsTraversal);
    }
    @Test
    public void bfsTestIntersting(){
        // BFS from a graph that is disconnected
        setUp4();
        List<String> bfsTraversal = graph.bfs("F");

        assertEquals(7, graph.getVertices().size());
        assertEquals(2, bfsTraversal.size());
        assertEquals("F", bfsTraversal.get(0));
        assertEquals("G", bfsTraversal.get(1));
    }

    @Test
    public void dfsTestStandard(){
        setUp4();

        // Act
        List<String> dfsTraversal = graph.dfs("A");

        // Assert
        assertEquals(5, dfsTraversal.size());
        assertEquals("A", dfsTraversal.get(0));
        assertEquals("C", dfsTraversal.get(1));
        assertEquals("B", dfsTraversal.get(2));
        assertEquals("E", dfsTraversal.get(3));
        assertEquals("D", dfsTraversal.get(4));

    }
    // revisar
    @Test
    public void dfsTestLimit(){
        // BFS from a vertex that does not exist
        setUp1();
        List<String> dfsTraversal = graph.dfs("A");

        assertEquals(0, graph.getVertices().size());
    }
    @Test
    public void dfsTestIntersting(){
        // BFS from a graph that is disconnected
        setUp4();
        List<String> dfsTraversal = graph.dfs("F");

        assertEquals(7, graph.getVertices().size());
        assertEquals(2, dfsTraversal.size());
        assertEquals("F", dfsTraversal.get(0));
        assertEquals("G", dfsTraversal.get(1));
    }

}
