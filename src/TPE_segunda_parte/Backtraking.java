
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Backtraking<T> {
    private GrafoNoDirigido<T> grafoNoDirigido;
    private List<Integer> caminoOptimo = new ArrayList<>();
    private List<Arco<T>> listaTuneles = new ArrayList<>();
    private int metrosAConstruir;
    private int costoTotal;

    public void construccionDeTuneles(GrafoNoDirigido<T> grafoNoDirigido) {
        this.grafoNoDirigido = grafoNoDirigido;
        this.caminoOptimo = new ArrayList<>();
        this.listaTuneles= new ArrayList<>();
        this.metrosAConstruir = 0;
        this.costoTotal = 0;
        
        int N = grafoNoDirigido.cantidadVertices();
        HashMap<Integer, ArrayList<Arco<T>>> metrosDeTuneles = new HashMap<>();

        // Obtener las distancias en metros entre estaciones del grafo
        for(int i=0; i< N; i++){
           metrosDeTuneles.put(i, new ArrayList<>());
           for(int j=0; j<N; j++){
                Arco<T>pesoArco = grafoNoDirigido.obtenerArco(i, j);
                metrosDeTuneles.get(i).add(j, pesoArco);
           }
        }
        List<Integer> caminoActual = new ArrayList<>();
        Set<Integer>visitado = new HashSet<>();
        visitado.add(N);
        List<Integer>metrosDeTuenelUtilizados = new ArrayList<>();
        metrosDeTuenelUtilizados.add(Integer.MAX_VALUE);
        
        creacionDeTuneles(0, N, caminoActual, visitado, metrosDeTuneles,metrosDeTuenelUtilizados);
    }

    private void creacionDeTuneles(int nivel, int N, List<Integer> caminoActual, Set<Integer> visitado, HashMap<Integer, ArrayList<Arco<T>>> metrosDeTuneles, List<Integer> metrosDeTuenelUtilizados) {
        if(nivel == N){
            int metrosTunelTotal = calcularMetrosTunelTotal(caminoActual, metrosDeTuneles);
            if( metrosTunelTotal < metrosDeTuenelUtilizados.get(0)){
                metrosDeTuenelUtilizados.set(0, metrosTunelTotal);
                caminoOptimo.clear();
                caminoOptimo.addAll(caminoActual);
                listaTuneles.clear();
                listaTuneles.addAll(obtenerListaTuneles(caminoActual, metrosDeTuneles));
                metrosAConstruir = metrosTunelTotal;
                costoTotal = calcularTiempoTotal(listaTuneles);
            }
        }else{
            for(int i= 0; i< N; i++){
                if(!visitado.contains(i)){
                    visitado.add(i);
                    caminoActual.add(i);
                    
                    creacionDeTuneles(nivel+1, N, caminoActual, visitado, metrosDeTuneles, metrosDeTuenelUtilizados);
                    visitado.remove(i);
                    caminoActual.remove(caminoActual.size()-1);
                    
                }
            }
        }
    }

    
    private int calcularMetrosTunelTotal(List<Integer> caminoActual, HashMap<Integer, ArrayList<Arco<T>>> metrosDeTuneles) {
        int metrosTunelTotal =0;
        for(int i=0; i< caminoActual.size()-1; i++){
            int estacionesA = caminoActual.get(i); 
            int estacionesB = caminoActual.get(i+1);
            
            metrosTunelTotal += ((int)(metrosDeTuneles.get(estacionesA).get(estacionesB).getEtiqueta()));
        }
        return metrosTunelTotal;
    }

    private int calcularTiempoTotal(List<Arco<T>> listaTuneles) {
        int tiempoTotal = 0;
        for(Arco<T>tunel : listaTuneles){
            int longitud = (int)tunel.getEtiqueta();
            tiempoTotal += longitud;
        }
        return tiempoTotal;
    }

    private List<Arco<T>> obtenerListaTuneles(List<Integer> caminoActual, HashMap<Integer, ArrayList<Arco<T>>> metrosDeTuneles) {
         List<Arco<T>>listTuneles = new ArrayList<>();
        for(int i=0; i< caminoActual.size() -1; i++){
            int estacionesA = caminoActual.get(i);
            int estacionesB = caminoActual.get(i+1);
            listaTuneles.add(metrosDeTuneles.get(estacionesA).get(estacionesB));
        }
        return listTuneles;
    } 
}
