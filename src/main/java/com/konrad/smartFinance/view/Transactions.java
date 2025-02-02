package com.konrad.smartFinance.view;

import com.konrad.smartFinance.view.transactions.CryptoTransactionLayout;
import com.konrad.smartFinance.view.transactions.CurrencyTransactionLayout;
import com.konrad.smartFinance.view.transactions.DebitTransactionLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("transactions")
public class Transactions extends VerticalLayout {

    private final Button debitButton = new Button("Debit");
    private final Button currencyButton = new Button("Currencies");
    private final Button cryptoButton = new Button("Crypto");
    private final DebitTransactionLayout debitTransactionLayout = new DebitTransactionLayout();
    private final CurrencyTransactionLayout currencyTransactionLayout = new CurrencyTransactionLayout();
    private final CryptoTransactionLayout cryptoTransactionLayout = new CryptoTransactionLayout();

    public Transactions() {

        debitTransactionLayout.setVisible(true);
        currencyTransactionLayout.setVisible(false);
        cryptoTransactionLayout.setVisible(false);

        HorizontalLayout toolbar = new HorizontalLayout(debitButton, currencyButton, cryptoButton);
        toolbar.setAlignItems(Alignment.END);

        VerticalLayout mainContent = new VerticalLayout(toolbar, debitTransactionLayout, currencyTransactionLayout, cryptoTransactionLayout);
        mainContent.setSizeFull();

        debitButton.addClickListener(event -> {
            debitTransactionLayout.refresh();
            debitTransactionLayout.setVisible(true);
            currencyTransactionLayout.setVisible(false);
            cryptoTransactionLayout.setVisible(false);
            debitTransactionLayout.refresh();
        });

        currencyButton.addClickListener(event -> {
            currencyTransactionLayout.refresh();
            debitTransactionLayout.setVisible(false);
            currencyTransactionLayout.setVisible(true);
            cryptoTransactionLayout.setVisible(false);
            currencyTransactionLayout.refresh();
        });

        cryptoButton.addClickListener(event -> {
            cryptoTransactionLayout.refresh();
            debitTransactionLayout.setVisible(false);
            currencyTransactionLayout.setVisible(false);
            cryptoTransactionLayout.setVisible(true);
            currencyTransactionLayout.refresh();
        });

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        add(mainContent);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (VaadinSession.getCurrent().getAttribute("username") == null) {
            UI.getCurrent().navigate("login");
        }
    }
}
