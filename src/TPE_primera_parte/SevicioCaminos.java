
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ServicioCaminos {

    private Grafo<?> grafo;
    private int origen;
    private int destino;
    private int lim;
    private List<List<Integer>> caminos;

    public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
        this.caminos = new ArrayList<>();
    }

    public List<List<Integer>> caminos() {
        List<Integer> caminoActual = new ArrayList<>();
        caminoActual.add(origen);
        Set<Integer> visitados = new HashSet<>();
        visitados.add(origen);

        buscarCaminos(caminoActual, caminos, visitados, 0);
        return caminos;
    }

    private void buscarCaminos(List<Integer> caminoActual, List<List<Integer>> caminos, Set<Integer> visitados, int arcosRecorridos) {
        int verticeActual = caminoActual.get(caminoActual.size() -1);
         if(verticeActual == destino && arcosRecorridos <= lim){
            caminos.add(new ArrayList<>(caminoActual));
        }
        if(arcosRecorridos >lim){
            return;
        }
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(verticeActual);
        while(adyacentes.hasNext()){
            int siguienteVertice = adyacentes.next();

            if(!visitados.contains(siguienteVertice)){
                List<Integer>caminoNuevo = new ArrayList<>(caminoActual);
                caminoNuevo.add(siguienteVertice);
                visitados.add(siguienteVertice);
                buscarCaminos(caminoNuevo, caminos, visitados,arcosRecorridos+1);
                visitados.remove(siguienteVertice);
            }
        }    
        
    }

}
