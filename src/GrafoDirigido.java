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
            //Si lo encuentra lo elimina
            listaAdyacente.remove(verticeId);
        }//elimina los arcos que conectan con el vertice eliminado
        for(ArrayList<Arco<T>> lista: listaAdyacente.values()){
			//Se utiliza removeIf(),para eliminar los arcos cuyos vertice destino es igual al vertice ingresado
            lista.removeIf(tmp -> tmp.getVerticeDestino() == verticeId);
        }
    }

    @Override
    public void agregarArco(int verticeOrigen, int verticeDestino, T etiqueta){
        //verifico que exista el origen
        if(!contieneVertice(verticeOrigen)){
			//Se no existe, agrega el vertice deorigen a la lista con una lista vacia de arcos
            listaAdyacente.put(verticeOrigen, new ArrayList<>());
        }
		//Creo un nuevo arco con el vertice de origen, vertice destino y la etiqueta
        Arco<T> nuevoArco = new Arco<>(verticeOrigen, verticeDestino, etiqueta);
		//Agrega el nuevo arco a la lista del vertice origen
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
		//verifica si el grafo contiene el vertice origen 
        if(contieneVertice(verticeOrigen)){
			//se obtiene la lista de arcos adyacentes al vertice origen
            ArrayList<Arco<T>> lista = listaAdyacente.get(verticeOrigen);
			//Se recorren la lista de arcos
            for(int i = 0; i < lista.size(); i++){
				//Se obtiene un arco temporal en la posicion i
                Arco<T> tmp = lista.get(i);
				//Se compara el vertice destino del arco temporal  con el destino especificado
                if(tmp.getVerticeDestino() == verticeDestino){
					//Si hay conincidencia, devolvemos ese arco
                    return  tmp;
                }
            }
        }
		//Si no hay coincidencia o no contiene el vertice origen devuelve null
        return null;
    }

    @Override
    public int cantidadVertices() {
        return listaAdyacente.size();
    }

    @Override
    public int cantidadArcos() {
        int contador =0;
		//Recorre todos los valores de la lisa 
        for(ArrayList<Arco<T>> tmp : listaAdyacente.values()){
			//se incrementa el contador con el tamaño de cada lista de arcos
            contador += tmp.size();
        }
		//Devuelve la cantidad total de arcos
        return contador;
    }

    @Override
	/*
	* Devuelve un iterador que recorre los vertices.
	* El metodo keySet() obtiene el conjntos de claves de los vertices.
	* El metodo devuelve un iterador que auida a recorrer vertice por vertice*/
    public Iterator<Integer> obtenerVertices() {
        return listaAdyacente.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		//obtenemos de la lista de arcos el vertice que ingresa por parametro
        ArrayList<Arco<T>> lista = listaAdyacente.get(verticeId);
		//Creo una lista temporal para almacenar los vertices adyacentes
        ArrayList<Integer> tmp = new ArrayList<>();
		
		//Recorro la lista de arcos
        for(int i=0; i< lista.size(); i++){
			//Se obtiene un arco en la posicion i
            Arco<T> arco = lista.get(i);
			//Se agrega el vertice destino del arco a la lista temporal
            tmp.add(arco.getVerticeDestino());
        }
		//Se devuelve un iterador sobre la lista temporal de vertice adyacente, esto permite recorrer uno por uno
        return tmp.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
		//Creo una lista temporal para almacenar los arcos
        ArrayList<Arco<T>> tmp = new ArrayList<>();
		//Se recorre todos los valores de la lista
        for(ArrayList<Arco<T>> lista : listaAdyacente.values()){
			//Se agregan todos los arcos de cada lista a la lita temporal
            tmp.addAll(lista);
        }
		//Devuelve un iterador sobre la lista temporal de arcos
        return tmp.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		//Creo una lista temporal para almacenar los arcos relacionador con el vertice qeu ingresa por parametro
        ArrayList<Arco<T>> tmp = new ArrayList<>();
		//Obtengo la lista de arcos adyacentes al vertice 
        ArrayList<Arco<T>> lista = listaAdyacente.get(verticeId);
		
		//Recorro la lista de arcos
        for(int i=0; i<lista.size(); i++){
			//obtengo un arco temporal en la posicion i
            Arco<T>arco = lista.get(i);
			//Verifico si el arco esta relacionado con el vertice ingresado como origen o destino
            if(arco.getVerticeOrigen() == verticeId || arco.getVerticeDestino() == verticeId){
				//Si es asi loa grego a la lista temporal
                tmp.add(arco);
            }
        }
		//Devuelve un iterador sobre la lista de arcos
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
