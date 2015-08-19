package net.mastro.ishop.view;

import java.util.List;

import net.mastro.ishop.service.SuggestFactory;
import net.mastro.ishop.service.SuggestFoundListener;
import net.mastro.ishop.service.SuggestWorker;
import net.mastro.ishop.view.model.SuggestName;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class SuggestTextField extends VerticalLayout implements TextChangeListener, SuggestFoundListener {
		
	private static final long serialVersionUID = 1L;

	private BeanItemContainer<SuggestName> suggestDatasource;
	
	private Table suggestName;

	private ProgressIndicator indicator;
	
	private TextField textField;
	
	private SuggestFactory suggestService;

	private SuggestWorker suggestworker;

	public SuggestTextField(TextField textField, SuggestFactory suggestService, SuggestWorker suggestworker) {
		
		this.textField = textField;
		this.suggestService = suggestService;
		this.suggestworker = suggestworker;
		
		setSizeFull();
		
		this.textField.addTextChangeListener(this);
		
		indicator = new ProgressIndicator();
		indicator.setVisible(false);
		indicator.setPollingInterval(500);
		
		suggestName = new Table();
		suggestName.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		suggestName.setImmediate(true);
		suggestName.setHeight("50px");
		suggestName.setSelectable(true);
		suggestName.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			public void valueChange(ValueChangeEvent event) {
				SuggestName suggest = (SuggestName) event.getProperty().getValue();
				if ( suggest == null )
					return;
				SuggestTextField.this.textField.setValue(suggest.getName());
				suggestName.setVisible(false);
		    }
		});
		
		suggestName.setVisible(false);
		suggestDatasource = new BeanItemContainer<SuggestName>(SuggestName.class);
		suggestName.setContainerDataSource(suggestDatasource);

		this.addComponent(textField);
		this.addComponent(indicator);
		this.addComponent(suggestName);
		
	}

	@Override
	public void textChange(TextChangeEvent event) {
		indicator.setVisible(true);
		suggestService.search(suggestworker, event.getText(), this);
	}

	@Override
	public void found(String text, List<String> list) {
		System.out.println("search for: " + text);
		indicator.setVisible(false);
		if ( list.size() == 0 ) {
			suggestName.setVisible(false);
			return;
		}
		suggestName.setVisible(true);
		suggestDatasource.removeAllItems();
		for ( String item : list) {
			suggestDatasource.addItem(new SuggestName(item));
		}
		suggestName.refreshRowCache();
	}

}
