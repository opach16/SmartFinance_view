package com.konrad.smartFinance.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class Navbar extends HorizontalLayout {

    public static HorizontalLayout getNavigation() {
        HorizontalLayout navigation = new HorizontalLayout();
        navigation.setWidthFull();
        navigation.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        navigation.setAlignItems(FlexComponent.Alignment.CENTER);

        RouterLink dashboardLink = new RouterLink("Dashboard", Dashboard.class);
        RouterLink assetsLink = new RouterLink("Assets", Assets.class);
        RouterLink transactionsLink = new RouterLink("Transactions", Transactions.class);
        RouterLink ratesLink = new RouterLink("Rates", Rates.class);
        RouterLink loginLink = new RouterLink("Login", Login.class);

        Tab dashboard = new Tab(dashboardLink);
        Tab assets = new Tab(assetsLink);
        Tab transactions = new Tab(transactionsLink);
        Tab rates = new Tab(ratesLink);
        Tab login = new Tab(loginLink);
        Tabs navigationTabs = new Tabs(dashboard, assets, transactions, rates, login);

        navigation.add(navigationTabs);
        return navigation;
    }

    public static H1 getTitle() {
        H1 title = new H1("SmartFinance");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)").set("margin", "0")
                .set("position", "absolute");
        return title;
    }
}
