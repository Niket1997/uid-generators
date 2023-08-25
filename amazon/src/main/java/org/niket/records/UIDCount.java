package org.niket.records;

import org.niket.enums.Service;

public record UIDCount(
        int id,
        Service service,
        Long count
) {
}
