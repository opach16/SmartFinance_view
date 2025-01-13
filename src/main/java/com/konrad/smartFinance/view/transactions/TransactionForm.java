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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.DoubleToBigDecimalConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

public class TransactionForm extends FormLayout {

    private final TransactionService transactionService = TransactionService.getInstance();
    private final TransactionLayout transactionLayout;
    private final TextField transactionId = new TextField("Transaction ID");
    private final DatePicker transactionDate = new DatePicker("Transaction Date");
    private final ComboBox<AccountTransactionType> transactionType = new ComboBox<>("Transaction Type");
    private final TextField name = new TextField("Name");
    private final NumberField amount = new NumberField("Amount");
    private final NumberField price = new NumberField("Price");
    private final ComboBox<CurrencySymbol> symbol = new ComboBox<>("Symbol");
    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final Binder<Transaction> binder = new Binder<>(Transaction.class);

    public TransactionForm(TransactionLayout transactionLayout) {
        this.transactionLayout = transactionLayout;
        transactionId.onEnabledStateChanged(false);
        transactionId.setVisible(false);
        amount.setVisible(false);
        price.setVisible(false);
        transactionType.setItems(AccountTransactionType.values());
        symbol.setItems(CurrencySymbol.values());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton);

        transactionType.addValueChangeListener(event -> {
            AccountTransactionType type = event.getValue();
            if (type == AccountTransactionType.INCOME) {
                amount.setValue(1.0);
                amount.setVisible(false);
                price.setLabel("Value");
                price.setVisible(true);
            } else if (type == AccountTransactionType.EXPENSE) {
                amount.clear();
                amount.setVisible(true);
                price.setLabel("Price");
                price.setVisible(true);
            }
        });

        saveButton.addClickListener(e -> save());

        deleteButton.addClickListener(e -> delete());

        binder.forField(transactionDate)
                .asRequired("Transaction date is required")
                .bind(Transaction::getTransactionDate, Transaction::setTransactionDate);

        binder.forField(transactionType)
                .asRequired("Transaction type is required")
                .bind(Transaction::getTransactionType, Transaction::setTransactionType);

        binder.forField(name)
                .asRequired("Name is required")
                .bind(Transaction::getName, Transaction::setName);

        binder.forField(price)
                .asRequired("Price/Value is required")
                .withConverter(new DoubleToBigDecimalConverter())
                .bind(Transaction::getPrice, Transaction::setPrice);

        binder.forField(amount)
                .asRequired("Amount is required")
                .withConverter(new DoubleToBigDecimalConverter())
                .bind(Transaction::getAmount, Transaction::setAmount);

        setWidth("50%");
        add(transactionId, transactionDate, transactionType, name, amount, price, buttons);
    }

    public void save() {
        if (binder.validate().isOk()) {
            try {
                transactionService.save(binder.getBean());
                transactionLayout.refresh();
                setTransaction(null);
            } catch (Exception e) {
                Notification.show(e.getMessage()).setPosition(Notification.Position.BOTTOM_CENTER);
            }
        } else {
            Notification.show("Please fill all fields").setPosition(Notification.Position.BOTTOM_CENTER);
        }
    }

    public void delete() {
        Transaction transaction = binder.getBean();
        try {
            transactionService.delete(transaction);
            transactionLayout.refresh();
            setTransaction(null);
        } catch (Exception e) {
            Notification.show(e.getMessage()).setPosition(Notification.Position.BOTTOM_CENTER);
        }
    }

    public void setTransaction(Transaction transaction) {
        binder.setBean(transaction);

        if (transaction == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }
}
