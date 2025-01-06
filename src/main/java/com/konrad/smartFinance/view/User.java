package com.konrad.smartFinance.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("user")
public class User extends VerticalLayout {

    public User() {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        FormLayout formLayout = new FormLayout();
        formLayout.setWidth("30%");

        TextField email = new TextField("E-Mail");
        email.setWidth("100%");
        email.setPlaceholder("E-Mail");

        TextField username = new TextField("Username");
        username.setWidth("100%");
        username.setPlaceholder("Username");

        PasswordField password = new PasswordField("Password");
        password.setWidth("100%");
        password.setPlaceholder("Password");

        Button signIn = new Button("Sign in");
        signIn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button signUp = new Button("Sign up");

        formLayout.add(email, username, password, signIn, signUp);
        horizontalLayout.add(formLayout);
        add(horizontalLayout);
    }
}
