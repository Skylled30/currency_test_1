package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyExchangeRepositorySqliteImpl extends Database implements CurrencyExchangeRepository{


    protected CurrencyExchangeRepositorySqliteImpl() throws SQLException {
        super();
    }

    @Override
    public CurrencyExchange findById(int id) {
        return null;
    }

    @Override
    public List<CurrencyExchange> findAll() {
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<CurrencyExchange> products = new ArrayList<>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CurrencyExchange");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                products.add(new CurrencyExchange(
                        resultSet.getDouble("value"),
                        resultSet.getInt("nominal"),
                        resultSet.getString("currency_name"),
                        resultSet.getString("currency_code"),
                        resultSet.getDate("date")));
            }
            // Возвращаем наш список
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    @Override
    public List<CurrencyExchange> findAllByCode(String currencyCode) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public int insert(CurrencyExchange currency) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO CurrencyExchange(value, nominal, currency_name, currency_code, date) " +
                        "VALUES(?, ?, ?, ?, ?)")) {
            statement.setObject(1, currency.getValue());
            statement.setObject(2, currency.getNominal());
            statement.setObject(3, currency.getCurrencyName());
            statement.setObject(4, currency.getCurrencyCode());
            statement.setObject(5, currency.getDate());

            statement.execute();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(CurrencyExchange currency) {
        return 0;
    }
}