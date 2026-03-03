package com.szymonharabasz.dottos.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class CreateDotto extends FlexLayout {
    public CreateDotto() {
        setClassName("create-dotto");
        var textPart = new FlexLayout();
        textPart.setClassName("create-dotto-text-part");
        var title = new TextField("Title");
        title.setClearButtonVisible(true);
        title.setWidthFull();
        var description = new TextArea("Description");
        description.setMinRows(4);
        description.setMaxRows(4);
        description.setWidthFull();
        textPart.add(title, description);

        var controlPart = new FlexLayout();
        controlPart.setClassName("create-dotto-control-part");
        var score = new IntegerField("Score");
        score.setMin(0);
        score.setMax(10);
        score.setStep(1);
        score.setWidthFull();
        score.setStepButtonsVisible(true);
        score.setPlaceholder("0 to 10");
        var button = new Button("Add");
        button.addThemeVariants(ButtonVariant.AURA_PRIMARY);
        controlPart.add(score, button);

        add(textPart, controlPart);
    }
}
