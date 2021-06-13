drop database dbcurrency;
create database dbcurrency;
use dbcurrency;

CREATE TABLE currency (
    id_currency varchar(36) NOT NULL,
    txt_name varchar(10) NOT NULL,
    c_date date NOT NULL,
    price double NOT NULL,
    PRIMARY KEY (id_currency)
);

insert into currency(id_currency, txt_name, c_date, price) values (uuid(), 'USD', '1999-07-29', 1);
select * from currency;