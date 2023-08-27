package org.niket.interfaces;

import java.sql.SQLException;

public interface TicketRepository {
    int getUID() throws SQLException;
}
