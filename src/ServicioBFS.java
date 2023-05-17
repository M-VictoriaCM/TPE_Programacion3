import java.util.ArrayList;
import java.util.List;

public class ServicioBFS {
    private Grafo<?> grafo;

    public ServicioBFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> bfsForest() {
        // Resolver BFS
        return new ArrayList<>();
    }
	/*
	public class ServicioBFS {
    private Grafo<?> grafo;

    public ServicioBFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> bfsForest() {
        List<Integer> resultado = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();

        Iterator<Integer> vertices = grafo.obtenerVertices();
        while (vertices.hasNext()) {
            int vertice = vertices.next();
            if (!visitados.contains(vertice)) {
                bfs(vertice, visitados, cola, resultado);
            }
        }

        return resultado;
    }

    private void bfs(int vertice, Set<Integer> visitados, Queue<Integer> cola, List<Integer> resultado) {
        visitados.add(vertice);
        cola.offer(vertice);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            resultado.add(actual);

            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
            while (adyacentes.hasNext()) {
                int adyacente = adyacentes.next();
                if (!visitados.contains(adyacente)) {
                    visitados.add(adyacente);
                    cola.offer(adyacente);
                }
            }
        }
    }
}
	*/
}
