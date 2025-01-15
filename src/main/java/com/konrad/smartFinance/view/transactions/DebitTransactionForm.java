package com.konrad.smartFinance.view.transactions;

import com.konrad.smartFinance.CurrencySymbol;
import com.konrad.smartFinance.DebitTransactionType;
import com.konrad.smartFinance.domain.DebitTransaction;
import com.konrad.smartFinance.service.DebitTransactionService;
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

public class DebitTransactionForm extends FormLayout {

    private final DebitTransactionService debitTransactionService = DebitTransactionService.getInstance();
    private final DebitTransactionLayout debitTransactionLayout;
    private final TextField transactionId = new TextField("Transaction ID");
    private final DatePicker transactionDate = new DatePicker("Transaction Date");
    private final ComboBox<DebitTransactionType> transactionType = new ComboBox<>("Transaction Type");
    private final TextField name = new TextField("Name");
    private final NumberField amount = new NumberField("Amount");
    private final NumberField price = new NumberField("Price");
    private final ComboBox<CurrencySymbol> symbol = new ComboBox<>("Symbol");
    private final Button saveButton = new Button("Save");
    private final Button deleteButton = new Button("Delete");
    private final Binder<DebitTransaction> binder = new Binder<>(DebitTransaction.class);

    public DebitTransactionForm(DebitTransactionLayout debitTransactionLayout) {
        this.debitTransactionLayout = debitTransactionLayout;
        transactionId.onEnabledStateChanged(false);
        transactionId.setVisible(false);
        amount.setVisible(false);
        price.setVisible(false);
        transactionType.setItems(DebitTransactionType.values());
        symbol.setItems(CurrencySymbol.values());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton);

        transactionType.addValueChangeListener(event -> {
            DebitTransactionType type = event.getValue();
            if (type == DebitTransactionType.INCOME) {
                amount.setValue(1.0);
                amount.setVisible(false);
                price.setLabel("Value");
                price.setVisible(true);
            } else if (type == DebitTransactionType.EXPENSE) {
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
                .bind(DebitTransaction::getTransactionDate, DebitTransaction::setTransactionDate);

        binder.forField(transactionType)
                .asRequired("Transaction type is required")
                .bind(DebitTransaction::getTransactionType, DebitTransaction::setTransactionType);

        binder.forField(name)
                .asRequired("Name is required")
                .bind(DebitTransaction::getName, DebitTransaction::setName);

        binder.forField(price)
                .asRequired("Price/Value is required")
                .withConverter(new DoubleToBigDecimalConverter())
                .bind(DebitTransaction::getPrice, DebitTransaction::setPrice);

        binder.forField(amount)
                .asRequired("Amount is required")
                .withConverter(new DoubleToBigDecimalConverter())
                .bind(DebitTransaction::getAmount, DebitTransaction::setAmount);

        setWidth("50%");
        add(transactionId, transactionDate, transactionType, name, amount, price, buttons);
    }

    public void save() {
        if (binder.validate().isOk()) {
            try {
                debitTransactionService.save(binder.getBean());
                debitTransactionLayout.refresh();
                setTransaction(null);
            } catch (Exception e) {
                Notification.show(e.getMessage()).setPosition(Notification.Position.BOTTOM_CENTER);
            }
        } else {
            Notification.show("Please fill all fields").setPosition(Notification.Position.BOTTOM_CENTER);
        }
    }

    public void delete() {
        DebitTransaction transaction = binder.getBean();
        try {
            debitTransactionService.delete(transaction);
            debitTransactionLayout.refresh();
            setTransaction(null);
        } catch (Exception e) {
            Notification.show(e.getMessage()).setPosition(Notification.Position.BOTTOM_CENTER);
        }
    }

    public void setTransaction(DebitTransaction transaction) {
        binder.setBean(transaction);

        if (transaction == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }
}
