package espm.invest.currency;

import espm.invest.currency.common.controller.CurrencyController;
import espm.invest.currency.common.datatype.Currency;
import espm.invest.currency.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class CurrencyResource implements CurrencyController{

    @Autowired
    private CurrencyService currencyService;

    @Override
    public List<Currency> currency() {
        return currencyService.listAll();
    }

    @Override
    public Currency currency(String id) {
        return null;
    }

    @Override
    public Currency deleteById(String id) {
        return null;
    }

    @Override
    public Currency save(Currency currency) {
        return null;
    }
}
