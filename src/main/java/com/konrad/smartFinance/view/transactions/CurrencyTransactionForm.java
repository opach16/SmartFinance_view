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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class CurrencyTransactionForm extends FormLayout {

    private final CurrencyTransactionService currencyTransactionService = CurrencyTransactionService.getInstance();
    private final CurrencyTransactionLayout currencyTransactionLayout;
    private final TextField transactionId = new TextField("Transaction ID");
    private final DatePicker transactionDate = new DatePicker("Transaction Date");
    private final ComboBox<CurrencyTransactionType> transactionType = new ComboBox<>("Transaction Type");
    private final TextField amount = new TextField("Amount");
    private final TextField price = new TextField("Price");
    private final ComboBox<CurrencySymbol> symbol = new ComboBox<>("Symbol");
    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final Binder<CurrencyTransaction> binder = new Binder<>(CurrencyTransaction.class);

    public CurrencyTransactionForm(CurrencyTransactionLayout currencyTransactionLayout) {
        transactionId.onEnabledStateChanged(false);
        transactionType.setItems(CurrencyTransactionType.values());
        symbol.setItems(CurrencySymbol.values());
        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton);
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(transactionId, transactionDate, transactionType, amount, price, symbol, buttons);
        binder.bindInstanceFields(this);
        this.currencyTransactionLayout = currencyTransactionLayout;
        saveButton.addClickListener(e -> {
        });
        deleteButton.addClickListener(e -> {
        });
        setWidth("50%");
    }

    public void save() {
        CurrencyTransaction transaction = binder.getBean();
        currencyTransactionService.save(transaction);
        currencyTransactionLayout.refresh();
        setTransaction(null);
    }

    public void delete() {
        CurrencyTransaction transaction = binder.getBean();
        currencyTransactionService.delete(transaction);
        currencyTransactionLayout.refresh();
        setTransaction(null);
    }

    public void setTransaction(CurrencyTransaction transaction) {
        binder.setBean(transaction);
        transactionDate.setValue(transaction.getTransactionDate());
        transactionType.setValue(transaction.getTransactionType());
        symbol.setValue(transaction.getSymbol());

        if (transaction == null) {
            setVisible(false);
        } else {
            setVisible(true);
            symbol.focus();
        }
    }
}
