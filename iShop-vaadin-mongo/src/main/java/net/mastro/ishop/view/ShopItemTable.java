package net.mastro.ishop.view;

import net.mastro.ishop.Dao;
import net.mastro.ishop.model.User;
import net.mastro.ishop.view.model.ShopItem;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;

public class ShopItemTable extends Table {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private BeanItemContainer<ShopItem> datasource;
	
	public ShopItemTable(User user) {
		super();
		this.user = user;
		datasource = new BeanItemContainer<ShopItem>(ShopItem.class);
		setContainerDataSource(datasource);
		setVisibleColumns(new Object[]{"bought", "name", "brand", "quantity", "type", "note", "remove"});
		reloadData();
	}
	
	public void reloadData() {
		datasource.removeAllItems();
		for ( net.mastro.ishop.model.ShopItem item : Dao.shopItem().getShopItems(user) ) 
			datasource.addBean(new ShopItem(item, datasource));
	}

}
