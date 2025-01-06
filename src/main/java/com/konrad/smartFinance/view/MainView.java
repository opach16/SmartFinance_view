package com.konrad.smartFinance.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.RouterLayout;

@Layout
public class MainView extends AppLayout implements RouterLayout {

    public MainView() {
        addToNavbar(Navbar.getTitle(), Navbar.getNavigation());
    }
}