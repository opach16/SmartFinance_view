package com.konrad.smartFinance.view;

import com.konrad.smartFinance.view.login.LoginForm;
import com.konrad.smartFinance.view.login.SignUpForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("user")
public class Login extends VerticalLayout {

    private final Button signUpButton = new Button("Sign Up");
    private final Button loginButton = new Button("Login");
    private final LoginForm loginForm = new LoginForm();
    private final SignUpForm signUpForm = new SignUpForm();

    public Login() {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        signUpForm.setVisible(false);
        loginForm.setVisible(true);

        HorizontalLayout buttons = new HorizontalLayout(loginButton, signUpButton);
        HorizontalLayout forms = new HorizontalLayout(loginForm, signUpForm);
        VerticalLayout layout = new VerticalLayout(buttons, forms);

        layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setWidth("30%");

        signUpButton.addClickListener(event -> {
            signUpForm.setVisible(true);
            loginForm.setVisible(false);
        });

        loginButton.addClickListener(event -> {
            loginForm.setVisible(true);
            signUpForm.setVisible(false);
        });

        add(layout);
    }
}
