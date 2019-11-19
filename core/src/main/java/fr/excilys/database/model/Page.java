package fr.excilys.database.model;

public class Page {
	private int taille;
	private int nombreLignes;

	public Page(int taille, int nombreLignes) {
		this.taille = taille;
		this.nombreLignes = nombreLignes;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getNombreLignes() {
		return nombreLignes;
	}

	public void setNombreLignes(int nombreLignes) {
		this.nombreLignes = nombreLignes;
	}
}
