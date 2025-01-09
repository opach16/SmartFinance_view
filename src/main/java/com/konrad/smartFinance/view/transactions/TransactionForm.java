package com.konrad.smartFinance.view.transactions;

import com.konrad.smartFinance.AccountTransactionType;
import com.konrad.smartFinance.CurrencySymbol;
import com.konrad.smartFinance.domain.Transaction;
import com.konrad.smartFinance.service.TransactionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class TransactionForm extends FormLayout {

    private final TransactionService transactionService = TransactionService.getInstance();
    private final TransactionLayout transactionLayout;
    private final TextField transactionId = new TextField("Transaction ID");
    private final DatePicker transactionDate = new DatePicker("Transaction Date");
    private final ComboBox<AccountTransactionType> transactionType = new ComboBox<>("Transaction Type");
    private final TextField name = new TextField("Name");
    private final TextField amount = new TextField("Amount");
    private final TextField price = new TextField("Price");
    private final ComboBox<CurrencySymbol> symbol = new ComboBox<>("Symbol");
    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final Binder<Transaction> binder = new Binder<>(Transaction.class);

    public TransactionForm(TransactionLayout transactionLayout) {
        transactionId.onEnabledStateChanged(false);
        transactionType.setItems(AccountTransactionType.values());
        symbol.setItems(CurrencySymbol.values());
        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton);
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(transactionId, transactionDate, transactionType, name, amount, price, buttons);
        binder.bindInstanceFields(this);
        this.transactionLayout = transactionLayout;
        saveButton.addClickListener(e -> {
        });
        deleteButton.addClickListener(e -> {
        });
        setWidth("50%");
    }

    public void save() {
        Transaction transaction = binder.getBean();
        transactionService.save(transaction);
        transactionLayout.refresh();
        setTransaction(null);
    }

    public void delete() {
        Transaction transaction = binder.getBean();
        transactionService.delete(transaction);
        transactionLayout.refresh();
        setTransaction(null);
    }

    public void setTransaction(Transaction transaction) {
        binder.setBean(transaction);
        transactionDate.setValue(transaction.getTransactionDate());
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
