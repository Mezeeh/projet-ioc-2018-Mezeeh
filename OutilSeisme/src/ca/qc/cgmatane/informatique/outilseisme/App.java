package ca.qc.cgmatane.informatique.outilseisme;

import ca.qc.cgmatane.informatique.outilseisme.vue.OutilSeismeVue;

public class App {

	public static void main(String[] args) {
		OutilSeismeVue outilSeismeVue = new OutilSeismeVue();
		outilSeismeVue.launch(OutilSeismeVue.class, args);
	}
}
