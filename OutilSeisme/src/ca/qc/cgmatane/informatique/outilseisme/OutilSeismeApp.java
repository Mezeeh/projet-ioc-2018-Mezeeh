package ca.qc.cgmatane.informatique.outilseisme;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.qc.cgmatane.informatique.outilseisme.modele.ListeDeVilles;
import ca.qc.cgmatane.informatique.outilseisme.modele.Ville;
import ca.qc.cgmatane.informatique.outilseisme.vue.OutilSeismeVue;

public class OutilSeismeApp {

	public static void main(String[] args) {
		OutilSeismeVue outilSeismeVue = new OutilSeismeVue();
		outilSeismeVue.launch(OutilSeismeVue.class, args);
	}

}
