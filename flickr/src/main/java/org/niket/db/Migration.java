package org.niket.db;

import org.niket.utils.FileLoader;

import java.sql.Connection;

public class Migration {
    public static void reset() throws Exception {
        Connection connection = HikariConnectionPool.getConnection();
        String queryDropTableUIDCounts = "DROP TABLE IF EXISTS tickets;";
        connection.createStatement().executeUpdate(queryDropTableUIDCounts);

        String queryCreateTicketsTable = FileLoader.loadFromFile("flickr/src/main/resources/db.migrations/create_table_tickets.sql");
        connection.createStatement().executeUpdate(queryCreateTicketsTable);

        String queryInsertPaymentsServiceInUIDCountsTable = "INSERT INTO tickets (id, stub) VALUES (0, 'a');";
        connection.createStatement().executeUpdate(queryInsertPaymentsServiceInUIDCountsTable);
        connection.close();
    }
}
