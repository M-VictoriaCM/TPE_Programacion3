
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Backtraking {
    private GrafoNoDirigido<Integer>grafoNoDirigido; 
    private Arco<?>arco;
    private ArrayList<Integer> caminoMinimo;

   
    public Backtraking(GrafoNoDirigido<Integer> grafoNoDirigido) {
        this.grafoNoDirigido = grafoNoDirigido;
        this.caminoMinimo = new ArrayList<>();
        this.arco = arco;
    }
    public void backtraking() {
        ArrayList<Integer>tunelActual= new ArrayList<>();
        Set<Integer>visitados = new HashSet<>();
        tunelActual.add(arco.getVerticeOrigen());
        
        buscarLaMenorCantidadDeTueneles(tunelActual, visitados);
    }
  

    private void buscarLaMenorCantidadDeTueneles(ArrayList<Integer>tunelActual, Set<Integer> visitados) {
        int estacionActual = tunelActual.get(tunelActual.size());
        
        if(estacionActual == arco.getVerticeDestino()){
            if(caminoMinimo.isEmpty() || tunelActual.size() < caminoMinimo.size()){
                caminoMinimo = new ArrayList<>(tunelActual);
            }
        }else{
            Iterator<Integer> estacionVecina = grafoNoDirigido.obtenerAdyacentes(estacionActual);
            while(estacionVecina.hasNext()){
                int siguienteEstacion = estacionVecina.next();
                if(!visitados.contains(siguienteEstacion)){
                    tunelActual.add(siguienteEstacion);
                    visitados.add(siguienteEstacion);
                    
                    buscarLaMenorCantidadDeTueneles(tunelActual, visitados);
                    
                    visitados.remove(siguienteEstacion);
                    tunelActual.remove(tunelActual.size() -1);
                }
            }
        }
    }
    
    public void imprimirCamino(){
        if(caminoMinimo.isEmpty()){
            System.out.println("No se encontro un camino valido");
        }else{
            System.out.println("E camino mas corto es: ");
            for(int estacion: caminoMinimo){
                System.out.println(estacion+" ");
            }
            System.out.println("");
        }
    }
  
}
