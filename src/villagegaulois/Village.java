package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;


public class Village extends Etal{
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nb_etals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = ;
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	
	private static class Marche{
		private Etal[] etals;
		private Marche(int nb_etal) {
			etals=new Etal[nb_etal];
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			
		}
		
		private int trouverEtalLibre() {
			for(int i=0; i<etals.length; i++) {
				if(etals[i].isEtalOccupe()==false) {
					return i;
				}
			}
			return -1;
		}
		
		
		private Etal[] trouverEtals(String produit) {
			int nb_etals=0;
			Etal[] etal_p = null;
			for(int i=0; i<etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					etal_p[nb_etals]=etals[i];
					nb_etals++;
				}
			}
			return etal_p;
		}
		
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if(etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			String text="";
			int nbEtalVide=trouverEtalLibre();
			for (int i = 0; i < etals.length; i++) {
				text+=etals[i].afficherEtal();
				text+='\n';
			}
			return text+'\n'+"Il reste "+nbEtalVide+ " étals non utilisés dans le marché.\n”;
		}
		

	}
}