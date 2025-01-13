package com.konrad.smartFinance.view.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class LoginForm extends FormLayout {
    private final TextField username = new TextField("Username");
    private final PasswordField password = new PasswordField("Password");
    private final Button loginButton = new Button("Login");

    public LoginForm() {
        setWidth("100%");
        username.setWidth("100%");
        username.setPlaceholder("Username");
        password.setWidth("100%");
        password.setPlaceholder("Password");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(username, password, loginButton);
    }
}
