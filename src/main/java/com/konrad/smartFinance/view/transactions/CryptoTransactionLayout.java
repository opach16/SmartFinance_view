package com.konrad.smartFinance.view.transactions;

import com.konrad.smartFinance.domain.CryptoTransaction;
import com.konrad.smartFinance.service.CryptoTransactionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.time.LocalDate;

public class CryptoTransactionLayout extends VerticalLayout {

    private final CryptoTransactionService cryptoTransactionService = CryptoTransactionService.getInstance();
    private final Grid<CryptoTransaction> grid = new Grid<>(CryptoTransaction.class);
    private final TextField filter = new TextField();
    private final Button addTransactionButton = new Button("Add transaction");
    private final CryptoTransactionForm form = new CryptoTransactionForm(this);

    public CryptoTransactionLayout() {
        filter.setPlaceholder("Filter by symbol..");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> update());

        grid.setColumns("transactionId", "transactionDate", "transactionType", "name", "symbol", "amount", "price", "transactionValue", "currentValue");
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
            CryptoTransaction transaction = new CryptoTransaction();
            transaction.setTransactionDate(LocalDate.now());
            form.setTransaction(transaction);
            form.setVisible(true);
        });

        add(toolbar, mainContent);
    }

    public void refresh() {
        cryptoTransactionService.updateCryptoTransactions();
        grid.setItems(cryptoTransactionService.getCryptoTransactions());
    }

    public void update() {
        grid.setItems(cryptoTransactionService.findBySymbol(filter.getValue()));
    }
}
