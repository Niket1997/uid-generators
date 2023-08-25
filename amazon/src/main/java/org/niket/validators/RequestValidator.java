package org.niket.validators;

import org.niket.constants.RequestConstants;
import org.niket.enums.Service;
import org.niket.exceptions.ValidationFailedException;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {
    public void validateGetUIDRangeRequest(int limit) throws ValidationFailedException {

        if (limit > RequestConstants.GET_UID_RANGE_MAX_LIMIT) {
            throw new ValidationFailedException(String.format("max allowed limit is %d", RequestConstants.GET_UID_RANGE_MAX_LIMIT));
        }
    }
}
