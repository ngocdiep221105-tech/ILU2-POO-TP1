package histoire;
import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) { 
		Etal etal = new Etal(); 
		 try {
	            etal.libererEtal();
	        } catch (IllegalStateException e) {
	            System.out.println("Erreur: " + e.getMessage());
	        }

	    System.out.println("\n=== Test quantité <= 0 ==="); 
	    try {
            Gaulois vendeur = new Gaulois("Test", 5);
            etal.occuperEtal(vendeur, "pommes", 10);
            etal.acheterProduit(0, vendeur);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur: " + e.getMessage());
        }

        System.out.println("\n=== Test acheteur null ===");
        System.out.println(etal.acheterProduit(5, null));

        System.out.println("\n=== Test étal non occupé ===");
        try {
            Etal etalVide = new Etal();
            Gaulois client = new Gaulois("Client", 3);
            etalVide.acheterProduit(5, client);
        } catch (IllegalStateException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
	        
		System.out.println("Fin du test"); 
		
		
		}
}
