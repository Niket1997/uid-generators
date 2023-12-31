package org.niket.records;

import org.niket.enums.Client;

public record UIDCount(
        int id,
        Client client,
        Long count
) {
}
