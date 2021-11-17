package se.iths.rest.validation;

import se.iths.entity.Student;
import se.iths.rest.exception.BadRequestException;

public class RequestBody {

    public void invalidRequestBody (Student student) {
        if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
            throw new BadRequestException("{\"message\":\"First name is required.\"}");
        }

        if (student.getLastName() == null || student.getLastName().isEmpty()) {
            throw new BadRequestException("{\"message\":\"Last name is required.\"}");
        }

        if (student.getEmail() == null || student.getEmail().isEmpty()) {
            throw new BadRequestException("{\"message\":\"Email is required.\"}");
        }
    }
}
