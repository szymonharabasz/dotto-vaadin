package com.szymonharabasz.dottos.ui;

import com.szymonharabasz.dottos.DottoInput;
import com.szymonharabasz.dottos.DottosService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Route("dottos")
@RolesAllowed("user")
@Menu(order = 1, icon = "vaadin:check-square-o", title = "Dottos")
public class DottosView extends Composite<Component> {
    public DottosView(DottosService dottosService) {
        this.dottoService = dottosService;
        ComponentEventListener<ClickEvent<Button>> addDottoListener = event -> {
            DottoInput input = createDotto.getDottoInput();
            dottoService.createDotto(input);
            addDottosDetails();
        };
        createDotto = new CreateDotto(addDottoListener);
        createDotto.setWidthFull();

    }

    @Override
    protected Component initContent() {
        var mainLayout = new VerticalLayout();
        var header = new H1("Dottos");
        dottosLayout = new FlexLayout();
        dottosLayout.setSizeFull();
        dottosLayout.setClassName("dottos-layout");
        addDottosDetails();
        mainLayout.add(header, createDotto, dottosLayout);
        return mainLayout;
    }

    private void addDottosDetails() {
        dottosLayout.removeAll();
        var dottos = dottoService.getDottos();
        for (var dotto : dottos) {
            var detailsHeader = new H3(dotto.title());
            var details = new Details(
                    detailsHeader,
                    new VerticalLayout(
                            new Div(dotto.description()),
                            new Div(new Icon("vaadin:calendar-clock"),
                                    new Span(dotto.created().format(
                                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)))),
                            new Div(Integer.toString(dotto.score()))
                    )
            );
            details.setClassName("dotto-details");
            dottosLayout.add(details);
        }
    }

    private final transient DottosService dottoService;
    private CreateDotto createDotto;
    private FlexLayout dottosLayout;
}
