package espm.invest.currency;

import espm.invest.currency.common.controller.CurrencyController;
import espm.invest.currency.common.datatype.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@EnableFeignClients(basePackages = {"espm.invest.currency.common.controller", "espm.invest.portfolio.common.controller"})
@RestController
public class CurrencyResource implements CurrencyController {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private CurrencyService currencyService;

    @Override
    public List<Currency> currency() {
        return currencyService.listAll();
    }

    @Override
    public Currency currency(String name) {
        return currencyService.findByName(name);
    }

    @Override
    public Currency currencyById(String id) {
        return currencyService.findById(UUID.fromString(id));
    }

    @Override
    public Currency currency(String name, String date) {
        try {
            Currency currency = currencyService.findByName(name);
            if (currency == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, name + " not found");
            }
            System.out.println(date);
            System.out.println(sdf.parse(date));
            return currencyService.findBy(currency.getId(), sdf.parse(date));
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public List<Currency> cotacoes(String name, String ini, String fim) {
        try {
            espm.invest.currency.common.datatype.Currency currency = currencyService.findByName(name);
            if (currency == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, name + " not found");
            }
            Date dtInicio = ini == null ? null : sdf.parse(ini);
            Date dtTermino = fim == null ? null : sdf.parse(fim);
            return currencyService.listBy(currency.getId(), dtInicio, dtTermino);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}