import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        GrafoDirigido<String> grafo = new GrafoDirigido<>();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);

        grafo.agregarArco(1,2,"b");
        grafo.agregarArco(1,3,"a");
        grafo.agregarArco(2,4,"c");
        grafo.agregarArco(3,2,"d");
        grafo.agregarArco(4,3,"e");

        System.out.println(grafo.toString());
        //grafo.borrarArco(1,2);
        //System.out.println(grafo.toString());
        //System.out.println(grafo.contieneVertice(2));
        //grafo.borrarVertice(4);
        //System.out.println(grafo.toString());

        System.out.println("Pertenece a la etiqueta: "+grafo.obtenerArco(1,3).getEtiqueta());
        System.out.println("cantidad de arcos: "+grafo.cantidadArcos());
        System.out.println("cantidad de vertices: "+grafo.cantidadVertices());
        System.out.println("Existe vertice? "+grafo.existeArco(2,4));


        Iterator<Integer> adyacente= grafo.obtenerAdyacentes(2);
        System.out.print("Los adyacentes son: ");
        while(adyacente.hasNext()){
            int adyacentes= adyacente.next();
            System.out.print(adyacentes +" ");
        }

    }
}
