
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, ArrayList<Arco<T>>> listaAdyacente;

    public GrafoDirigido(){
       listaAdyacente = new HashMap<>();
    }
    @Override
    /* Notacion BIG O: O(1)
    * Dado que el contienVertice tambien y el metodo put tambien es de una complejidad
    * de tiempo constante.
    * */
    public void agregarVertice(int verticeId) {
        //verifico que el vertice exista
        if(!contieneVertice(verticeId)){
            //si lo tiene lo agrego
            listaAdyacente.put(verticeId, new ArrayList<>());
        }
    }

    @Override
    /* Notacion BIG O: O(n *m)
    * Todo depende del numero de vertices y el promedio de arcos pero a grandes razgos es O(n *m)
    * Primero tenemos la verificacion del vertice la cual tiene una complejidad O(1)
    * La eliminacion del vertice(remove), tambien tiene una complejidad de O(1)
    * En el bucle for existe una complejidad de O(n), porque todo depende la lista de adyacencia
    * En la operacion removeIf existe una complejidad de O(m), ya qye necesita verificar  todos
    * los arcos en la lista de adyacencia
    * */
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
    /* Notacion BIG O: O(1)
        * Tiene una complejidad de O(1), por el metofo get y add, pero podria tener una complejidad de O(n).
        * Si tiene muchos arcos, al tener el get y el add, el gasto seria diferente
        * */
    @Override
    public void agregarArco(int verticeOrigen, int verticeDestino, T etiqueta){
        //verifico que exista el vertice origen
        //Y si no lo tengo lo agrego;
        agregarVertice(verticeOrigen);
        //En caso que exista
        //Creo un nuevo arco con el vertice de origen, vertice destino y la etiqueta
        Arco<T> nuevoArco = new Arco<>(verticeOrigen, verticeDestino, etiqueta);
        //Agrega el nuevo arco a la lista
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
    /* Notacion BIG O: O(1) */
    @Override
    public boolean contieneVertice(int verticeId) {
        return this.listaAdyacente.containsKey(verticeId);
    }

    /* Notacion BIG O: O(n)
    * Dado que tenemos un recorrido, todo depende del tamaño de la lista adyacente.
	* Contiene vertice tiene una complejidad de O(1);
	* El recorrido de las lista adyacentes es del tamaño O(n).
	* La validacion de la igualdad de los vertices es O(1).
	* Todos estos datos dan que la complejidad del metodo es O(n)
    * */
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
    /* Notacion BIG O: O(n)
     * - La verificacion si tiene el vertice es O(1)
	 * - El recorrido de la lista es O(n)
	 * - La validadcion de la igualdad del vertice destino de cada arco es O(1)
	 * Todos estos datos dan que la complejidad del metodo es O(n).
	 */
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
    /* Notacion BIG O: O(1) */
    @Override
    public int cantidadVertices() {
        return listaAdyacente.size();
    }

    /* Notacion BIG O: O(n)
    * - tenemos un recorrido , lo que implica en el peor de los casos recorrer toda la 
	* lista de arcos adyacentes a cada vertice, la complejidad es O(n) 
	* - Incrementar el contador, tiene una complejidad de  O(1).
	* -Todos estos datos, da una complejidad de O(n)
    * */
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
	/*Notacion BIG O: O(n).
	* Dado que el KeySet tiene una complejidad O(1), pero al tener el iterator la complejidad aumenta ya que esto implica
	* que se tenga que iterar en las claves.
	*
	* Devuelve un iterador que recorre los vertices.
	* El metodo keySet() obtiene el conjntos de claves de los vertices.
	* El metodo devuelve un iterador que auida a recorrer vertice por vertice*/
    public Iterator<Integer> obtenerVertices() {
        return listaAdyacente.keySet().iterator();
    }
    /* Notacion BIG O: O(n)
    * - Obtener la lista de adyacentes tiene una complejidad de O(1)
	* - Crear y recorrer la lista de arcos, tiene una complejidad de O(n)
	* - Agregar a la lista temporal, tiene una complejidad de  O(1)
	* - Devolver el iterador tiene una complejidad de  O(1)
	* -Todos estos datos dan una complejidad de O(n)
    * */
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
    /* Notacion BIG O: O(n)
    * -La creacion de la lista temporal O(1)
	* -Recorrer todos los valores de las lista de adyacentes. En el peor de los casos recorrera todas las listas, esto tiene una complejidad de O(n)
	* -Agregar todsos los arcos a la lista temporal: addAll agrega todos los elementos de la lista. En el peor de los casos la complejidad seria de O(m)
	* -Devolver la lista en un iterador tiene una complejidad de O(1).
	* Todos estos datos dan una complejidad de O(n + m)
    * */
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
    /* Notacion BIG O: O(n)
    * -crear la lista temporal es O(1)
	* - obtener la lista de los adyacentes es O(1)
	* - Recorrer la lista de arcos, tiene una complejidad de O(n)
	* - Verificar si cada arco esta relacionado con el vertice, tiene una complejidad de O(1)
	* - Agregar a la lista de temporal, tiene una complejidad  de  O(1)
	* - devolver la listaen un iterador, tiene una complejidad de O(1).
	* Todos estos datos dan una complejidad de O(n)
    * */
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
	/* Notacion BIG O: O(n+m)
	* -Iterar la lista de las claves, en el peor de los casos es O(n)
	* - concatenar la cedena de vertices es tiene una complejidad de O(1)
	* -iterar las claves de la lista en el peor de los casos es O(n)
	* -Iterar sobre cada vertice es O(m)
	* concatenar los resultados es O(1)
	* -Todos estos resultados dan como resultado O(n+m)
	*/

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
