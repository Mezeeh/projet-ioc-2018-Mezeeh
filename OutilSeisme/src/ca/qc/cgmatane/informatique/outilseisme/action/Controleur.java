package ca.qc.cgmatane.informatique.outilseisme.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.qc.cgmatane.informatique.outilseisme.dao.SeismeDAO;
import ca.qc.cgmatane.informatique.outilseisme.modele.ListeDeVilles;
import ca.qc.cgmatane.informatique.outilseisme.modele.ListeString;
import ca.qc.cgmatane.informatique.outilseisme.modele.Mondial;
import ca.qc.cgmatane.informatique.outilseisme.modele.Ville;
import ca.qc.cgmatane.informatique.outilseisme.vue.OutilSeismeVue;

public class Controleur {
	protected OutilSeismeVue vue;
	protected ApplicationContext contexte;
	protected SeismeDAO seismeDAO;
	protected ListeDeVilles listeDeVilles;
	protected Mondial mondial;
	protected List<Ville> tableauVille ;
	protected ListeString informationsMondial;

	public Controleur(OutilSeismeVue vue){
		this.vue = vue;

		contexte = new ClassPathXmlApplicationContext("ca/qc/cgmatane/informatique/outilseisme/modele/ListeVilles.xml");

		seismeDAO = new SeismeDAO();

		listeDeVilles = (ListeDeVilles)contexte.getBean("listeDeVilles");

		mondial = new Mondial();
		vue.afficherMenu(mondial.getNom(), 0);
		informationsMondial = seismeDAO.rechercherInformationMondial();
		vue.afficherListe(informationsMondial, 0);
		
		tableauVille = listeDeVilles.getListeVilles();

		for(int indexVille = 0; indexVille < tableauVille.size(); indexVille++){
			vue.afficherMenu(tableauVille.get(indexVille).getNom().toString(), indexVille + 1);
			vue.afficherListe(seismeDAO.rechercherInformationsVille(tableauVille.get(indexVille).getNom().toString()), indexVille + 1);
		}
	}

	public void chargerNouvellePage() {
		vue.afficherListe(informationsMondial, 0);
	}
}
