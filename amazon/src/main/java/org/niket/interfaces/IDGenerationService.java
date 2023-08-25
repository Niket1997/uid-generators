package org.niket.interfaces;

import org.niket.enums.Service;
import org.niket.records.UIDRange;

import java.sql.SQLException;

public interface IDGenerationService {
    UIDRange getUIDRange(Service service, int limit) throws SQLException;
}
