package com.konrad.smartFinance.view;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.CryptoRates;
import com.konrad.smartFinance.domain.CryptoTransaction;
import com.konrad.smartFinance.service.CryptoService;
import com.konrad.smartFinance.service.CryptoTransactionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

@Route("crypto")
public class Crypto extends VerticalLayout {

    private final CryptoTransactionService cryptoTransactionService = new CryptoTransactionService(new SmartFinanceClient(new RestTemplate()));
    private final CryptoService cryptoService = new CryptoService(new SmartFinanceClient(new RestTemplate()));
    private Grid<CryptoTransaction> mainGrid = new Grid<>(CryptoTransaction.class);
    private Grid<CryptoRates> ratesGrid = new Grid<>(CryptoRates.class);
    private TextField filter = new TextField();
    private Button assets = new Button("Assets");
    private Button rates =new Button("Rates");

    public Crypto() {
        filter.setPlaceholder("Filter");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);

        mainGrid.setColumns("transactionDate", "name", "symbol", "amount", "price", "transactionValue", "currentValue");
        mainGrid.setItems(cryptoTransactionService.getCryptoTransactions());
        mainGrid.setSizeFull();

        ratesGrid.setColumns("name", "symbol", "price");
        ratesGrid.setItems(cryptoService.getCryptoRates());
        ratesGrid.setSizeFull();
        ratesGrid.setVisible(false);

        HorizontalLayout toolbar = new HorizontalLayout(filter, assets, rates);
        toolbar.setAlignItems(Alignment.END);

        HorizontalLayout mainContent = new HorizontalLayout(mainGrid, ratesGrid);

        mainContent.setSizeFull();
        mainContent.setFlexGrow(1, mainGrid);

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        rates.addClickListener(event -> {
            mainGrid.setVisible(false);
            ratesGrid.setVisible(true);
        });

        assets.addClickListener(event -> {
            ratesGrid.setVisible(false);
            mainGrid.setVisible(true);
        });

        add(toolbar, mainContent);
    }
}
