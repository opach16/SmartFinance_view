package com.konrad.smartFinance.view.transactions;

import com.konrad.smartFinance.CryptoSymbol;
import com.konrad.smartFinance.CryptoTransactionType;
import com.konrad.smartFinance.domain.CryptoTransaction;
import com.konrad.smartFinance.service.CryptoTransactionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.math.BigDecimal;

public class CryptoTransactionForm extends FormLayout {

    private final CryptoTransactionService cryptoTransactionService = CryptoTransactionService.getInstance();
    private final CryptoTransactionLayout cryptoTransactionLayout;
    private final TextField transactionId = new TextField("Transaction ID");
    private final DatePicker transactionDate = new DatePicker("Transaction Date");
    private final ComboBox<CryptoTransactionType> transactionType = new ComboBox<>("Transaction Type");
    private final NumberField amount = new NumberField("Amount");
    private final NumberField price = new NumberField("Price");
    private final ComboBox<CryptoSymbol> symbol = new ComboBox<>("Symbol");
    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final Binder<CryptoTransaction> binder = new Binder<>(CryptoTransaction.class);

    public CryptoTransactionForm(CryptoTransactionLayout cryptoTransactionLayout) {
        transactionId.onEnabledStateChanged(false);
        transactionType.setItems(CryptoTransactionType.values());
        symbol.setItems(CryptoSymbol.values());
        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton);
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(transactionId, transactionDate, transactionType, amount, price, symbol, buttons);
        binder.bindInstanceFields(this);
        this.cryptoTransactionLayout = cryptoTransactionLayout;
        saveButton.addClickListener(e -> save());
        deleteButton.addClickListener(e -> delete());
        setWidth("50%");
    }

    public void save() {
        CryptoTransaction transaction = CryptoTransaction.builder()
                .transactionDate(transactionDate.getValue())
                .transactionType(transactionType.getValue())
                .symbol(symbol.getValue())
                .amount(BigDecimal.valueOf(amount.getValue()))
                .price(BigDecimal.valueOf(price.getValue()))
                .transactionId(transactionId.getValue().isBlank() ? 0L : Long.parseLong(transactionId.getValue()))
                .build();
        cryptoTransactionService.save(transaction);
        cryptoTransactionLayout.refresh();
        setTransaction(null);
    }

    public void delete() {
        CryptoTransaction transaction = binder.getBean();
        cryptoTransactionService.delete(transaction);
        cryptoTransactionLayout.refresh();
        setTransaction(null);
    }

    public void setTransaction(CryptoTransaction transaction) {
        binder.setBean(transaction);

        if (transaction == null) {
            setVisible(false);
        } else {
            setVisible(true);
            symbol.focus();
        }
    }
}
