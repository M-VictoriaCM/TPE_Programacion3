
public class Main {
    public static void main(String[] args){
        GrafoNoDirigido<Integer>grafoNoDirigido = new GrafoNoDirigido();
        
        String path = "datasets/dataset1.txt";
	CSVReader reader = new CSVReader(path);
	reader.read();
        
        Backtraking b = new Backtraking(grafoNoDirigido);
        b.backtraking();
        b.imprimirCamino();
    }
	
}
