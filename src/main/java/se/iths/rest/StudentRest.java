package se.iths.rest;

import se.iths.entity.Student;
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
        requestBody.invalidRequestBody(student);
        studentService.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
       /* if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Student with id " + id + " not found.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }*/
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
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"lastname parameter is mandatory.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }

        if (studentService.findStudentByLastName(lastName).isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Could not find student with last name " + lastName + ".\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(studentService.findStudentByLastName(lastName)).build();
    }

    @Path("{id}")
    @PUT
    public Response replaceStudent(@PathParam("id") Long id, Student student) {
        /*if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Student with id " + id + " not found.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }*/
        requestBody.invalidRequestBody(student);
        return Response.ok(studentService.replaceStudent(id, student)).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        /*if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Student with id " + id + " not found.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }*/

        // TODO: check when id does not exist and email is null.

        if (email.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"email parameter is mandatory.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(studentService.updateEmail(id, email)).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        /*if (studentService.findStudentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\":\"Student with id " + id + " not found.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }*/
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}
