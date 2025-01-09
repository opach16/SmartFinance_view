package com.konrad.smartFinance.view.transactions;

import com.konrad.smartFinance.domain.Transaction;
import com.konrad.smartFinance.service.TransactionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class TransactionLayout extends VerticalLayout {

    private final TransactionService transactionService = TransactionService.getInstance();
    private final Grid<Transaction> grid = new Grid<>(Transaction.class);
    private final TextField filter = new TextField();
    private final Button addTransactionButton = new Button("Add transaction");
    private final TransactionForm form = new TransactionForm(this);

    public TransactionLayout() {
        filter.setPlaceholder("Filter");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);

        grid.setColumns("transactionId", "transactionDate", "transactionType", "name", "amount", "price", "symbol", "transactionValue");
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
            form.setTransaction(new Transaction());
            form.setVisible(true);
        });

        add(toolbar, mainContent);
    }

    public void refresh() {
        grid.setItems(transactionService.getTransactions());
    }
}
