package com.konrad.smartFinance.view.transactions;

import com.konrad.smartFinance.domain.DebitTransaction;
import com.konrad.smartFinance.service.DebitTransactionService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;

public class DebitTransactionLayout extends VerticalLayout {

    private final DebitTransactionService debitTransactionService = DebitTransactionService.getInstance();
    private final Grid<DebitTransaction> grid = new Grid<>(DebitTransaction.class);
    private final TextField filter = new TextField();
    private final Button addTransactionButton = new Button("Add transaction");
    private final DebitTransactionForm form = new DebitTransactionForm(this);

    public DebitTransactionLayout() {
        filter.setPlaceholder("Filter by name..");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> update());

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
            DebitTransaction transaction = new DebitTransaction();
            transaction.setTransactionDate(LocalDate.now());
            form.setTransaction(transaction);
            form.setVisible(true);
        });

        add(toolbar, mainContent);
    }

    public void refresh() {
        if (VaadinSession.getCurrent().getAttribute("username") == null) {
            UI.getCurrent().navigate("login");
        } else {
            debitTransactionService.updateTransactions();
            grid.setItems(debitTransactionService.getTransactions());
        }
    }

    public void update() {
        grid.setItems(debitTransactionService.findByName(filter.getValue()));
    }
}
