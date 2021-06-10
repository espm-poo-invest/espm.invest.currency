package espm.invest.currency;

import espm.invest.currency.common.datatype.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="currency")
public class CurrencyModel {

    @Id
    @Column(name="id_currency")
    private String idCurrency;
    @Column(name="txt_name")
    private String name;
    @Column(name="c_date")
    private Date date;
    private double price;

    public CurrencyModel(){

    }

    public CurrencyModel(Currency c){
        this.idCurrency = c.getId().toString();
        this.name = c.getName();
        this.date = c.getDate();
        this.price = c.getPrice();
    }

    public Currency to(){
        Currency c = new Currency();
        c.setId(UUID.fromString(idCurrency));
        c.setName(name);
        c.setDate(date);
        c.setPrice(price);
        return c;
    }

}
