package com.konrad.smartFinance.view.transactions;

import com.konrad.smartFinance.domain.CurrencyTransaction;
import com.konrad.smartFinance.service.CurrencyTransactionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class CurrencyTransactionLayout extends VerticalLayout {

    private final CurrencyTransactionService cryptoTransactionService = CurrencyTransactionService.getInstance();
    private final Grid<CurrencyTransaction> grid = new Grid<>(CurrencyTransaction.class);
    private final TextField filter = new TextField();
    private final Button addTransactionButton = new Button("Add transaction");
    private final CurrencyTransactionForm form = new CurrencyTransactionForm(this);

    public CurrencyTransactionLayout() {
        filter.setPlaceholder("Filter");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);

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
            form.setVisible(true);
        });

        add(toolbar, mainContent);
    }

    public void refresh() {
        grid.setItems(cryptoTransactionService.getCurrencyTransactions());
    }
}
