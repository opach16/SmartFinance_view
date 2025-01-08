package com.konrad.smartFinance.view;

import com.konrad.smartFinance.domain.CurrencyRates;
import com.konrad.smartFinance.domain.CurrencyTransaction;
import com.konrad.smartFinance.service.CurrencyService;
import com.konrad.smartFinance.service.CurrencyTransactionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("currencies")
public class Currencies extends VerticalLayout {

    private final CurrencyTransactionService currencyTransactionService = CurrencyTransactionService.getInstance();
    private final CurrencyService currencyService = CurrencyService.getInstance();
    private Grid<CurrencyTransaction> mainGrid = new Grid<>(CurrencyTransaction.class);
    private Grid<CurrencyRates> ratesGrid = new Grid<>(CurrencyRates.class);

    private TextField filter = new TextField();
    private Button assets = new Button("Assets");
    private Button rates = new Button("Rates");

    public Currencies() {
        filter.setPlaceholder("Filter");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);

        mainGrid.setColumns("transactionDate", "transactionType", "symbol", "amount", "price", "transactionValue", "currentValue");
        mainGrid.setItems(currencyTransactionService.getCurrencyTransactions());
        mainGrid.setSizeFull();

        ratesGrid.setColumns("symbol", "price");
        ratesGrid.setItems(currencyService.getCurrencyRatesSet());
        ratesGrid.setSizeFull();
        ratesGrid.setVisible(false);

        HorizontalLayout toolbar = new HorizontalLayout(filter, assets, rates);
        toolbar.setAlignItems(Alignment.END);

        HorizontalLayout mainContent = new HorizontalLayout(mainGrid, ratesGrid);

        mainContent.setSizeFull();
        mainContent.setFlexGrow(1, mainGrid);

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        rates.addClickListener(event -> {
            mainGrid.setVisible(false);
            ratesGrid.setVisible(true);
        });

        assets.addClickListener(event -> {
            ratesGrid.setVisible(false);
            mainGrid.setVisible(true);
        });

        add(toolbar, mainContent);
    }
}
