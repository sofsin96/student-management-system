package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Mandatory field is missing.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }

        // TODO: check if lastname and email is null or empty.

        studentService.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Student with id " + id + " not found.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(studentService.findStudentById(id)).build();
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        return Response.ok(studentService.getAllStudents()).build();
    }

    @Path("getAllByLastName")
    @GET
    public Response getAllStudentsByLastName(@QueryParam("lastname") String lastName) {

        // TODO: json response when the lastname was not found.
        // TODO: add equalsIgnoreCase

        if (lastName.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                            .entity("{\"message\":\"lastName parameter is mandatory.\"}")
                            .type(MediaType.APPLICATION_JSON)
                            .build());
        }
        return Response.ok(studentService.findStudentByLastName(lastName)).build();
    }

    @Path("{id}")
    @PUT
    public Response replaceStudent(@PathParam("id") Long id, Student student) {
        // TODO: json response when mandatory field is null or empty

        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Student with id " + id + " not found.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(studentService.replaceStudent(id, student)).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        // TODO: check if email parameter is null or empty -> json response

        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Student with id " + id + " not found.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(studentService.updateEmail(id, email)).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Student with id " + id + " not found.\"}")
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build());
        }
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}
