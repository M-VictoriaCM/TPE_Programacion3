import javafx.scene.shape.Arc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, ArrayList<Arco<T>>> listaAdyacente;

    public GrafoDirigido(){
       listaAdyacente = new HashMap<>();
    }
    @Override
    public void agregarVertice(int verticeId) {
        //verifico que el vertice exista
        if(contieneVertice(verticeId)){
            //si lo tiene lo agrego
            listaAdyacente.put(verticeId, new ArrayList<>());
        }
    }

    @Override
    public void borrarVertice(int verticeId) {
        //verifico que el vertice exista en la lista
        if(contieneVertice(verticeId)){
            //Si lo encuentro lo elimino
            listaAdyacente.remove(verticeId);
        }//elimino los arcos que conectan los vertices
        for(ArrayList<Arco<T>> lista: listaAdyacente.values()){
            lista.removeIf(tmp -> tmp.getVerticeDestino() == verticeId);
        }
    }

    @Override
    public void agregarArco(int verticeOrigen, int verticeDestino, T etiqueta){
        //verifico que exista el origen
        if(!contieneVertice(verticeOrigen)){
            listaAdyacente.put(verticeOrigen, new ArrayList<>());
        }
        Arco<T> nuevoArco = new Arco<>(verticeOrigen, verticeDestino, etiqueta);
        listaAdyacente.get(verticeOrigen).add(nuevoArco);
    }

    @Override
    public void borrarArco(int verticeOrigen, int verticeDestino) {
        //verifico si existe el vertice
        if(contieneVertice(verticeOrigen)){
            //Buscamos el arco que conecta el vertice con el vertice destino
            ArrayList<Arco<T>>lista = listaAdyacente.get(verticeOrigen);
            //borrar de el arco
            lista.removeIf(tmp -> tmp.getVerticeDestino() == verticeDestino);
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.listaAdyacente.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeOrigen, int verticeDestino) {
        //primero verifico que contenga el vertice
        if(contieneVertice(verticeOrigen)){
            ArrayList<Arco<T>> lista = listaAdyacente.get(verticeOrigen);
            //recorremos la lista
            for(int i=0; i<lista.size(); i++){
                Arco<T> tmp = lista.get(i);
                //Verifico si la lista destino igual que la que estamos buscando
                if(tmp.getVerticeDestino() == verticeDestino){
                    return true;
                }
            }
        }return false;
    }

    @Override
    public Arco<T> obtenerArco(int verticeOrigen, int verticeDestino) {
        if(contieneVertice(verticeOrigen)){
            ArrayList<Arco<T>> lista = listaAdyacente.get(verticeOrigen);
            for(int i = 0; i < lista.size(); i++){
                Arco<T> tmp = lista.get(i);
                if(tmp.getVerticeDestino() == verticeDestino){
                    return  tmp;
                }
            }
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return listaAdyacente.size();
    }

    @Override
    public int cantidadArcos() {
        int contador =0;
        for(ArrayList<Arco<T>> tmp : listaAdyacente.values()){
            contador += tmp.size();
        }
        return contador;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return listaAdyacente.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        ArrayList<Arco<T>> lista = listaAdyacente.get(verticeId);
        ArrayList<Integer> tmp = new ArrayList<>();

        for(int i=0; i< lista.size(); i++){
            Arco<T> arco = lista.get(i);
            tmp.add(arco.getVerticeDestino());
        }
        return tmp.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> tmp = new ArrayList<>();
        for(ArrayList<Arco<T>> lista : listaAdyacente.values()){
            tmp.addAll(lista);
        }
        return tmp.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        ArrayList<Arco<T>> tmp = new ArrayList<>();
        ArrayList<Arco<T>> lista = listaAdyacente.get(verticeId);

        for(int i=0; i<lista.size(); i++){
            Arco<T>arco = lista.get(i);
            if(arco.getVerticeOrigen() == verticeId || arco.getVerticeDestino() == verticeId){
                tmp.add(arco);
            }
        }
        return tmp.iterator();
    }

    @Override
    public String toString() {
        String resultado ="Vertice=  ";
        for(int v: listaAdyacente.keySet()){
            resultado += v + " ";
        }
        resultado +="\nArcos= ";
        for(int v : listaAdyacente.keySet()){
            for(Arco<T>arco : listaAdyacente.get(v)){
                resultado += "(" + v + " , "+arco.getVerticeDestino() + " , " + arco.getEtiqueta() + " ) " ;

            }
        }

        return resultado;

    }
}
