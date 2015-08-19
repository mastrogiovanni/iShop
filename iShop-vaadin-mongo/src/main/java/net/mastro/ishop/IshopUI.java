package net.mastro.ishop;

import java.net.URI;
import java.net.UnknownHostException;

import net.mastro.ishop.model.User;
import net.mastro.ishop.utility.Cfg;
import net.mastro.ishop.view.AddShopItem;
import net.mastro.ishop.view.LoginView;
import net.mastro.ishop.view.ShopItemTable;

import com.mongodb.MongoException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
public class IshopUI extends UI  {
		
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void init(VaadinRequest request) {

//		if (Cfg.getBoolean(Cfg.DEBUG_USE_DEFAULT_USER)) {
//			User user = Dao.auth().authorize(Cfg.get(Cfg.DEBUG_DEFAULT_USER), Cfg.get(Cfg.DEBUG_DEFAULT_PASS));
//			request.getWrappedSession().setAttribute("principal", user);
//		}
		
		final User user = (User) request.getWrappedSession().getAttribute("principal");
		
		if ( user == null ) {

			try {
				setContent(new LoginView(getPage().getLocation(), getPage().getLocation()));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
			
		}
		else {
			
			VerticalLayout layout = new VerticalLayout();
			layout.setMargin(true);
			setContent(layout);

			final ShopItemTable table = new ShopItemTable(user);
			layout.addComponent(new Label("Welcome: " + user.getUsername()));
			Button logout = new Button("Logout");
			layout.addComponent(logout);
			
			
			Button add = new Button("Add");
			add.addClickListener(new ClickListener() {
				
				private static final long serialVersionUID = 1L;

				public void buttonClick(ClickEvent event) {
					
					getUI().addWindow(new AddShopItem(user, table));
					
				}
				
			});

			layout.addComponent(add);
			
			layout.addComponent(table);
			
			table.setSelectable(true);
			
			logout.addClickListener(new ClickListener() {
				
				private static final long serialVersionUID = 1L;

				public void buttonClick(ClickEvent event) {
					
					URI location = getPage().getLocation();

					VaadinService.getCurrentRequest().getWrappedSession().removeAttribute("principal");
					
					table.removeAllItems();
					
					getUI().getPage().setLocation(location);
					
				}
				
			});

		}
		
	}

}