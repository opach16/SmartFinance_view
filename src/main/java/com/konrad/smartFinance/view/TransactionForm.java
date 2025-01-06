package com.konrad.smartFinance.view;

import com.konrad.smartFinance.AccountTransactionType;
import com.konrad.smartFinance.domain.Transaction;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class TransactionForm extends FormLayout {

    private Transactions transactions;
    private DatePicker datePicker = new DatePicker("Date");
    private ComboBox<AccountTransactionType> transactionType = new ComboBox<>("Type");
    private TextField name = new TextField("Name");
    private TextField amount = new TextField("Amount");
    private Button saveButton = new Button("Save");
    private Button deleteButton = new Button("Delete");
    private Binder<Transaction> binder = new Binder<>(Transaction.class);

    public TransactionForm(Transactions transactions) {
        transactionType.setItems(AccountTransactionType.values());
        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton);
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(datePicker, transactionType, name, amount, buttons);
        binder.bindInstanceFields(this);
        this.transactions = transactions;
        saveButton.addClickListener(e -> {});
        deleteButton.addClickListener(e -> {});
        setWidth("50%");
    }
}
