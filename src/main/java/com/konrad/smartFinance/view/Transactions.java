package com.konrad.smartFinance.view;

import com.konrad.smartFinance.domain.Transaction;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("transactions")
public class Transactions extends VerticalLayout {

    private Grid<Transaction> grid = new Grid<>(Transaction.class);
    private TextField filter = new TextField();
    private Button addTransaction = new Button("Add Transaction");
    private TransactionForm form = new TransactionForm(this);

    public Transactions() {
        filter.setPlaceholder("Filter");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);

        grid.setColumns("date", "type", "name", "amount");
        grid.setSizeFull();

        form.setVisible(false);

        HorizontalLayout toolbar = new HorizontalLayout(filter, addTransaction);
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);

        mainContent.setSizeFull();
        mainContent.setFlexGrow(1, grid);

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        addTransaction.addClickListener(event -> {
            form.setVisible(true);
        });

        add(toolbar, mainContent);
    }
}
