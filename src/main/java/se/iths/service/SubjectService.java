package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.rest.exception.NotFoundException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    StudentService studentService;

    @Inject
    TeacherService teacherService;

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public Subject findSubjectById(Long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        if (foundSubject == null) {
            throw new NotFoundException("{\"message\":\"Subject with id " + id + " not found.\"}");
        }
        return foundSubject;
    }

    public List<Subject> findSubjectByName(String name) {
        return entityManager.createQuery("select s from Subject s where s.name like :name", Subject.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Subject> getAllSubjects() {
        return entityManager.createQuery("select s from Subject s", Subject.class).getResultList();
    }

    public void deleteSubject(Long id) {
        Subject foundSubject = findSubjectById(id);
        foundSubject.getStudents().forEach(s-> s.getSubjects().remove(foundSubject));
        entityManager.remove(foundSubject);
    }

    public Subject addStudent(Long id, Long studentID) {
        Subject foundSubject = findSubjectById(id);
        Student foundStudent = studentService.findStudentById(studentID);
        foundSubject.addStudent(foundStudent);
        entityManager.persist(foundSubject);
        return foundSubject;
    }
    public Subject deleteStudent(Long id, Long studentID) {
        Subject foundSubject = findSubjectById(id);
        Student foundStudent = studentService.findStudentById(studentID);
        foundSubject.removeStudent(foundStudent);
        entityManager.persist(foundSubject);
        return foundSubject;
    }

    public Subject addTeacher(Long id, Long teacherID) {
        Subject foundSubject = findSubjectById(id);
        Teacher foundTeacher = teacherService.findTeacherById(teacherID);
        foundSubject.setTeacher(foundTeacher);
        foundTeacher.addSubject(foundSubject);
        return foundSubject;
    }

    public Subject deleteTeacher(Long id, Long teacherID) {
        Subject foundSubject = findSubjectById(id);
        Teacher foundTeacher = teacherService.findTeacherById(teacherID);
        foundSubject.setTeacher(null);
        foundTeacher.removeSubject(foundSubject);
        entityManager.persist(foundSubject);
        return foundSubject;
    }
}
