package com.konrad.smartFinance.view;

import com.konrad.smartFinance.domain.Asset;
import com.konrad.smartFinance.service.AssetsService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("assets")
public class Assets extends VerticalLayout {

    private final AssetsService assetsService = AssetsService.getInstance();
    private final Grid<Asset> grid = new Grid<>(Asset.class);
    private final TextField filter = new TextField();

    public Assets() {
        filter.setPlaceholder("Filter by symbol..");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> update());

        grid.setColumns("assetId", "assetType", "symbol", "amount", "currentValue");
        grid.setItems(assetsService.getAssets());

        VerticalLayout mainContent = new VerticalLayout(filter, grid);

        mainContent.setSizeFull();
        mainContent.setFlexGrow(1, grid);

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        add(mainContent);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (VaadinSession.getCurrent().getAttribute("username") == null) {
            UI.getCurrent().navigate("login");
        } else {
            refresh();
        }
    }

    public void refresh() {
        assetsService.updateAssets();
        grid.setItems(assetsService.getAssets());
    }

    public void update() {
        grid.setItems(assetsService.findBySymbol(filter.getValue()));
    }
}
