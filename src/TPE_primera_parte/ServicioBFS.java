import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ServicioBFS {
    private Grafo<?> grafo;
    private Set<Integer>visitado;
    private List<Integer> resultado;

    public ServicioBFS(Grafo<?> grafo) {
        this.grafo = grafo;
        this.visitado= new HashSet<>();
        this.resultado = new ArrayList<>();
    }

    public List<Integer> bfsForest() {
        Iterator<Integer> vertices = grafo.obtenerVertices();
        while(vertices.hasNext()){
            int vertice = vertices.next();
            if(!visitado.contains(vertice)){
                bfs(vertice);
            }
        }

        return resultado;
    }

    private void bfs(int vertice) {
        LinkedList<Integer> cola = new LinkedList<>();
        cola.add(vertice);
        visitado.add(vertice);

        while(!cola.isEmpty()) {
            int grafoActual = cola.removeFirst();
            resultado.add(grafoActual);

            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(grafoActual);
            while(adyacentes.hasNext()){
                int adyacente = adyacentes.next();
                if(!visitado.contains(adyacente)){
                    cola.add(adyacente);
                    visitado.add(adyacente);
                }
            }
        }

    }

}