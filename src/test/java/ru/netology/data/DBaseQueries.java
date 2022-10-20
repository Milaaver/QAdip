package ru.netology.data;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.checkerframework.checker.units.qual.C;

import java.sql.DriverManager;

public class DBaseQueries {
//    private static String url = "jdbc:mysql://localhost:3306/app_db";
    private static String url = System.getProperty("datasource.url");
    private static String user = "user";
    private static String password = "password";

    @Data
    public static class OrderEntity {
        String credit_id;
        String payment_id;
    }

    @SneakyThrows
    public static OrderEntity getOrder() {
        var ordersSQL = "SELECT credit_id, payment_id FROM order_entity ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection(url, user, password)) {
            return runner.query(conn, ordersSQL, new BeanHandler<>(OrderEntity.class));
        }
    }

    @Data
    public static class PaymentEntity {
        String status;
        String transaction_id;
    }

    @SneakyThrows
    public static PaymentEntity getPayment() {
        var paymentSQL = "SELECT status, transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection(url, user, password)) {
            return runner.query(conn, paymentSQL, new BeanHandler<>(PaymentEntity.class));
        }
    }

    @Data
    public static class CreditRequestEntity {
        String bank_id;
        String status;
    }

    @SneakyThrows
    public static CreditRequestEntity getCredit() {
        var creditSQL = "SELECT bank_id, status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection(url, user, password)) {
            return runner.query(conn, creditSQL, new BeanHandler<>(CreditRequestEntity.class));
        }
    }

    @SneakyThrows
    public static void clearAllData() {
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection(url, user, password)) {
            runner.execute(conn, "TRUNCATE credit_request_entity");
            runner.execute(conn, "TRUNCATE payment_entity");
            runner.execute(conn, "TRUNCATE order_entity");
        }
    }
}
