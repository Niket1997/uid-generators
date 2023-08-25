package org.niket.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.niket.enums.Client;
import org.niket.interfaces.IDGenerationService;
import org.niket.records.UIDRange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class IDGenerationController {
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    private final IDGenerationService idGenerationService;

    public IDGenerationController(IDGenerationService idGenerationService) {
        this.idGenerationService = idGenerationService;
    }

    @GetMapping("v1/uid/range")
    public UIDRange getUIDRange(@RequestParam("client") Client client, @RequestParam(value = "limit", defaultValue = "500") int limit) throws SQLException {
        logger.info(String.format("Received getUIDRange request for client %s with limit %d", client, limit));
        return idGenerationService.getUIDRange(client, limit);
    }
}
