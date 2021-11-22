package se.iths.rest;

import se.iths.entity.Student;
import se.iths.rest.exception.BadRequestException;
import se.iths.rest.validation.RequestBody;
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

    @Inject
    RequestBody requestBody;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        requestBody.invalidRequestBody(student.getFirstName(), student.getLastName(), student.getEmail());
        studentService.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
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
        if (lastName.isEmpty()) {
            throw new BadRequestException("{\"message\":\"lastname parameter is mandatory.\"}");
        }

        if (studentService.findStudentByLastName(lastName).isEmpty()) {
            throw new BadRequestException("{\"message\":\"Could not find student with last name " + lastName + ".\"}");
        }
        return Response.ok(studentService.findStudentByLastName(lastName)).build();
    }

    @Path("{id}")
    @PUT
    public Response replaceStudent(@PathParam("id") Long id, Student student) {
        requestBody.invalidRequestBody(student.getFirstName(), student.getLastName(), student.getEmail());
        return Response.ok(studentService.replaceStudent(id, student)).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        if (email.isEmpty()) {
            throw new BadRequestException("{\"message\":\"email parameter is mandatory.\"}");
        }
        return Response.ok(studentService.updateEmail(id, email)).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}
