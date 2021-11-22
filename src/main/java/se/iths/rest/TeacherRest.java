package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.rest.exception.BadRequestException;
import se.iths.rest.validation.RequestBody;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Inject
    RequestBody requestBody;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        requestBody.invalidRequestBody(teacher.getFirstName(), teacher.getLastName(), teacher.getEmail());
        teacherService.createTeacher(teacher);
        return Response.status(Response.Status.CREATED).entity(teacher).build();
    }

    @Path("{id}")
    @GET
    public Response getTeacher(@PathParam("id") Long id) {
        return Response.ok(teacherService.findTeacherById(id)).build();
    }

    @Path("")
    @GET
    public Response getAllTeachers() {
        return Response.ok(teacherService.getAllTeachers()).build();
    }

    @Path("getAllByLastName")
    @GET
    public Response getAllTeachersByLastName(@QueryParam("lastname") String lastName) {
        if (lastName.isEmpty()) {
            throw new BadRequestException("{\"message\":\"lastname parameter is mandatory.\"}");
        }

        if (teacherService.findTeacherByLastName(lastName).isEmpty()) {
            throw new BadRequestException("{\"message\":\"Could not find teacher with last name " + lastName + ".\"}");
        }
        return Response.ok(teacherService.findTeacherByLastName(lastName)).build();
    }

    @Path("{id}")
    @PUT
    public Response replaceTeacher(@PathParam("id") Long id, Teacher teacher) {
        requestBody.invalidRequestBody(teacher.getFirstName(), teacher.getLastName(), teacher.getEmail());
        return Response.ok(teacherService.replaceTeacher(id, teacher)).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        if (email.isEmpty()) {
            throw new BadRequestException("{\"message\":\"email parameter is mandatory.\"}");
        }
        return Response.ok(teacherService.updateEmail(id, email)).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        teacherService.deleteTeacher(id);
        return Response.ok().build();
    }
}
