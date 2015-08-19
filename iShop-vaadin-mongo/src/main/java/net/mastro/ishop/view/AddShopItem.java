package net.mastro.ishop.view;

import net.mastro.ishop.Dao;
import net.mastro.ishop.model.ShopItem;
import net.mastro.ishop.model.User;
import net.mastro.ishop.service.SuggestBrandName;
import net.mastro.ishop.service.SuggestFactory;
import net.mastro.ishop.service.SuggestShopItemName;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class AddShopItem extends Window implements Button.ClickListener {

	private static final long serialVersionUID = 1L;
	
	private Button cancel;
	private Button add;
	
	private TextField name;
	private TextField brand;
	private TextField quantity;
	private ComboBox type;
	private TextField note;
	
	private User user;
	
	private ShopItemTable table;
	
	private SuggestFactory suggestName;
	private SuggestFactory suggestBrand;

	public AddShopItem(User user, ShopItemTable table) {
		super();
		this.user = user;
		this.table = table;
		setWidth("300px");
		setHeight("500px");
		center();
		buildMainLayout();
	}

	@AutoGenerated
	private void buildMainLayout() {
				
		// the main layout and components will be created here
		FormLayout mainLayout = new FormLayout();
		mainLayout.setSizeFull();
		
		suggestName = new SuggestFactory();
		suggestBrand = new SuggestFactory();
		
		mainLayout.setMargin(true);
		
		name = new TextField("Name");
		name.setImmediate(true);
		
		brand = new TextField("Brand");
		quantity = new TextField("Quantity");
		type = new ComboTypes("Type");
		note = new TextField("Note");
		
		mainLayout.addComponent(new SuggestTextField(name, suggestName, new SuggestShopItemName()));
		mainLayout.addComponent(new SuggestTextField(brand, suggestBrand, new SuggestBrandName()));
		mainLayout.addComponent(quantity);
		mainLayout.addComponent(type);
		mainLayout.addComponent(note);

		cancel = new Button("Cancel");
		add = new Button("Add");
		
		cancel.addClickListener(this);
		add.addClickListener(this);

		mainLayout.addComponent(cancel);
		mainLayout.addComponent(add);
		
		setContent(mainLayout);
				
	}

	public void buttonClick(ClickEvent event) {

		if ( event.getButton() == cancel ) {
			getUI().removeWindow(this);
		}
		else if ( event.getButton() == add ) {
			
			ShopItem item = new ShopItem();
			
			item.setName(name.getValue());
			item.setBrand(brand.getValue());
			item.setQuantity(Float.parseFloat(quantity.getValue()));
			item.setType((String) type.getValue());
			item.setBought(false);
			item.setNote(note.getValue());
			item.setOrder(0);
			
			Dao.shopItem().add(item, user);
			
			table.reloadData();
						
			getUI().removeWindow(this);

		}
		
	}
	
}
