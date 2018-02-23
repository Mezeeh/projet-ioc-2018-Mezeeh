package ca.qc.cgmatane.informatique.outilseisme.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.qc.cgmatane.informatique.outilseisme.dao.SeismeDAO;
import ca.qc.cgmatane.informatique.outilseisme.modele.ListeDeVilles;
import ca.qc.cgmatane.informatique.outilseisme.modele.Mondial;
import ca.qc.cgmatane.informatique.outilseisme.vue.OutilSeismeVue;

public class Controleur {
	protected OutilSeismeVue vue;
	protected ApplicationContext contexte;
	protected SeismeDAO seismeDAO;
	protected ListeDeVilles listeDeVilles;
	protected Mondial mondial;

	public Controleur(OutilSeismeVue vue){
		this.vue = vue;

		contexte = new ClassPathXmlApplicationContext("ca/qc/cgmatane/informatique/outilseisme/modele/ListeVilles.xml");

		seismeDAO = new SeismeDAO();

		listeDeVilles = (ListeDeVilles)contexte.getBean("listeDeVilles");

		mondial = new Mondial();
		vue.afficherMenu(mondial.getNom(), 0);
		vue.afficherListe(seismeDAO.rechercherInformationMondial(), 0);

		for(int indexVille = 0; indexVille < listeDeVilles.getListeVilles().size(); indexVille++){
			vue.afficherMenu(listeDeVilles.getListeVilles().get(indexVille).getNom().toString(), indexVille + 1);
			vue.afficherListe(seismeDAO.rechercherInformationsVille(listeDeVilles.getListeVilles().get(indexVille).getNom().toString()), indexVille + 1);
		}
	}
}
