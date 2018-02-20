package ca.qc.cgmatane.informatique.outilseisme.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.qc.cgmatane.informatique.outilseisme.modele.ListeDeVilles;
import ca.qc.cgmatane.informatique.outilseisme.modele.Mondial;
import ca.qc.cgmatane.informatique.outilseisme.modele.Ville;
import ca.qc.cgmatane.informatique.outilseisme.vue.OutilSeismeVue;

public class OutilSeismeControleur {
	protected OutilSeismeVue vue;

	public OutilSeismeControleur(OutilSeismeVue vue){
		this.vue = vue;

		ApplicationContext contexte = new ClassPathXmlApplicationContext("ca/qc/cgmatane/informatique/outilseisme/modele/ListeVilles.xml");

		ListeDeVilles listeDeVilles;
		listeDeVilles = (ListeDeVilles)contexte.getBean("listeDeVilles");

		//for(Ville ville : listeDeVilles.getListeVilles())
			//System.out.println("Nom : " + ville.getNom());

		Mondial mondial = new Mondial();
		vue.afficherMenu(mondial.getNom(), 0);
		for(int indexVille = 0; indexVille < listeDeVilles.getListeVilles().size(); indexVille++){
			vue.afficherMenu(listeDeVilles.getListeVilles().get(indexVille).getNom().toString(), indexVille + 1);
		}

		/*
		vue.afficherMenu("Mondial", 0);
		vue.afficherMenu("Brazil", 1);
		vue.afficherMenu("Réunion", 2);
		vue.afficherMenu("Haiti", 3);

		List<String> listeMondial = new ArrayList<String>();
		listeMondial.add("Ligne 1");
		listeMondial.add("Ligne 2");
		listeMondial.add("Ligne 3");
		vue.afficherListe(listeMondial, 0);

		List<String> listeBrazil = new ArrayList<String>();
		listeBrazil.add("Ligne 1");
		listeBrazil.add("Ligne 2");
		listeBrazil.add("Ligne 3");
		vue.afficherListe(listeBrazil, 1);

		List<String> listeReunion = new ArrayList<String>();
		listeReunion.add("Ligne 1");
		listeReunion.add("Ligne 2");
		listeReunion.add("Ligne 3");
		vue.afficherListe(listeReunion, 2);

		List<String> listeHaiti = new ArrayList<String>();
		listeHaiti.add("Ligne 1");
		listeHaiti.add("Ligne 2");
		listeHaiti.add("Ligne 3");
		vue.afficherListe(listeHaiti, 3);
		*/
	}
}
