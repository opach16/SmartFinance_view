package com.konrad.smartFinance.view.transactions;

import com.konrad.smartFinance.domain.Transaction;
import com.konrad.smartFinance.service.CryptoTransactionService;
import com.konrad.smartFinance.service.CurrencyTransactionService;
import com.konrad.smartFinance.service.TransactionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("transactions")
public class Transactions extends VerticalLayout {

    private final Button debitButton = new Button("Debit");
    private final Button currencyButton = new Button("Currencies");
    private final Button cryptoButton = new Button("Crypto");
    private final TransactionLayout transactionLayout = new TransactionLayout();
    private final CryptoTransactionLayout cryptoTransactionLayout = new CryptoTransactionLayout();

    public Transactions() {

        HorizontalLayout toolbar = new HorizontalLayout(debitButton, currencyButton, cryptoButton);
        toolbar.setAlignItems(Alignment.END);

        transactionLayout.setVisible(true);
        cryptoTransactionLayout.setVisible(false);
        VerticalLayout mainView = new VerticalLayout(transactionLayout, cryptoTransactionLayout);

        debitButton.addClickListener(event -> {
            transactionLayout.setVisible(true);
            cryptoTransactionLayout.setVisible(false);
        });

        cryptoButton.addClickListener(event -> {
            transactionLayout.setVisible(false);
            cryptoTransactionLayout.setVisible(true);
        });

        add(toolbar, mainView);
    }
}
