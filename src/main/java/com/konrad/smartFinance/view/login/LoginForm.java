package com.konrad.smartFinance.view.login;

import com.konrad.smartFinance.domain.LoginData;
import com.konrad.smartFinance.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class LoginForm extends FormLayout {
    private final UserService userService = UserService.getInstance();
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

        loginButton.addClickListener(e -> processLogin());

        add(username, password, loginButton);
    }

    public void processLogin() {
        LoginData loginData = new LoginData(username.getValue(), password.getValue());
        try {
            userService.login(loginData);
            Notification.show("Login Successful");
            UI.getCurrent().navigate("/");
        } catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }
}
