package ca.qc.cgmatane.informatique.outilseisme.vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OutilSeismeVue extends Application{
	private int hauteurFenetre = 400;
	private int largeurFenetre = 600;
	private String nomFenetre = "Outil de seisme";

	private Text text;
	private Tab onglet;
	private TabPane panneauOnglets;

	@Override
	public void start(Stage scenePrincipal){
		panneauOnglets = new TabPane();
		scenePrincipal.setScene(new Scene(panneauOnglets, largeurFenetre, hauteurFenetre));
		scenePrincipal.setTitle(nomFenetre);
		creerOnglet(2);
		scenePrincipal.show();
	}

	public void creerOnglet(int nombreOnglet){
		for(int numeroOnglet = 0; numeroOnglet < nombreOnglet; numeroOnglet++){
			onglet = new Tab();
			onglet.setText("Onglet numero : " + (numeroOnglet + 1));
			//onglet.setContent(new Text("blablabla"));
			genererInformationsDansOnglet(3);
			onglet.setClosable(false);
			panneauOnglets.getTabs().add(onglet);
		}
	}

	public void genererInformationsDansOnglet(int nombreInformation){
		VBox pageOnglet = new VBox();

		for(int numeroOnglet = 0; numeroOnglet < nombreInformation; numeroOnglet++){
			text = new Text();
			text.setText("blablabla " + (numeroOnglet + 1) + "\n");
			pageOnglet.getChildren().add(text);
			onglet.setContent(pageOnglet);
		}
	}
}
