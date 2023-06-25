import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ServicioDFS {
    private Grafo<?> grafo;
    private Set<Integer>visitado;
    private List<Integer> resultado;

    public ServicioDFS(Grafo<?> grafo) {
        this.grafo = grafo;
        this.visitado= new HashSet<>();
        this.resultado = new ArrayList<>();
    }

    public List<Integer> dfsForest() {
        Iterator<Integer>vertices = grafo.obtenerVertices();
        while(vertices.hasNext()){
            int vertice = vertices.next();
            if(!visitado.contains(vertice)){
                dfs(vertice);
            }
        }
        return resultado;
    }

    private void dfs(int vertice) {
        visitado.add(vertice);
        resultado.add(vertice);
        Iterator<Integer>adyacentes = grafo.obtenerAdyacentes(vertice);
        while(adyacentes.hasNext()){
            int  adyacente = adyacentes.next();

            if(!visitado.contains(adyacente)){
                dfs(adyacente);
            }
        }
    }
}