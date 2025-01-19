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
import com.vaadin.flow.server.VaadinSession;

public class LoginForm extends FormLayout {
    private final UserService userService = UserService.getInstance();
    private final TextField username = new TextField("Username");
    private final PasswordField password = new PasswordField("Password");
    private final Button loginButton = new Button("Login");
    private final Button logoutButton = new Button("Logout");

    public LoginForm() {
        setWidth("100%");
        username.setWidth("100%");
        username.setPlaceholder("Username");
        password.setWidth("100%");
        password.setPlaceholder("Password");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        loginButton.addClickListener(e -> processLogin());
        logoutButton.addClickListener(e -> processLogout());


        add(username, password, loginButton, logoutButton);
    }

    public void processLogin() {
        LoginData loginData = new LoginData(username.getValue(), password.getValue());
        try {
            userService.login(loginData);
            Notification.show("Login Successful").setPosition(Notification.Position.BOTTOM_CENTER);
            UI.getCurrent().navigate("/");
        } catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }

    public void processLogout() {
        VaadinSession.getCurrent().close();
        Notification.show("Logout Successful").setPosition(Notification.Position.BOTTOM_CENTER);
    }
}
