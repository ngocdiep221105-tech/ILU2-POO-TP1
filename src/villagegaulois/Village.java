package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nb_etals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nb_etals);
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

	public String afficherVillageois() throws VillageSansChefException {
		if (chef == null) {
			throw new VillageSansChefException("Le village ne peut pas exister sans chef.");
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- ").append(villageois[i].getNom()).append("\n");
			}
		}
		return chaine.toString();
	}

	private class Marche {
		private Etal[] etals;

		private Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);

		}

		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int nbEtals = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit))
					nbEtals++;
			}
			Etal[] etalP = new Etal[nbEtals];

			int index = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					etalP[index] = etals[i];
					index++;
				}
			}
			return etalP;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}

		private String afficherMarche() {
			StringBuilder sb = new StringBuilder();
			int nbEtalVide = 0;

			for (Etal e : etals) {
				if (e.isEtalOccupe()) {
					sb.append(e.getVendeur().getNom()).append(" ").append(e.getQuantite()).append(" ")
							.append(e.getProduit()).append("\n");
				} else {
					nbEtalVide++;
				}
			}

			sb.append("Il reste ").append(nbEtalVide).append(" étals non utilisés dans le marché.\n");

			return sb.toString();
		}

	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder sb = new StringBuilder();

		sb.append(vendeur.getNom()).append(" cherche un endroit pour vendre ").append(nbProduit).append(" ")
				.append(produit).append(".\n");

		int indice = marche.trouverEtalLibre();

		if (indice == -1) {
			sb.append("Il n'y a plus d'étal disponible.\n");
		} else {
			marche.utiliserEtal(indice, vendeur, produit, nbProduit);
			sb.append("Le vendeur ").append(vendeur.getNom()).append(" vend des ").append(produit)
					.append(" à l'étal n°").append(indice + 1).append(".\n");
		}

		return sb.toString();
	}

	public String rechercherVendeursProduit(String produit) {
		Etal[] etalsProduit = marche.trouverEtals(produit);
		if (etalsProduit.length == 0) {
			return "Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n";
		}

		if (etalsProduit.length == 1) {
			return "Seul le vendeur " + etalsProduit[0].getVendeur().getNom() + "  propose des " + produit
					+ " au marché.\n";
		}

		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des " + produit + " sont :\n");
		for (int i = 0; i < etalsProduit.length; i++) {
			chaine.append("- ").append(etalsProduit[i].getVendeur().getNom()).append("\n");
		}
		return chaine.toString();
	}

	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}

	public String partirVendeur(Gaulois vendeur) {
		Etal etal = rechercherEtal(vendeur);
		if (etal == null) {
			return "Le vendeur " + vendeur.getNom() + " n'a pas d'étal au marché.\n";
		}
		return etal.libererEtal();
	}

	public String afficherMarche() {
		return marche.afficherMarche();
	}

}