package espm.invest.currency;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<CurrencyModel, String>{
    @Override
    Iterable<CurrencyModel> findAll();

    @Override
    Optional<CurrencyModel> findById(String c);
}
