package net.mastro.ishop.view;

import java.net.URI;
import java.net.UnknownHostException;

import net.mastro.ishop.Dao;
import net.mastro.ishop.model.User;

import com.mongodb.MongoException;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class LoginView extends CustomComponent implements Button.ClickListener {

	private static final long serialVersionUID = 1L;
	
	@AutoGenerated
	private FormLayout mainLayout;

	private Button login;

	private Button register;
	
	private TextField username;

	private TextField password;
	
	private URI onSuccess;

	private URI onFail;
	
	public LoginView(URI onSuccess, URI onFail) throws UnknownHostException, MongoException {
		this.onSuccess = onSuccess;
		this.onFail = onFail;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private void buildMainLayout() {
		
		// the main layout and components will be created here
		mainLayout = new FormLayout();
		
		mainLayout.setMargin(true);
		
		username = new TextField("Username");
		password = new TextField("Password");
		mainLayout.addComponent(username);
		mainLayout.addComponent(password);

		login = new Button("Login");
		register = new Button("Register");

		mainLayout.addComponent(login);
		mainLayout.addComponent(register);
		
		login.addClickListener(this);
		register.addClickListener(this);
		
	}

	public void buttonClick(ClickEvent event) {
		if ( event.getButton() == login ) {
			
			User user = Dao.auth().authorize(username.getValue(), password.getValue());
			
			if ( user != null ) {
				
				VaadinService.reinitializeSession(VaadinService.getCurrentRequest());

				// Setup new session
				VaadinService.getCurrentRequest().getWrappedSession().setAttribute("principal", user);

				// Reload same page
				getUI().getPage().setLocation(onSuccess);
				
			}
			else {

				// Reload same page
				getUI().getPage().setLocation(onFail);
				
			}
		}
		else if ( event.getButton() == register ) {
			try {
				getUI().addWindow(new RegistrationWindow());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

}
