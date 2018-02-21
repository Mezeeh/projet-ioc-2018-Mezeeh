package ca.qc.cgmatane.informatique.outilseisme.vue;

import java.util.List;

import ca.qc.cgmatane.informatique.outilseisme.action.Controleur;
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
	protected Controleur controleur;
	protected TextFlow[] textesFlow = new TextFlow[6];

	@Override
	public void start(Stage scenePrincipal){
		onglets = new TabPane();
		for(int compteurOnglet = 0; compteurOnglet < onglet.length; compteurOnglet++)
			onglet[compteurOnglet] = new Tab();
		// TEMP
		for(int compteurTextesFlow = 0; compteurTextesFlow < onglet.length; compteurTextesFlow++)
			textesFlow[compteurTextesFlow] = new TextFlow();
		scenePrincipal.setScene(new Scene(onglets, largeurFenetre, hauteurFenetre));
		scenePrincipal.setTitle(nomFenetre);
		scenePrincipal.show();
		controleur = new Controleur(this);
	}

	public void afficherMenu(String texte, int page){
		onglet[page].setText(texte);
		onglet[page].setClosable(false);
		onglets.getTabs().add(onglet[page]);
	}

	public void afficherListe(List<String> liste, int page){
		for (String informations : liste) {
			textesFlow[page].getChildren().add(new Text(informations + "\n"));
		}

		onglet[page].setContent(textesFlow[page]);
	}

	/*public void afficherPagination(listeNumeros){

	}*/
}
