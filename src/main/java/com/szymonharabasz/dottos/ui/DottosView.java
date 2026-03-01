package com.szymonharabasz.dottos.ui;

import com.szymonharabasz.dottos.DottosService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
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
        var createDotto = new CreateDotto();
        var dottosLayout = new FlexLayout();
        dottosLayout.setSizeFull();
        dottosLayout.setClassName("dottos-layout");
        var dottos = dottoService.getDottos();
        for (var dotto : dottos) {
            var detailsHeader = new H3(dotto.title());
            detailsHeader.getStyle().set("text-wrap", "wrap");
            var details = new Details(
                    detailsHeader,
                    new VerticalLayout(
                            new Div(dotto.description()),
                            new Div(dotto.created().toString()),
                            new Div(Integer.toString(dotto.score()))
                    )
            );
            details.setClassName("dotto-details");
            dottosLayout.add(details);
        }
        mainLayout.add(header, createDotto, dottosLayout);
        return mainLayout;
    }

    private final transient DottosService dottoService;
}
