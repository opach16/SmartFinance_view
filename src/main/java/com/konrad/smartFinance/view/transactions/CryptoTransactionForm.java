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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class CryptoTransactionForm extends FormLayout {

    private final CryptoTransactionService cryptoTransactionService = CryptoTransactionService.getInstance();
    private final CryptoTransactionLayout cryptoTransactionLayout;
    private final DatePicker datePicker = new DatePicker("Transaction Date");
    private final ComboBox<CryptoTransactionType> transactionType = new ComboBox<>("Transaction Type");
    private final TextField name = new TextField("Name");
    private final TextField amount = new TextField("Amount");
    private final ComboBox<CryptoSymbol> symbol = new ComboBox<>("Symbol");
    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final Binder<CryptoTransaction> binder = new Binder<>(CryptoTransaction.class);

    public CryptoTransactionForm(CryptoTransactionLayout cryptoTransactionLayout) {
        transactionType.setItems(CryptoTransactionType.values());
        symbol.setItems(CryptoSymbol.values());
        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton);
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(datePicker, transactionType, name, amount, symbol, buttons);
        binder.bindInstanceFields(this);
        this.cryptoTransactionLayout = cryptoTransactionLayout;
        saveButton.addClickListener(e -> {
        });
        deleteButton.addClickListener(e -> {
        });
        setWidth("50%");
    }

    public void save() {
        CryptoTransaction transaction = binder.getBean();
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
        datePicker.setValue(transaction.getTransactionDate());
        transactionType.setValue(transaction.getTransactionType());
        symbol.setValue(transaction.getSymbol());

        if (transaction == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }
}
