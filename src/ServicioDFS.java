import java.util.ArrayList;
import java.util.List;

public class ServicioDFS {
    private Grafo<?> grafo;

    public ServicioDFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> dfsForest() {
        // Resolver DFS
        return new ArrayList<>();
    }
	/*
	public class DFS<T> {
    private Grafo<T> grafo;

    public DFS(Grafo<T> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> dfsForest() {
        List<Integer> resultado = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();

        Iterator<Integer> vertices = grafo.obtenerVertices();
        while (vertices.hasNext()) {
            int vertice = vertices.next();
            if (!visitados.contains(vertice)) {
                dfs(vertice, visitados, resultado);
            }
        }

        return resultado;
    }

    private void dfs(int vertice, Set<Integer> visitados, List<Integer> resultado) {
        visitados.add(vertice);
        resultado.add(vertice);

        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
        while (adyacentes.hasNext()) {
            int adyacente = adyacentes.next();
            if (!visitados.contains(adyacente)) {
                dfs(adyacente, visitados, resultado);
            }
        }
    }
}
	*/
}
