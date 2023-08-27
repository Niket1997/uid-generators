package org.niket.repositories;

import org.niket.db.HikariConnectionPool;
import org.niket.exceptions.NoDataFoundException;
import org.niket.interfaces.TicketRepository;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TicketRepositoryImpl implements TicketRepository {
    @Override
    public int getUID() throws SQLException {
        Connection connection = HikariConnectionPool.getConnection();
        connection.setAutoCommit(false);
        String updateQuery = "INSERT INTO tickets (stub) VALUES ('a') ON DUPLICATE KEY UPDATE id = id + 1;";
        connection.createStatement().executeUpdate(updateQuery);

        String readQuery = "SELECT id FROM tickets LIMIT 1;";
        ResultSet resultSet = connection.createStatement().executeQuery(readQuery);
        if (!resultSet.next()) throw new NoDataFoundException();

        int id = resultSet.getInt("id");
        connection.commit();
        connection.close();
        return id;
    }
}
