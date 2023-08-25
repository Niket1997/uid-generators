package org.niket.interfaces;

import org.niket.enums.Client;
import org.niket.records.UIDRange;

import java.sql.SQLException;

public interface IDGenerationService {
    UIDRange getUIDRange(Client client, int limit) throws SQLException;
}
