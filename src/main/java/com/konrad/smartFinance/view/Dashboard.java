package com.konrad.smartFinance.view;

import com.konrad.smartFinance.domain.Account;
import com.konrad.smartFinance.service.AccountService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("/")
public class Dashboard extends VerticalLayout {

    private final AccountService accountService;

    private final H1 balance = new H1("Debit Balance");
    private final H1 assetsBalance = new H1("Assets Balance");
    private final H1 totalBalance = new H1("Total Balance");
    private H1 balanceValue = new H1();
    private H1 assetsValue = new H1();
    private H1 totalBalanceValue = new H1();

    public Dashboard(AccountService accountService) {
        this.accountService = accountService;

        Div balanceDiv = new Div(balance, balanceValue);
        balanceDiv.getStyle().set("background-color", "#888888");
        balanceDiv.getStyle().set("text-align", "center");
        balanceDiv.getStyle().set("border-radius", "25px");
        balanceDiv.getStyle().set("padding", "25px");

        Div assetsBalanceDiv = new Div(assetsBalance, assetsValue);
        assetsBalanceDiv.getStyle().set("background-color", "#888888");
        assetsBalanceDiv.getStyle().set("text-align", "center");
        assetsBalanceDiv.getStyle().set("border-radius", "25px");
        assetsBalanceDiv.getStyle().set("padding", "25px");

        Div totalBalanceDiv = new Div(totalBalance, totalBalanceValue);
        totalBalanceDiv.getStyle().set("background-color", "#888888");
        totalBalanceDiv.getStyle().set("text-align", "center");
        totalBalanceDiv.getStyle().set("border-radius", "25px");
        totalBalanceDiv.getStyle().set("padding", "25px");

        HorizontalLayout horizontalLayout = new HorizontalLayout(balanceDiv, assetsBalanceDiv, totalBalanceDiv);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        add(horizontalLayout);
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (VaadinSession.getCurrent().getAttribute("username") == null) {
            UI.getCurrent().navigate("login");
        } else {
            refresh();
        }
    }

    public void refresh() {
        Account account = accountService.getAccount();
        String symbol = account.getMainCurrency().getSymbol();
        balanceValue.setText(account.getMainBalance().toString() + " " + symbol);
        assetsValue.setText(account.getAssetsBalance().toString() + " " + symbol);
        totalBalanceValue.setText(account.getTotalBalance().toString() + " " + symbol);
    }
}
