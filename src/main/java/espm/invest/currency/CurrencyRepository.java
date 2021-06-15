package espm.invest.currency;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<CurrencyModel, String>{
    @Override
    Iterable<CurrencyModel> findAll();

    @Override
    Optional<CurrencyModel> findById(String c);

    @Query("SELECT c from CurrencyModel c WHERE c.idCurrency = :idCurrency AND c.date <= :date ORDER BY c.date DESC")
    List<CurrencyModel> listByCurrencyDate(
            @Param("idCurrency") String idCurrency,
            @Param("date") Date date);

    @Query("SELECT c FROM CurrencyModel c " +
            "WHERE " +
            "(c.idCurrency is null or c.idCurrency = :idCurrency) AND " +
            "(c.date is null or c.date >= :dtInicio) AND " +
            "(c.date is null or c.date <= :dtFim)"
    )
    List<CurrencyModel> listBy(
            @Param("idCurrency") String idCurrency,
            @Param("dtInicio") Date dtInicio,
            @Param("dtFim") Date dtFim
    );

    @Query("SELECT m from CurrencyModel m WHERE UPPER(m.name) = UPPER(:name) ORDER BY m.date desc")
    List<CurrencyModel> findByName(@Param("name") String name);
}
