import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Greedy {
    
    private Timer time;

    public void greedy(GrafoNoDirigido<Integer> grafoNoDirigido) {
       Set<Integer>estaciones = new HashSet<>();
       Iterator<Integer> estacionSiguiente = grafoNoDirigido.obtenerVertices();
       
       while(estacionSiguiente.hasNext()){
           estaciones.add(estacionSiguiente.next());
       }
       Set<Integer>conectadas = new HashSet<>();
       //Obtenemos la primera estacion
      
       int estacionActual = estaciones.iterator().next();
       conectadas.add(estacionActual);
       mostrarSolucion(estacionActual);
       
       while(conectadas.size() < estaciones.size()){
           int estacionCercana = encontrarEstacionCercana(grafoNoDirigido, conectadas);
           conectadas.add(estacionCercana);
           estacionActual = estacionCercana;
           mostrarSolucion(estacionActual);
       }
       
    }

    private int encontrarEstacionCercana(GrafoNoDirigido<Integer> grafoNoDirigido, Set<Integer> conectadas) {
        int estacionCercana = -1;
        int minimaDistancia = Integer.MAX_VALUE;
        
        for(int estacion: conectadas){
            Iterator<Integer>adyacentes = grafoNoDirigido.obtenerAdyacentes(estacion);
            while(adyacentes.hasNext()){
                int vecina = adyacentes.next();
                if(!conectadas.contains(vecina)){
                    Iterator<Integer> distancia = (Iterator<Integer>) grafoNoDirigido.obtenerArco(estacion, vecina);
                    while(distancia.hasNext()){
                        int d= distancia.next();
                        if(d < minimaDistancia){
                            minimaDistancia= d;
                            estacionCercana = vecina;
                        }
                    }
                    
                }
            }
        }
        return estacionCercana;
    }

    private void mostrarSolucion(int estacionActual) {
        System.out.println("Estacion conectada: "+ estacionActual);
    }

}
