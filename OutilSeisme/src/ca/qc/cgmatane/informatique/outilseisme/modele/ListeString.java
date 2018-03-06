package ca.qc.cgmatane.informatique.outilseisme.modele;

public class ListeString {

	private class NoeudString{

		protected String string;
		protected NoeudString suivant;
		protected int index;

		public NoeudString(String string, int index) {
			this.string = string;
			this.index = index;
			this.suivant = null;
		}

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}

		public NoeudString getSuivant() {
			return suivant;
		}

		public void setSuivant(NoeudString suivant) {
			this.suivant = suivant;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	protected NoeudString tete;
	protected int taille = 0;

	public ListeString() {
		tete = null;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public void ajouter(String nouveauTexte){
		NoeudString nouveauNoeud = new NoeudString(nouveauTexte, taille);

		this.taille++;
		//System.out.println(taille);
		//System.out.println(nouveauTexte);

		if(null == tete){
			tete = nouveauNoeud;
		}
		else{
			NoeudString pointInsertion = tete;

			while(null != pointInsertion.getSuivant())
				pointInsertion = pointInsertion.getSuivant();

			pointInsertion.setSuivant(nouveauNoeud);
		}
	}

	public String rechercher(int indexTexteRecherche){
		NoeudString chercheur = tete;

		while(null != chercheur){
			if(chercheur.getIndex() == indexTexteRecherche)
				return chercheur.getString();

			chercheur = chercheur.getSuivant();
		}
		return null;
	}

	public ListeString.VisiteurString getVisiteur(){
		return new VisiteurString(this.tete);
	}

	public class VisiteurString implements Visitable{
		protected NoeudString noeud;

		public VisiteurString(NoeudString premierNoeud){
			this.noeud = premierNoeud;
		}

		public String visiterSuivant(){
			String texte = noeud.getString();
			noeud = noeud.getSuivant();
			return texte;
		}

		public boolean estFini(){
			return null == noeud;
		}
	}
}
