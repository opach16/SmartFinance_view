package com.konrad.smartFinance.view;

import com.konrad.smartFinance.view.transactions.CryptoTransactionLayout;
import com.konrad.smartFinance.view.transactions.CurrencyTransactionLayout;
import com.konrad.smartFinance.view.transactions.TransactionLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("transactions")
public class Transactions extends VerticalLayout {

    private final Button debitButton = new Button("Debit");
    private final Button currencyButton = new Button("Currencies");
    private final Button cryptoButton = new Button("Crypto");
    private final TransactionLayout transactionLayout = new TransactionLayout();
    private final CurrencyTransactionLayout currencyTransactionLayout = new CurrencyTransactionLayout();
    private final CryptoTransactionLayout cryptoTransactionLayout = new CryptoTransactionLayout();

    public Transactions() {

        transactionLayout.setVisible(true);
        currencyTransactionLayout.setVisible(false);
        cryptoTransactionLayout.setVisible(false);

        HorizontalLayout toolbar = new HorizontalLayout(debitButton, currencyButton, cryptoButton);
        toolbar.setAlignItems(Alignment.END);

        VerticalLayout mainContent = new VerticalLayout(toolbar, transactionLayout, currencyTransactionLayout, cryptoTransactionLayout);
        mainContent.setSizeFull();

        debitButton.addClickListener(event -> {
            transactionLayout.setVisible(true);
            currencyTransactionLayout.setVisible(false);
            cryptoTransactionLayout.setVisible(false);
            transactionLayout.refresh();
        });

        currencyButton.addClickListener(event -> {
            transactionLayout.setVisible(false);
            currencyTransactionLayout.setVisible(true);
            cryptoTransactionLayout.setVisible(false);
            currencyTransactionLayout.refresh();
        });

        cryptoButton.addClickListener(event -> {
            transactionLayout.setVisible(false);
            currencyTransactionLayout.setVisible(false);
            cryptoTransactionLayout.setVisible(true);
            currencyTransactionLayout.refresh();
        });

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        add(mainContent);
    }
}
