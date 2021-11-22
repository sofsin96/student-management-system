package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.rest.exception.BadRequestException;
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

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {

        // TODO: extract method

        if (teacher.getFirstName() == null || teacher.getFirstName().isEmpty()) {
            throw new BadRequestException("{\"message\":\"First name is required.\"}");
        }

        if (teacher.getLastName() == null || teacher.getLastName().isEmpty()) {
            throw new BadRequestException("{\"message\":\"Last name is required.\"}");
        }

        if (teacher.getEmail() == null || teacher.getEmail().isEmpty()) {
            throw new BadRequestException("{\"message\":\"Email is required.\"}");
        }
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
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"lastname parameter is mandatory.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }

        if (teacherService.findTeacherByLastName(lastName).isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Could not find teacher with last name " + lastName + ".\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(teacherService.findTeacherByLastName(lastName)).build();
    }

    @Path("{id}")
    @PUT
    public Response replaceTeacher(@PathParam("id") Long id, Teacher teacher) {
        // TODO: validate fields
        return Response.ok(teacherService.replaceTeacher(id, teacher)).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        if (email.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"email parameter is mandatory.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
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
