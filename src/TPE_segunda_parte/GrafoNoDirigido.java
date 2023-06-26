
import java.util.ArrayList;
import java.util.Iterator;

public class GrafoNoDirigido<T> extends GrafoDirigido<T> {

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        super.agregarArco(verticeId1, verticeId2, etiqueta);
        super.agregarArco(verticeId2, verticeId1, etiqueta);
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        super.borrarArco(verticeId1, verticeId2);
        super.borrarArco(verticeId2, verticeId1);
    }

    @Override
    public int cantidadArcos() {
        return super.cantidadArcos() / 2;
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        ArrayList<Arco<T>> lista = (ArrayList<Arco<T>>) super.obtenerAdyacentes(verticeId);
        ArrayList<Integer> adyacente = new ArrayList<>();
        for (Arco<T> arco : lista)
        {
            if (arco.getVerticeOrigen() == verticeId)
            {
                adyacente.add(arco.getVerticeDestino());
            } else if (arco.getVerticeDestino() == verticeId)
            {
                adyacente.add(arco.getVerticeOrigen());
            }
        }
        return adyacente.iterator();
    }

    @Override
    public Arco<T> obtenerArco(int verticeOrigen, int verticeDestino) {
        Arco<T> arcoDirigido = super.obtenerArco(verticeOrigen, verticeDestino);
        if (arcoDirigido != null){
            return arcoDirigido;
        } else{
            //Si el arco no fue encontrado, intentamos buscar el arco inverso
            Arco<T> arcoInverso = super.obtenerArco(verticeOrigen, verticeDestino);
            if (arcoInverso != null){
                //se invierte los vertices de origen y destino
                int nuevoVerticeOrigen = arcoInverso.getVerticeDestino();
                int nuevoVerticeDestino = arcoInverso.getVerticeOrigen();
                return new Arco<>(nuevoVerticeOrigen, nuevoVerticeDestino, arcoInverso.getEtiqueta());
            } else{//No se encontró ningun arco entre los vertices
                return null;
            }

        }
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        ArrayList<Arco<T>> arcos = new ArrayList<>();

        //Obtengo los arcos salientes
        Iterator<Arco<T>> arcosSaliente = super.obtenerArcos(verticeId);
        while (arcosSaliente.hasNext()){
            Arco<T> arco = arcosSaliente.next();
            arcos.add(arco);
        }
        //Obtengo los arco entrantes 
        Iterator<Arco<T>> arcoEntrante = super.obtenerArcos(verticeId);
        while (arcoEntrante.hasNext()){
            Arco<T> arco = arcoEntrante.next();
            arcos.add(arco);
            //Si no lo contiene lo agrego
            if (!arcos.contains(arco)){
                arcos.add(arco);
            }
        }
        return arcos.iterator();
    }
    
    @Override
    public Iterator<Arco<T>> obtenerArcos(){
        ArrayList<Arco<T>> arcos = new ArrayList<>();
        //Obtenemos todos los vertices del grafo
        Iterator<Integer>vertices = super.obtenerVertices();
        //Se itera sobre todos los vertices y obtner sus arcos
        while(vertices.hasNext()){
            int verticeId = vertices.next();
            Iterator<Arco<T>> arcosVertices = this.obtenerArcos(verticeId);
            while(arcosVertices.hasNext()){
                Arco<T>arco = arcosVertices.next();
                //si el arco no esta en la lista, lo agrego
                if(!arcos.contains(arco)){
                    arcos.add(arco);
                }
            }
        }
        return arcos.iterator();
    }

}
