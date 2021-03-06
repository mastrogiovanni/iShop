package net.mastro.ishop.view;

import net.mastro.ishop.Dao;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class RegistrationWindow extends Window implements Button.ClickListener {

	private static final long serialVersionUID = 1L;
	
	private Button cancel;

	private Button register;
	
	private TextField username;

	private TextField password;

	public RegistrationWindow() {
		super();
		setWidth("300px");
		setHeight("250px");
		center();
		buildMainLayout();
	}

	@AutoGenerated
	private void buildMainLayout() {
		
		// the main layout and components will be created here
		FormLayout mainLayout = new FormLayout();
		mainLayout.setSizeFull();
		
		mainLayout.setMargin(true);
		
		username = new TextField("Username");
		password = new TextField("Password");
		mainLayout.addComponent(username);
		mainLayout.addComponent(password);

		cancel = new Button("Cancel");
		register = new Button("Register");
		
		cancel.addClickListener(this);
		register.addClickListener(this);

		mainLayout.addComponent(cancel);
		mainLayout.addComponent(register);
		
		setContent(mainLayout);
				
	}

	public void buttonClick(ClickEvent event) {

		if ( event.getButton() == cancel ) {
			getUI().removeWindow(this);
		}
		else if ( event.getButton() == register ) {
			
			// TODO add validation of user
			
			// Register new user;
			Dao.auth().register(username.getValue(), password.getValue());

			getUI().removeWindow(this);

		}
		
	}

}
