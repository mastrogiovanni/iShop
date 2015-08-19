package net.mastro.ishop.view;

import com.vaadin.ui.ComboBox;

public class ComboTypes extends ComboBox {

	private static final long serialVersionUID = 1L;
	
	public ComboTypes() {
		super();
		loadValues();
	}
	
	public ComboTypes(String caption) {
		super(caption);
		loadValues();
	}
	
	private void loadValues() {
		addItem("chilo");
		addItem("etto");
		addItem("grammo");
		addItem("pezzo");
		addItem("litro");
		addItem("sacco");
		addItem("filone");
		addItem("pagnotta");
		addItem("confezione");
	}

}
