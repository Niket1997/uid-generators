package org.niket.db;

import org.niket.utils.FileLoader;

import java.sql.Connection;

public class Migration {
    public static void reset() throws Exception {
        Connection connection = HikariConnectionPool.getConnection();
        String queryDropTableUIDCounts = "DROP TABLE IF EXISTS uid_counts;";
        connection.createStatement().executeUpdate(queryDropTableUIDCounts);

        String queryCreateUIDCountsTable = FileLoader.loadFromFile("amazon/src/main/resources/db.migrations/create_uid_counts.sql");
        connection.createStatement().executeUpdate(queryCreateUIDCountsTable);

        String queryInsertPaymentsServiceInUIDCountsTable = "INSERT INTO uid_counts (client) VALUES ('PAYMENTS');";
        connection.createStatement().executeUpdate(queryInsertPaymentsServiceInUIDCountsTable);
        connection.close();
    }
}
