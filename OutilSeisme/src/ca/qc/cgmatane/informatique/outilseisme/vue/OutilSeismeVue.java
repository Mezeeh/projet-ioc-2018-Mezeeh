package ca.qc.cgmatane.informatique.outilseisme.vue;

import java.util.List;

import ca.qc.cgmatane.informatique.outilseisme.action.OutilSeismeControleur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class OutilSeismeVue extends Application{
	private int hauteurFenetre = 400;
	private int largeurFenetre = 600;
	private String nomFenetre = "Outil de seisme";
	protected TabPane onglets;
	protected Tab[] onglet = new Tab[6];
	protected OutilSeismeControleur controleur;
	protected TextFlow textes;

	@Override
	public void start(Stage scenePrincipal){
		onglets = new TabPane();
		controleur = new OutilSeismeControleur(this);
		scenePrincipal.setScene(new Scene(onglets, largeurFenetre, hauteurFenetre));
		scenePrincipal.setTitle(nomFenetre);
		scenePrincipal.show();
	}

	public void afficherMenu(String texte, int page){
		onglet[page] = new Tab();
		onglet[page].setText(texte);
		onglet[page].setClosable(false);
		onglets.getTabs().add(onglet[page]);
	}

	public void afficherListe(List<String> liste, int page){
		textes = new TextFlow();

		for (String informations : liste) {
			textes.getChildren().add(new Text(informations + "\n"));
		}

		onglet[page].setContent(textes);
	}

	/*public void afficherPagination(listeNumeros){

	}*/
}
