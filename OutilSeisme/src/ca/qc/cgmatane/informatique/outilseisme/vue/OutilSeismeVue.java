package ca.qc.cgmatane.informatique.outilseisme.vue;

import javafx.scene.control.Button;

import java.util.Iterator;
import java.util.List;

import javax.lang.model.element.Element;

import ca.qc.cgmatane.informatique.outilseisme.action.Controleur;
import ca.qc.cgmatane.informatique.outilseisme.modele.ListeString;
import ca.qc.cgmatane.informatique.outilseisme.modele.ListeString.VisiteurString;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private int largeurFenetre = 700;
	private int hauteurFenetre = 450;
	private String nomFenetre = "Outil de seisme";
	protected TabPane onglets;
	protected Tab[] onglet = new Tab[6];
	protected Controleur controleur;
	protected TextFlow[] textesFlow = new TextFlow[6];
	
	protected Button boutonSuivant;
	protected VBox contenuPageMondiale;

	@Override
	public void start(Stage scenePrincipal){
		onglets = new TabPane();
		for(int compteurOnglet = 0; compteurOnglet < onglet.length; compteurOnglet++)
			onglet[compteurOnglet] = new Tab();
		
		for(int compteurTextesFlow = 0; compteurTextesFlow < onglet.length; compteurTextesFlow++)
			textesFlow[compteurTextesFlow] = new TextFlow();

		contenuPageMondiale = new VBox();

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
		ListeString.VisiteurString visiteur = liste.getVisiteur();
		
		textesFlow[page].getChildren().clear();
		
		if(textesFlow[page].getChildren().size() < 20 && visiteur.estFini())
			boutonSuivant.setDisable(true);
		
		int compteur = 0;
		while (!visiteur.estFini() && compteur != 20) {
			textesFlow[page].getChildren().add(new Text(visiteur.visiterSuivant() + "\n"));
			compteur++;
		}		
		
		if(page == 0) {
			contenuPageMondiale.getChildren().add(textesFlow[page]);
			ajouterBouton();
			onglet[page].setContent(contenuPageMondiale);
		} 
		else
			onglet[page].setContent(textesFlow[page]);
	}
	
	protected void ajouterBouton() {
		boutonSuivant = new Button("Suivant");
		boutonSuivant.setOnAction(
				new EventHandler<ActionEvent>( ){
					@Override
					public void handle(ActionEvent event){
						controleur.chargerNouvellePage();
					}
				}
			);
		contenuPageMondiale.getChildren().add(boutonSuivant);
	}
}
