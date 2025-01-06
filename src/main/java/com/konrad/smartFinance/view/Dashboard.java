package com.konrad.smartFinance.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("dashboard")
public class Dashboard extends VerticalLayout {

    private H1 balance = new H1("Balance");
    private H1 totalBalance = new H1("Total Balance");

    public Dashboard() {
        balance.getStyle().set("background-color", "#888888");
        balance.getStyle().set("text-align", "center");
        balance.getStyle().set("border-radius", "20px");
        balance.getStyle().set("padding", "20px");

        totalBalance.getStyle().set("background-color", "#888888");
        totalBalance.getStyle().set("text-align", "center");
        totalBalance.getStyle().set("border-radius", "20px");
        totalBalance.getStyle().set("padding", "20px");

        HorizontalLayout horizontalLayout = new HorizontalLayout(balance, totalBalance);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        add(horizontalLayout);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
