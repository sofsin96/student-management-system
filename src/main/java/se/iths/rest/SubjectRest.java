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

    @Path("getAllByName")
    @GET
    public Response getAllSubjectsByName(@QueryParam("name") String name) {
        if (name.isEmpty()) {
            throw new BadRequestException("{\"message\":\"name parameter is mandatory.\"}");
        }

        if (subjectService.findSubjectByName(name).isEmpty()) {
            throw new BadRequestException("{\"message\":\"Could not find subject with name " + name + ".\"}");
        }
        return Response.ok(subjectService.findSubjectByName(name)).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        subjectService.deleteSubject(id);
        return Response.ok().build();
    }

    @Path("{id}/add/student/{studentID}")
    @POST
    public Subject addStudent(@PathParam("id") Long id, @PathParam("studentID") Long studentID) {
        return subjectService.addStudent(id, studentID);
    }

    @Path("{id}/delete/student/{studentID}")
    @POST
    public Response deleteStudent(@PathParam("id") Long id, @PathParam("studentID") Long studentID) {
        Subject subject = subjectService.deleteStudent(id, studentID);
        return Response.ok(subject).build();
    }

    @Path("{id}/add/teacher/{teacherID}")
    @POST
    public Subject addTeacher(@PathParam("id") Long id, @PathParam("teacherID")  Long teacherID) {
        return subjectService.addTeacher(id, teacherID);
    }

    @Path("{id}/delete/teacher/{teacherID}")
    @POST
    public Response deleteTeacher(@PathParam("id") Long id, @PathParam("teacherID") Long teacherID) {
        Subject subject = subjectService.deleteTeacher(id, teacherID);
        return Response.ok(subject).build();
    }
}
