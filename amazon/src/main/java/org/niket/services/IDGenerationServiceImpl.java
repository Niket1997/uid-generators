package org.niket.services;

import org.niket.enums.Client;
import org.niket.interfaces.IDGenerationRepository;
import org.niket.interfaces.IDGenerationService;
import org.niket.records.UIDRange;
import org.niket.validators.RequestValidator;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class IDGenerationServiceImpl implements IDGenerationService {
    private final RequestValidator requestValidator;
    private final IDGenerationRepository repository;

    public IDGenerationServiceImpl(RequestValidator requestValidator, IDGenerationRepository repository) {
        this.requestValidator = requestValidator;
        this.repository = repository;
    }

    @Override
    public UIDRange getUIDRange(Client client, int limit) throws SQLException {
        requestValidator.validateGetUIDRangeRequest(limit);
        return repository.getUIDRange(client, limit);
    }
}
