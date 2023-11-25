package solution;

import com.example.demo.HelloApplication;
import com.example.demo.control.GraphController;
import com.example.demo.dataStructures.AdjacencyList;
import com.example.demo.dataStructures.IDataStructures.IGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {

    private HelloApplication helloApplication;
    private IGraph<Integer> graph;

    private GraphController graphController;


    public void setUp1(){
        IGraph<Integer> graph = new AdjacencyList<>();
        graphController = new GraphController(graph);
    }

    @Test
    public void testGenerateConnections() {
        setUp1();

        // Verificar que no haya camino antes de generar conexiones
        assertFalse(graphController.getGraph().bfs(1).contains(50));

        // Generar conexiones
        graphController.generateConnections();

        // Verificar que haya un camino después de generar conexiones
        assertTrue(graphController.getGraph().bfs(1).contains(50));
    }

    public void setUp2(){
        IGraph<Integer> graph = new AdjacencyList<>();
        graphController = new GraphController(graph);
        graphController.generateConnections();
    }

    @Test
    public void testGenerateConnectionsWithAlreadyConnectedGraph() {
        setUp2();
        // Verificar que haya un camino antes de generar conexiones
        assertTrue(graphController.getGraph().bfs(1).contains(50));

        // Generar conexiones
        graphController.generateConnections();

        // Verificar que no haya cambios en el grafo, ya que ya estaba conectado
        assertTrue(graphController.getGraph().bfs(1).contains(50));
    }

}
