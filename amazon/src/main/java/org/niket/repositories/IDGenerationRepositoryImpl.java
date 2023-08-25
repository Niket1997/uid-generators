package org.niket.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.niket.db.HikariConnectionPool;
import org.niket.enums.Service;
import org.niket.interfaces.IDGenerationRepository;
import org.niket.records.UIDCount;
import org.niket.records.UIDRange;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IDGenerationRepositoryImpl implements IDGenerationRepository {
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Override
    public UIDRange getUIDRange(Service service, int limit) throws SQLException {
        Connection connection = HikariConnectionPool.getConnection();
        connection.setAutoCommit(false);

        String getLastIDQuery = String.format("SELECT * FROM uid_counts WHERE service = \"%s\" FOR UPDATE;", service);
        ResultSet resultSet = connection.createStatement().executeQuery(getLastIDQuery);
        if (!resultSet.next()) throw new RuntimeException("service not found");

        UIDCount count = new UIDCount(
                resultSet.getInt("id"),
                Service.valueOf(resultSet.getString("service")),
                resultSet.getLong("count")
        );

        logger.info(String.format("id: %d, service: %s, count: %d",
                count.id(),
                count.service(),
                count.count()));

        long nextOffset = count.count() + limit;

        String updateCountQuery = String.format("UPDATE uid_counts SET count = \"%d\" WHERE service = \"%s\";", nextOffset, service);
        connection.prepareStatement(updateCountQuery).executeUpdate();
        connection.commit();

        connection.close();
        return new UIDRange(count.count(), nextOffset - 1);
    }
}
