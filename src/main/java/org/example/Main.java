package org.example;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, SQLException {
        Post post = new Post();
        String result = post.getResponse("https://www.cbr.ru/scripts/XML_daily.asp"); // запрос по ссылке
        ValCurs valCurs = deserializeFromXML(result); // десереализация данных
        List<CurrencyExchange> currencyExchange = converterToCurrencyExchange(valCurs); // конвертация
        CurrencyExchangeRepositorySqliteImpl currExchange = new CurrencyExchangeRepositorySqliteImpl();

        for (CurrencyExchange currencyExchange2: currencyExchange){
            currExchange.insert(currencyExchange2);
        }

        System.out.println(currExchange.findAll());
    }

    public static List<CurrencyExchange> converterToCurrencyExchange(ValCurs valCurs) throws ParseException {
        List<CurrencyExchange> currencyExchangeList = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(valCurs.getDate());
        String nameValCurs = valCurs.getName();

        for(Valute valute: valCurs.getValuteList()){
            String id = valute.getID();
            int numCode = valute.getNumCode();
            String charCode = valute.getCharCode();
            int nominal = valute.getNominal();
            String name = valute.getName();
            double value = Double.parseDouble(valute.getValue().replace(",", ".")); // "123.543252"

            CurrencyExchange currencyExchange = new CurrencyExchange(value, nominal, name, charCode, date);
            currencyExchangeList.add(currencyExchange);
        }

        return currencyExchangeList;

    }

    public static ValCurs deserializeFromXML(String data) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            ValCurs deserializedData = xmlMapper.readValue(data, ValCurs.class);
            return deserializedData;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}