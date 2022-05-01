public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

       
       // Test Initialisation Tuiles
       
        TuileTerrain[] TT = TuileTerrain.initTuileTerrains();
        
        for(int i=0; i<40;i++)
        {
            System.out.println(TT[i]);    
        }
    }
}
