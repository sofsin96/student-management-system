package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.rest.exception.StudentNotFoundException;

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

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public Subject findSubjectById(Long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        if (foundSubject == null) {
            throw new StudentNotFoundException("{\"message\":\"Subject with id " + id + " not found.\"}");
            // TODO: create NotFoundException class
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
        entityManager.remove(foundSubject);
    }

    // TODO: get subject (information, students and teacher)
}
