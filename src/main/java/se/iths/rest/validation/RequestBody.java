package se.iths.rest.validation;

import se.iths.rest.exception.BadRequestException;

public class RequestBody {

    public void invalidRequestBody (String firstName, String lastName, String email) {
        if (firstName == null || firstName.isEmpty()) {
            throw new BadRequestException("{\"message\":\"First name is required.\"}");
        }

        if (lastName == null || lastName.isEmpty()) {
            throw new BadRequestException("{\"message\":\"Last name is required.\"}");
        }

        if (email == null || email.isEmpty()) {
            throw new BadRequestException("{\"message\":\"Email is required.\"}");
        }
    }
}
