package org.example;

import java.time.LocalDate;
import java.util.Date;

public class CurrencyExchange {
    private int id;
    private Double value;
    private Integer nominal;
    private String currencyName;
    private String currencyCode;
    private Date date;

    private static int idCurrency = 0;

    public CurrencyExchange(Double value, Integer nominal, String currencyName, String currencyCode, Date date) {
        this.id = idCurrency;
        this.value = value;
        this.nominal = nominal;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.date = date;
        idCurrency++;
    }

    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "id=" + id +
                ", value=" + value +
                ", nominal=" + nominal +
                ", currencyName='" + currencyName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public Integer getNominal() {
        return nominal;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Date getDate() {
        return date;
    }

    public static int getIdCurrency() {
        return idCurrency;
    }
}
