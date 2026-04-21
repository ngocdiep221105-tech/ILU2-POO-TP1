package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	private static final String ERREUR = "Erreur: ";

	public static void main(String[] args) {
		Etal etal = new Etal();
		try {
			etal.libererEtal();
		} catch (IllegalStateException e) {
			System.out.println(ERREUR + e.getMessage());
		}

		System.out.println("\n=== Test acheter==null ===");
		try {
			Gaulois vendeur = new Gaulois("Test", 5);
			etal.occuperEtal(vendeur, "pommes", 10);
			etal.acheterProduit(5, null);
		} catch (NullPointerException e) {
			System.out.println(ERREUR + e.getMessage());
		}

		System.out.println("\n=== Test quantité <= 0 ===");
		try {
			Gaulois vendeur = new Gaulois("Test", 5);
			etal.occuperEtal(vendeur, "pommes", 10);
			etal.acheterProduit(0, vendeur);
		} catch (IllegalArgumentException e) {
			System.out.println(ERREUR + e.getMessage());
		}

		System.out.println("\n=== Test étal non occupé ===");
		try {
			Etal etalVide = new Etal();
			Gaulois client = new Gaulois("Client", 3);
			etalVide.acheterProduit(5, client);
		} catch (IllegalStateException e) {
			System.out.println(ERREUR + e.getMessage());
		}

		System.out.println("Fin du test");

	}
}
