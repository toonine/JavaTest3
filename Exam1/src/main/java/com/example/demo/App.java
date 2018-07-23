package com.example.demo;

import java.sql.*;

public class App {
    private static final String USERNAME = System.getenv("DB_USERNAME");
    private static final String HOST_IP = System.getenv("DB_HOST_IP");
    private static final String PORT = System.getenv("DB_PORT");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    private static final String SCHEMA = System.getenv("DB_SCHEME");
    private static final String URL = "jdbc:mysql://" + HOST_IP + ":" + PORT + "/" + SCHEMA + "?useSSL=false";

    private static final int COUNTRY_ID = Integer.parseInt("1");
    private static final int CUSTOMER_ID = Integer.parseInt("1");


    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT city, city_id, country FROM city INNER JOIN country on city.country_id = country.country_id WHERE country.country_id = " + COUNTRY_ID);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    System.out.println("Country ID: " + COUNTRY_ID);
                    System.out.println("Country " + resultSet.getString("country") + " 的城市");
                    System.out.println("城市 ID \t| 城市名称\t");
                }
                System.out.print(resultSet.getInt("city_id") + "\t|");
                System.out.println(resultSet.getString("city") + "\t");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n-----------------------------------------------\n");
        System.out.println("Customer ID: " + CUSTOMER_ID);
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT first_name, last_name, film.film_id, title, rental_date FROM film INNER JOIN inventory on film.film_id = inventory.film_id INNER JOIN rental r on inventory.inventory_id = r.inventory_id INNER JOIN customer c on r.customer_id = c.customer_id WHERE r.customer_id = " + CUSTOMER_ID + " ORDER BY rental_date desc ");
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name") + "租用的Film");
                    System.out.println("Film ID | Film 名称 | 租用时间");
                }
                System.out.print(resultSet.getInt("film_id") + "\t|");
                System.out.print(resultSet.getString("title") + "\t|");
                System.out.println(resultSet.getString("rental_date") + "\t");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
