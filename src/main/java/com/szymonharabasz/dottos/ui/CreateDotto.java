package com.szymonharabasz.dottos.ui;

import com.szymonharabasz.dottos.DottoInput;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class CreateDotto extends FlexLayout {
    private final transient DottoInput dottoInput = new DottoInput();

    public CreateDotto(ComponentEventListener<ClickEvent<Button>> buttonClickListener) {
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
        button.addClickListener(buttonClickListener);
        controlPart.add(score, button);

        add(textPart, controlPart);

        Binder<DottoInput> bindder= new Binder<>();
        bindder.bind(title, DottoInput::getTitle, DottoInput::setTitle);
        bindder.bind(description, DottoInput::getDescription, DottoInput::setDescription);
        bindder.bind(score, DottoInput::getRating, DottoInput::setRating);
        bindder.setBean(dottoInput);
    }

    public DottoInput getDottoInput() {
        return dottoInput;
    }
}
