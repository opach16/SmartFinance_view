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
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        transactionId.onEnabledStateChanged(false);
        transactionId.setVisible(false);
        amount.setVisible(false);
        price.setVisible(false);
        transactionType.setItems(AccountTransactionType.values());
        symbol.setItems(CurrencySymbol.values());
        HorizontalLayout buttons = new HorizontalLayout(saveButton, deleteButton);
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(transactionId, transactionDate, transactionType, name, amount, price, buttons);
        binder.bindInstanceFields(this);
        this.transactionLayout = transactionLayout;
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
        setWidth("50%");
    }

    public void save() {
        Transaction transaction = Transaction.builder()
                .transactionDate(transactionDate.getValue())
                .transactionType(transactionType.getValue())
                .name(name.getValue())
                .symbol(symbol.getValue())
                .amount(BigDecimal.valueOf(amount.getValue()))
                .price(BigDecimal.valueOf(price.getValue()))
                .transactionId(transactionId.getValue().isBlank() ? 0L : Long.parseLong(transactionId.getValue()))
                .build();
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

        if (transaction == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }
}
