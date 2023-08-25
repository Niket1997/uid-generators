package org.niket.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.niket.records.ServiceRegistrationRequest;
import org.niket.records.ServiceRegistrationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRegistrationController {
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @PostMapping("v1/client/register")
    public ServiceRegistrationResponse registerClient(@RequestBody ServiceRegistrationRequest request) {
        return null;
    }
}
