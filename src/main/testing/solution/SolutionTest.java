package solution;

import com.example.demo.HelloApplication;
import com.example.demo.control.GraphController;
import com.example.demo.dataStructures.AdjacencyList;
import com.example.demo.dataStructures.Graph;
import com.example.demo.dataStructures.IDataStructures.IGraph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    private HelloApplication helloApplication;
    private Graph<Integer> graph;

    private GraphController graphController;


    public void setUp1(){
        Graph<Integer> graph = new AdjacencyList<>();
        graphController = new GraphController(graph);
    }

    @Test
    public void testGenerateConnections() {
        setUp1();

        // Verificar que no haya camino antes de generar conexiones
        assertFalse(graphController.getGraph().bfs(1).contains(50));

        // Generar conexiones
        graphController.generateConnections(1, 50);

        // Verificar que haya un camino después de generar conexiones
        assertTrue(graphController.getGraph().bfs(1).contains(50));
    }

    public void setUp2(){
        Graph<Integer> graph = new AdjacencyList<>();
        graphController = new GraphController(graph);
        graphController.generateConnections(1, 50);
    }

    @Test
    public void testGenerateConnectionsWithAlreadyConnectedGraph() {
        setUp2();
        // Verificar que haya un camino antes de generar conexiones
        assertTrue(graphController.getGraph().bfs(1).contains(50));

        // Generar conexiones
        graphController.generateConnections(1, 50);

        // Verificar que no haya cambios en el grafo, ya que ya estaba conectado
        assertTrue(graphController.getGraph().bfs(1).contains(50));
    }
    @Test
    public void testGenerateConnectionsInvalid(){
        setUp2();

        graphController.generateConnections(100, 200);
        // Verificar que no haya cambios en el grafo, ya que ya estaba conectado
        assertTrue(graphController.getGraph().bfs(1).contains(50));

    }

    public void setUp3(){
        graphController = new GraphController();
        graph = new AdjacencyList<>();
        graphController.setGraph(graph);
    }
   @Test
    public void testMinimumPath(){
        setUp3();
       for (int i = 1; i <= 5; i++) {
           graphController.getGraph().addVertex(i);
       }
       graphController.getGraph().addEdge(1, 2, 1);
       graphController.getGraph().addEdge(1, 3, 4);
       graphController.getGraph().addEdge(2, 3, 2);
       graphController.getGraph().addEdge(2, 4, 5);
       graphController.getGraph().addEdge(3, 4, 1);
       graphController.getGraph().addEdge(4, 5, 3);


       // Ejecutar el método minimumPath
       List<Integer> path = graphController.minimumPath(1, 5);

       // Verificar que el resultado no sea nulo
       assertNotNull(path);

       // Verificar que el resultado sea el camino mínimo esperado
       List<Integer> expectedPath = Arrays.asList(1, 2, 3, 4, 5);  // Camino mínimo 1 -> 2 -> 3 -> 4 -> 5
       assertEquals(expectedPath, path);

   }
    @Test
    public void testMinimumPathWhenSourceAndDestinationAreTheSame() {
        setUp3();
        graphController.getGraph().addVertex(1);

        List<Integer> path = graphController.minimumPath(1, 1);

        assertNotNull(path);
        assertEquals(List.of(1), path);
    }

    @Test
    public void testMinimumPathWhenNoPathExists() {
        setUp3();
        graphController.getGraph().addVertex(1);
        graphController.getGraph().addVertex(2);

        List<Integer> path = graphController.minimumPath(1, 2);

        assertNull(path);
    }

}
