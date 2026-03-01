package com.szymonharabasz.dottos.ui;

import com.szymonharabasz.dottos.DottosService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.Route;

@Route("dottos")
@Menu(order = 1, icon = "vaadin:check-square-o", title = "Dottos")
public class DottosView extends Composite<Component> {
    public DottosView(DottosService dottosService) {
        this.dottoService = dottosService;
    }

    @Override
    protected Component initContent() {
        var mainLayout = new VerticalLayout();
        var header = new H1("Dottos");
        var dottosLayout = new FlexLayout();
        dottosLayout.setSizeFull();
        dottosLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
        var dottos = dottoService.getDottos();
        for (var dotto : dottos) {
            var details = new Details(
                    new H3(dotto.title()),
                    new VerticalLayout(
                            new Div(dotto.description()),
                            new Div(dotto.created().toString()),
                            new Div(Integer.toString(dotto.rating()))
                    )
            );
            details.getStyle().setPadding("2rem");
            details.setWidth(20, Unit.REM);
            dottosLayout.add(details);
        }
        mainLayout.add(header, dottosLayout);
        return mainLayout;
    }

    private transient DottosService dottoService;
}
