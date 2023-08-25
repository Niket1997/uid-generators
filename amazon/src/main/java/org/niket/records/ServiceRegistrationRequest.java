package org.niket.records;

import org.niket.enums.Client;

public record ServiceRegistrationRequest(
        Client client,
        Long offset
) {
}
