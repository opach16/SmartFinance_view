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
        RouterLink transactionsLink = new RouterLink("Transactions", Transactions.class);
        RouterLink ratesLink = new RouterLink("Rates", Rates.class);
        RouterLink userLink = new RouterLink("User", User.class);

        Tab dashboard = new Tab(dashboardLink);
        Tab transactions = new Tab(transactionsLink);
        Tab rates = new Tab(ratesLink);
        Tab user = new Tab(userLink);
        Tabs navigationTabs = new Tabs(dashboard, transactions, rates, user);

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
