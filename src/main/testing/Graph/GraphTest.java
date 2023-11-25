package Graph;


import com.example.demo.dataStructures.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        graph.addEdge("A", "B", 6);
        graph.addEdge("A", "C", 5);
        graph.addEdge("B", "D", 7);
        graph.addEdge("B", "E", 8);
        graph.addEdge("F", "G", 9);
    }

    public void setUp5(){
        graph = new AdjacencyList<>();
        //graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
    }

    public void setUp6(){
        graph = new AdjacencyList<>();
        //graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B", 8);
        graph.addEdge("A", "C", 5);
        graph.addEdge("A", "D", 3);
    }

    public void setUp7(){
        graph = new AdjacencyList<>();
        //graph = new AdjacencyMatrix<>();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B", 8);
        graph.addEdge("A", "C", 5);
        graph.addEdge("A", "D", 3);
        graph.addEdge("B", "D", 1);
        graph.addEdge("B", "F", 5);
        graph.addEdge("C", "E", 1);
        graph.addEdge("D", "E", 4);
        graph.addEdge("D", "C", 1);
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
    public void getNeighborsTestStandard(){
        //Search neighbors of a vertex with edges
        setUp6();

        List<String> neighbors = graph.getNeighbors("A");

        assertEquals(3, neighbors.size());
        assertTrue(neighbors.contains("B"));
        assertTrue(neighbors.contains("C"));
        assertTrue(neighbors.contains("D"));
    }

    @Test
    public void getNeighborsTestLimit(){
        //Search neighbors when the graph just has one vertex
        setUp5();
        List<String> neighbors = graph.getNeighbors("A");

        assertTrue(neighbors.isEmpty());
    }

    @Test
    public void getNeighborsTestInteresting(){
        //Search neighbors of a vertex without edges
        setUp3();

        List<String> neighbors = graph.getNeighbors("C");

        assertEquals(0, neighbors.size());
        assertFalse(neighbors.contains("B"));
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

    @Test
    public void bfsTestLimit(){
        // BFS from a vertex that does not exist
        setUp1();
        List<String> bfsTraversal = graph.bfs("A");

        assertNull(bfsTraversal);
    }
    @Test
    public void bfsTestInteresting(){
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
        //DFS from a graph with vertices
        setUp4();

        List<String> dfsTraversal = graph.dfs("A");

        assertEquals(5, dfsTraversal.size());
        assertEquals("A", dfsTraversal.get(0));
        assertEquals("C", dfsTraversal.get(1));
        assertEquals("B", dfsTraversal.get(2));
        assertEquals("E", dfsTraversal.get(3));
        assertEquals("D", dfsTraversal.get(4));

    }
    @Test
    public void dfsTestLimit(){
        // DFS from a vertex that does not exist
        setUp1();
        List<String> dfsTraversal = graph.dfs("A");

        assertEquals(0, graph.getVertices().size());
        assertTrue(dfsTraversal.isEmpty());
    }
    @Test
    public void dfsTestInteresting(){
        // DFS from a graph that is disconnected
        setUp4();
        List<String> dfsTraversal = graph.dfs("F");

        assertEquals(7, graph.getVertices().size());
        assertEquals(2, dfsTraversal.size());
        assertEquals("F", dfsTraversal.get(0));
        assertEquals("G", dfsTraversal.get(1));
    }

    @Test
    public void dijkstraTestStandard(){
        //Dijkstra from a graph with vertices and is disconnected
        setUp3();
        Map<String, Pair<Integer, String>> distances = graph.dijkstra("A");

        assertEquals(0, distances.get("A").getFirst());
        assertEquals(5, distances.get("B").getFirst());
        assertEquals(13, distances.get("C").getFirst());

    }

    @Test
    public void dijkstraTestLimit(){
        // Dijkstra from graph with one node
        setUp5();
        Map<String, Pair<Integer, String>> distances = graph.dijkstra("A");

        assertEquals(1, graph.getVertices().size());
        assertEquals(0, distances.get("A").getFirst());
    }

    @Test
    public void dijkstraTestInteresting(){
        // Dijkstra from a graph with vertices that is disconnected and the vertex of start
        // don't connected with the rest of the graph in that direction
        setUp4();
        Map<String, Pair<Integer, String>> distances = graph.dijkstra("C");

        assertEquals(7, distances.size());
        assertEquals(0, distances.get("C").getFirst());
        assertFalse(distances.get("A").getFirst()==5);
    }

    @Test
    public void floydWarshallTestStandard(){
        //Floyd-Warshall from a graph with vertices and edges
        setUp3();
        Map<String, Map<String, Integer>> distances = graph.floydWarshall();

        assertEquals(0, distances.get("A").get("A"));
        assertEquals(5, distances.get("A").get("B"));
        assertEquals(13, distances.get("A").get("C"));
    }

    @Test
    public void floydWarshallTestLimit(){
        //Floyd-Warshall from graph with one node
        setUp5();
        Map<String, Map<String, Integer>> distances = graph.floydWarshall();

        assertEquals(1, graph.getVertices().size());
        assertEquals(0, distances.get("A").get("A"));
    }

    @Test
    public void floydWarshallTestInteresting(){
        //Floyd-Warshall from a graph with vertices that is disconnected and the vertex of start
        //don't connected with the rest of the graph in that direction
        setUp4();
        Map<String, Map<String, Integer>> distances = graph.floydWarshall();

        assertEquals(7, distances.size());
        assertEquals(0, distances.get("C").get("C"));
        assertFalse(distances.get("C").get("A")==5);
    }

    @Test
    public void primTestStandard(){
        //Prim from a graph with vertices and edges
        setUp3();
        Map<String, String> mst = graph.primMST();

        assertEquals(3, mst.size());
        assertEquals("A", mst.get("B"));
        assertEquals("B", mst.get("C"));
    }

    @Test
    public void primTestLimit(){
        //Prim from a graph with one vertex
        setUp5();
        Map<String, String> mst = graph.primMST();

        assertEquals(1, graph.getVertices().size());
        assertEquals(1, mst.size());
    }

    @Test
    public void primTestInteresting(){
        //Prim from a graph with a cyclic path
        setUp7();
        Map<String, String> mst = graph.primMST();

        assertEquals(6, mst.size());
        assertEquals("A", mst.get("B"));
        assertEquals("D", mst.get("C"));
        assertEquals("A", mst.get("D"));
        assertEquals("C", mst.get("E"));
        assertEquals("B", mst.get("F"));
    }

    @Test
    public void kruskalTestStandard(){
        //Kruskal from a graph with vertices and edges
        setUp3();
        List<Edge<String>> mst = graph.kruskalMST();

        assertEquals(2, mst.size());
        assertEquals("A", mst.get(0).getSource().getValue());
        assertEquals("B", mst.get(0).getNode().getValue());
        assertEquals("B", mst.get(1).getSource().getValue());
        assertEquals("C", mst.get(1).getNode().getValue());
    }

    @Test
    public void kruskalTestLimit(){
        //Kruskal from a graph with one vertex
        setUp5();
        List<Edge<String>> mst = graph.kruskalMST();

        assertEquals(1, graph.getVertices().size());
        assertEquals(0, mst.size());
    }

    @Test
    public void kruskalTestInteresting(){
        //Kruskal from a graph with a cyclic path
        setUp7();
        List<Edge<String>> mst = graph.kruskalMST();

        assertEquals(5, mst.size());
        assertEquals("B", mst.get(0).getSource().getValue());
        assertEquals("D", mst.get(0).getNode().getValue());
        assertEquals("C", mst.get(1).getSource().getValue());
        assertEquals("E", mst.get(1).getNode().getValue());
        assertEquals("D", mst.get(2).getSource().getValue());
        assertEquals("C", mst.get(2).getNode().getValue());
        assertEquals("A", mst.get(3).getSource().getValue());
        assertEquals("D", mst.get(3).getNode().getValue());
        assertEquals("B", mst.get(4).getSource().getValue());
        assertEquals("F", mst.get(4).getNode().getValue());
    }
}
