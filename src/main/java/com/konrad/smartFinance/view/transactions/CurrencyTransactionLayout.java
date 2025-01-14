package com.konrad.smartFinance.view.transactions;

import com.konrad.smartFinance.domain.CurrencyTransaction;
import com.konrad.smartFinance.service.CurrencyTransactionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.time.LocalDate;

public class CurrencyTransactionLayout extends VerticalLayout {

    private final CurrencyTransactionService currencyTransactionService = CurrencyTransactionService.getInstance();
    private final Grid<CurrencyTransaction> grid = new Grid<>(CurrencyTransaction.class);
    private final TextField filter = new TextField();
    private final Button addTransactionButton = new Button("Add transaction");
    private final CurrencyTransactionForm form = new CurrencyTransactionForm(this);

    public CurrencyTransactionLayout() {
        filter.setPlaceholder("Filter by symbol..");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> update());

        grid.setColumns("transactionId", "transactionDate", "transactionType", "symbol", "amount", "price", "transactionValue", "currentValue");
        grid.setSizeFull();
        refresh();
        grid.asSingleSelect().addValueChangeListener(event -> form.setTransaction(grid.asSingleSelect().getValue()));

        form.setVisible(false);

        HorizontalLayout toolbar = new HorizontalLayout(filter, addTransactionButton);
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);

        mainContent.setSizeFull();
        mainContent.setFlexGrow(1, grid);

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        addTransactionButton.addClickListener(event -> {
            CurrencyTransaction transaction = new CurrencyTransaction();
            transaction.setTransactionDate(LocalDate.now());
            form.setTransaction(transaction);
            form.setVisible(true);
        });

        add(toolbar, mainContent);
    }

    public void refresh() {
        currencyTransactionService.updateCurrencyTransactions();
        grid.setItems(currencyTransactionService.getCurrencyTransactions());
    }

    public void update() {
        grid.setItems(currencyTransactionService.findBySymbol(filter.getValue()));
    }
}
