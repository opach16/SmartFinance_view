package com.konrad.smartFinance.view;

import com.konrad.smartFinance.domain.Asset;
import com.konrad.smartFinance.service.AssetsService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("assets")
public class Assets extends VerticalLayout {

    private final AssetsService assetsService = AssetsService.getInstance();
    private final Grid<Asset> grid = new Grid<>(Asset.class);
    private final TextField filter = new TextField();

    public Assets() {
        filter.setPlaceholder("Filter");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);

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
}