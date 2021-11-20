package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.rest.exception.BadRequestException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        if (subject.getName() == null || subject.getName().isEmpty()) {
            throw new BadRequestException("{\"message\":\"Name is required.\"}");
        }
        subjectService.createSubject(subject);
        return Response.status(Response.Status.CREATED).entity(subject).build();
    }

    @Path("{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id) {
        return Response.ok(subjectService.findSubjectById(id)).build();
    }

    @Path("")
    @GET
    public Response getAllSubjects() {
        return Response.ok(subjectService.getAllSubjects()).build();
    }

    @Path("getAllByLastName")
    @GET
    public Response getAllSubjectsByName(@QueryParam("name") String name) {
        if (name.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"name parameter is mandatory.\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }

        if (subjectService.findSubjectByName(name).isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Could not find subject with name " + name + ".\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(subjectService.findSubjectByName(name)).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        subjectService.deleteSubject(id);
        return Response.ok().build();
    }
}