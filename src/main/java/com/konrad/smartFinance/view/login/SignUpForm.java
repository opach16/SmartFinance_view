package com.konrad.smartFinance.view.login;

import com.konrad.smartFinance.CurrencySymbol;
import com.konrad.smartFinance.domain.User;
import com.konrad.smartFinance.domain.UserRegistration;
import com.konrad.smartFinance.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.DoubleToBigDecimalConverter;

public class SignUpForm extends FormLayout {

    private final UserService userService = UserService.getInstance();
    private final TextField email = new TextField("E-Mail");
    private final TextField username = new TextField("Username");
    private final PasswordField password = new PasswordField("Password");
    private final PasswordField confirmPassword = new PasswordField("Confirm Password");
    private final ComboBox<CurrencySymbol> mainCurrency = new ComboBox<>("Main Currency");
    private final NumberField mainBalance = new NumberField("Start Balance");
    private final Button signUpButton = new Button("Sign up");
    private final Binder<UserRegistration> binder = new Binder<>(UserRegistration.class);

    public SignUpForm() {
        setWidth("100%");
        email.setWidth("100%");
        email.setPlaceholder("E-Mail");
        username.setWidth("100%");
        username.setPlaceholder("Username");
        password.setWidth("100%");
        password.setPlaceholder("Password");
        confirmPassword.setWidth("100%");
        confirmPassword.setPlaceholder("Confirm Password");
        mainCurrency.setWidth("100%");
        mainCurrency.setItems(CurrencySymbol.values());
        mainCurrency.setValue(CurrencySymbol.PLN);
        mainBalance.setWidth("100%");
        mainBalance.setPlaceholder("10000");
        signUpButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        binder.setBean(new UserRegistration(new User()));
        binder.forField(email)
                .asRequired("Email is required")
                .bind(userRegister -> userRegister.getUser().getEmail(),
                        (userRegister, value) -> userRegister.getUser().setEmail(value));

        binder.forField(username)
                .asRequired("Username is required")
                .bind(userRegister -> userRegister.getUser().getUsername(),
                        (userRegister, value) -> userRegister.getUser().setUsername(value));

        binder.forField(password)
                .asRequired("Password is required")
                .bind(userRegister -> userRegister.getUser().getPassword(),
                        (userRegister, value) -> userRegister.getUser().setPassword(value));

        binder.forField(mainCurrency)
                .asRequired("Main currency is required")
                .bind(UserRegistration::getMainCurrency, UserRegistration::setMainCurrency);

        binder.forField(mainBalance)
                .asRequired("Main balance is required")
                .withConverter(new DoubleToBigDecimalConverter())
                .bind(UserRegistration::getMainBalance, UserRegistration::setMainBalance);

        signUpButton.addClickListener(event -> save());

        add(email, username, password, confirmPassword, mainCurrency, mainBalance, signUpButton);
    }

    public void save() {
        if (email.getValue().isEmpty() || username.getValue().isEmpty() || password.getValue().isEmpty() || confirmPassword.getValue().isEmpty() || mainCurrency.getValue() == null || mainBalance.getValue() == null) {
            Notification.show("Please fill all the fields")
                    .setPosition(Notification.Position.BOTTOM_CENTER);
            return;
        }
        if (binder.validate().isOk()) {
            if (password.getValue().equals(confirmPassword.getValue())) {
                UserRegistration registration = binder.getBean();
                try {
                    userService.save(registration);
                    binder.setBean(new UserRegistration(new User()));
                    confirmPassword.clear();
                    Notification.show("User has been successfully saved")
                            .setPosition(Notification.Position.BOTTOM_CENTER);
                } catch (Exception e) {
                    Notification.show(e.getMessage()).setPosition(Notification.Position.BOTTOM_CENTER);
                    binder.setBean(new UserRegistration(new User()));
                    confirmPassword.clear();
                }
            } else {
                Notification.show("Passwords do not match")
                        .setPosition(Notification.Position.BOTTOM_CENTER);
                password.clear();
                confirmPassword.clear();
                password.focus();
            }
        } else {
            Notification.show("Fill all fields").setPosition(Notification.Position.BOTTOM_CENTER);
        }
    }
}
