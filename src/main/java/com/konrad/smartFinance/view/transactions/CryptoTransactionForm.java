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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.DoubleToBigDecimalConverter;

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
        this.cryptoTransactionLayout = cryptoTransactionLayout;
        transactionId.onEnabledStateChanged(false);
        transactionId.setVisible(false);
        transactionType.setItems(CryptoTransactionType.values());
        symbol.setItems(CryptoSymbol.values());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton);
        binder.bindInstanceFields(this);
        saveButton.addClickListener(e -> save());
        deleteButton.addClickListener(e -> delete());

        binder.forField(transactionDate)
                .asRequired("Transaction date is required")
                .bind(CryptoTransaction::getTransactionDate, CryptoTransaction::setTransactionDate);

        binder.forField(transactionType)
                .asRequired("Transaction type is required")
                .bind(CryptoTransaction::getTransactionType, CryptoTransaction::setTransactionType);

        binder.forField(symbol)
                .asRequired("Symbol is required")
                .bind(CryptoTransaction::getSymbol, CryptoTransaction::setSymbol);

        binder.forField(price)
                .asRequired("Price/Value is required")
                .withConverter(new DoubleToBigDecimalConverter())
                .bind(CryptoTransaction::getPrice, CryptoTransaction::setPrice);

        binder.forField(amount)
                .asRequired("Amount is required")
                .withConverter(new DoubleToBigDecimalConverter())
                .bind(CryptoTransaction::getAmount, CryptoTransaction::setAmount);

        setWidth("50%");
        add(transactionId, transactionDate, transactionType, amount, price, symbol, buttons);
    }

    public void save() {
        if (binder.validate().isOk()) {
            try {
                cryptoTransactionService.save(binder.getBean());
                cryptoTransactionLayout.refresh();
                setTransaction(null);
            } catch (Exception e) {
                Notification.show(e.getMessage()).setPosition(Notification.Position.BOTTOM_CENTER);
            }
        } else {
            Notification.show("Please fill all fields").setPosition(Notification.Position.BOTTOM_CENTER);
        }
    }

    public void delete() {
        try {
            CryptoTransaction transaction = binder.getBean();
            cryptoTransactionService.delete(transaction);
            cryptoTransactionLayout.refresh();
            setTransaction(null);
        } catch (Exception e) {
            Notification.show(e.getMessage()).setPosition(Notification.Position.BOTTOM_CENTER);
        }
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
