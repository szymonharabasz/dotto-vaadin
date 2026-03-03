package com.szymonharabasz.dottos.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class CreateDotto extends FlexLayout {
    public CreateDotto() {
        setClassName("create-dotto");
        var title = new TextField("Title");
        var description = new TextArea("Description");
        description.setMinRows(3);
        description.setMaxRows(4);
        var add = new Button("Add");
        add.addThemeVariants(ButtonVariant.AURA_PRIMARY);
        add(title, description, add);
    }
}
