package net.mastro.ishop.view.model;

import net.mastro.ishop.Dao;
import net.mastro.ishop.view.ComboTypes;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ShopItem implements ValueChangeListener, ClickListener {
	
	private static final long serialVersionUID = 1L;

	private net.mastro.ishop.model.ShopItem item;
	
	private CheckBox bought;
	
	private TextField name;
	
	private TextField brand;

	private TextField quantity;
	
	private ComboBox type;
	
	private TextArea note;
	
	private Button remove;
	
	private BeanItemContainer<ShopItem> container;
	
	public ShopItem(net.mastro.ishop.model.ShopItem item, BeanItemContainer<ShopItem> container) {
		super();
		
		this.container = container;
		this.item = item;
		
		bought = new CheckBox();
		name = new TextField();
		brand = new TextField();
		quantity = new TextField();
		type = new ComboTypes();
		note = new TextArea();
		remove = new Button("remove");
		
		loadValues();

		bought.setImmediate(true);
		name.setImmediate(true);
		brand.setImmediate(true);
		quantity.setImmediate(true);
		type.setImmediate(true);
		note.setImmediate(true);

		bought.addValueChangeListener(this);
		name.addValueChangeListener(this);
		brand.addValueChangeListener(this);
		quantity.addValueChangeListener(this);
		type.addValueChangeListener(this);
		note.addValueChangeListener(this);
		remove.addClickListener(this);

	}

	private void loadValues() {
		bought.setValue(item.isBought());
		name.setValue(item.getName());
		brand.setValue(item.getBrand());
		quantity.setValue("" + item.getQuantity());
		type.setValue(item.getType());
		note.setValue(item.getNote());
	}
	
	public void valueChange(ValueChangeEvent event) {
		
		item.setBought(bought.getValue());
		item.setName(name.getValue());
		item.setBrand(brand.getValue());
		item.setQuantity(Float.parseFloat(quantity.getValue()));
		item.setType((String) type.getValue());
		item.setNote(note.getValue());

		Dao.shopItem().update(ShopItem.this.item);
		
	}

	public net.mastro.ishop.model.ShopItem getItem() {
		return item;
	}

	public void setItem(net.mastro.ishop.model.ShopItem item) {
		this.item = item;
	}

	public CheckBox getBought() {
		return bought;
	}

	public void setBought(CheckBox bought) {
		this.bought = bought;
	}

	public TextField getName() {
		return name;
	}

	public void setName(TextField name) {
		this.name = name;
	}

	public TextField getBrand() {
		return brand;
	}

	public void setBrand(TextField brand) {
		this.brand = brand;
	}

	public TextField getQuantity() {
		return quantity;
	}

	public void setQuantity(TextField quantity) {
		this.quantity = quantity;
	}

	public ComboBox getType() {
		return type;
	}

	public void setType(ComboBox type) {
		this.type = type;
	}

	public TextArea getNote() {
		return note;
	}

	public void setNote(TextArea note) {
		this.note = note;
	}

	public void buttonClick(ClickEvent event) {
		Dao.shopItem().delete(item);
		container.removeItem(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopItem other = (ShopItem) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

	public Button getRemove() {
		return remove;
	}

	public void setRemove(Button remove) {
		this.remove = remove;
	}
	
}
