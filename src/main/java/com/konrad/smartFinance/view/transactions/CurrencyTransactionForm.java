package com.konrad.smartFinance.view.transactions;

import com.konrad.smartFinance.CurrencySymbol;
import com.konrad.smartFinance.CurrencyTransactionType;
import com.konrad.smartFinance.domain.CurrencyTransaction;
import com.konrad.smartFinance.service.CurrencyTransactionService;
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

public class CurrencyTransactionForm extends FormLayout {

    private final CurrencyTransactionService currencyTransactionService = CurrencyTransactionService.getInstance();
    private final CurrencyTransactionLayout currencyTransactionLayout;
    private final TextField transactionId = new TextField("Transaction ID");
    private final DatePicker transactionDate = new DatePicker("Transaction Date");
    private final ComboBox<CurrencyTransactionType> transactionType = new ComboBox<>("Transaction Type");
    private final NumberField amount = new NumberField("Amount");
    private final NumberField price = new NumberField("Price");
    private final ComboBox<CurrencySymbol> symbol = new ComboBox<>("Symbol");
    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final Binder<CurrencyTransaction> binder = new Binder<>(CurrencyTransaction.class);

    public CurrencyTransactionForm(CurrencyTransactionLayout currencyTransactionLayout) {
        this.currencyTransactionLayout = currencyTransactionLayout;
        transactionId.onEnabledStateChanged(false);
        transactionId.setVisible(false);
        transactionType.setItems(CurrencyTransactionType.values());
        symbol.setItems(CurrencySymbol.values());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton);

        saveButton.addClickListener(e -> save());

        deleteButton.addClickListener(e -> delete());

        binder.forField(transactionDate)
                .asRequired("Transaction date is required")
                .bind(CurrencyTransaction::getTransactionDate, CurrencyTransaction::setTransactionDate);

        binder.forField(transactionType)
                .asRequired("Transaction type is required")
                .bind(CurrencyTransaction::getTransactionType, CurrencyTransaction::setTransactionType);

        binder.forField(symbol)
                .asRequired("Symbol is required")
                .bind(CurrencyTransaction::getSymbol, CurrencyTransaction::setSymbol);

        binder.forField(price)
                .asRequired("Price/Value is required")
                .withConverter(new DoubleToBigDecimalConverter())
                .bind(CurrencyTransaction::getPrice, CurrencyTransaction::setPrice);

        binder.forField(amount)
                .asRequired("Amount is required")
                .withConverter(new DoubleToBigDecimalConverter())
                .bind(CurrencyTransaction::getAmount, CurrencyTransaction::setAmount);

        setWidth("50%");
        add(transactionId, transactionDate, transactionType, symbol, amount, price, buttons);
    }

    public void save() {
        if (binder.validate().isOk()) {
            try {
                currencyTransactionService.save(binder.getBean());
                currencyTransactionLayout.refresh();
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
            CurrencyTransaction transaction = binder.getBean();
            currencyTransactionService.delete(transaction);
            currencyTransactionLayout.refresh();
            setTransaction(null);
        } catch (Exception e) {
            Notification.show(e.getMessage()).setPosition(Notification.Position.BOTTOM_CENTER);
        }
    }

    public void setTransaction(CurrencyTransaction transaction) {
        binder.setBean(transaction);

        if (transaction == null) {
            setVisible(false);
        } else {
            setVisible(true);
            symbol.focus();
        }
    }
}
