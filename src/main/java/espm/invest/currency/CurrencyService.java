package espm.invest.currency;

import espm.invest.currency.common.datatype.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CurrencyService {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> listAll() {
        return StreamSupport
                .stream(currencyRepository.findAll().spliterator(), false)
                .collect(Collectors.toList())
                .stream().map(CurrencyModel::to)
                .collect(Collectors.toList());
    }

    public Currency findById(UUID id) {
        return currencyRepository.findById(id.toString()).map(currencyModel -> currencyModel.to()).orElse(null);
    }

    public Currency findByName(String name) {
        return currencyRepository
                .findByName(name)
                .map(CurrencyModel::to)
                .orElse(null);
    }

    public Currency create(Currency currency) {
        currency.setId(UUID.randomUUID().toString());
        return currencyRepository.save(new CurrencyModel(currency)).to();
    }

    public Currency findBy(String idCurrency, Date date) {
        return currencyRepository
                .listByCurrencyDate(idCurrency, date).stream()
                .map(CurrencyModel::to)
                .findFirst()
                .orElse(null);
    }

    public List<Currency> listBy(String idCurrency, Date dtInicio, Date dtFim) {
        return currencyRepository
                .listBy(idCurrency, dtInicio, dtFim).stream()
                .map(CurrencyModel::to)
                .collect(Collectors.toList());
    }

    public void delete(UUID id) {
        currencyRepository.deleteById(id.toString());
    }
}
