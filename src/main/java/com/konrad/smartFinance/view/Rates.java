package com.konrad.smartFinance.view;

import com.konrad.smartFinance.domain.CryptoRates;
import com.konrad.smartFinance.domain.CurrencyRates;
import com.konrad.smartFinance.service.CryptoService;
import com.konrad.smartFinance.service.CurrencyService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("rates")
public class Rates extends VerticalLayout {

    private final CurrencyService currencyService = CurrencyService.getInstance();
    private final CryptoService cryptoService = CryptoService.getInstance();
    private final Grid<CurrencyRates> fiatRatesGrid = new Grid<>(CurrencyRates.class);
    private final Grid<CryptoRates> cryptoRatesGrid = new Grid<>(CryptoRates.class);
    private final Button currenciesButton = new Button("Currencies");
    private final Button cryptoButton = new Button("Crypto");

    public Rates() {

        fiatRatesGrid.setColumns("symbol", "price");
        fiatRatesGrid.setItems(currencyService.getCurrencyRates());
        fiatRatesGrid.setSizeFull();

        cryptoRatesGrid.setColumns("name", "symbol", "price");
        cryptoRatesGrid.setItems(cryptoService.getCryptoRates());
        cryptoRatesGrid.setSizeFull();
        cryptoRatesGrid.setVisible(false);

        HorizontalLayout toolbar = new HorizontalLayout(currenciesButton, cryptoButton);
        toolbar.setAlignItems(Alignment.END);

        VerticalLayout mainContent = new VerticalLayout(toolbar, fiatRatesGrid, cryptoRatesGrid);
        mainContent.setSizeFull();
        mainContent.setFlexGrow(1, fiatRatesGrid);

        currenciesButton.addClickListener(event -> {
            fiatRatesGrid.setItems(currencyService.getCurrencyRates());
            cryptoRatesGrid.setVisible(false);
            fiatRatesGrid.setVisible(true);
        });

        cryptoButton.addClickListener(event -> {
            cryptoRatesGrid.setItems(cryptoService.getCryptoRates());
            fiatRatesGrid.setVisible(false);
            cryptoRatesGrid.setVisible(true);
        });

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        add(mainContent);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        refresh();
    }

    public void refresh() {
        fiatRatesGrid.setItems(currencyService.getCurrencyRates());
        cryptoRatesGrid.setItems(cryptoService.getCryptoRates());
    }
}
