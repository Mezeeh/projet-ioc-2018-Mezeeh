package ca.qc.cgmatane.informatique.outilseisme.vue;

import java.util.List;

import ca.qc.cgmatane.informatique.outilseisme.action.Controleur;
import ca.qc.cgmatane.informatique.outilseisme.modele.ListeString;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class OutilSeismeVue extends Application{
	private int largeurFenetre = 800;
	private int hauteurFenetre = 600;
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
		scenePrincipal.getIcons().add(new Image("file:seisme-icon.png"));
		scenePrincipal.show();
		controleur = new Controleur(this);
	}

	public void afficherMenu(String texte, int page){
		onglet[page].setText(texte);
		onglet[page].setClosable(false);
		onglet[page].setGraphic(new ImageView(new Image("Mondial" == texte ? "file:mondial-icon.png" : "file:ville-icon.png", 15, 15, true, true)));
		onglets.getTabs().add(onglet[page]);
	}

	public void afficherListe(ListeString liste, int page){
		for(int index = 0; index < liste.getTaille(); index++)
			textesFlow[page].getChildren().add(new Text(liste.rechercher(index) + "\n"));

		onglet[page].setContent(textesFlow[page]);
	}

	/*public void afficherPagination(listeNumeros){

	}*/
}
