package se.iths.service;

import se.iths.entity.Teacher;
import se.iths.rest.exception.NotFoundException;

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
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public Teacher findTeacherById(Long id) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        if (foundTeacher == null) {
            throw new NotFoundException("{\"message\":\"Teacher with id " + id + " not found.\"}");
        }
        return foundTeacher;
    }

    public List<Teacher> findTeacherByLastName(String lastName) {
        return entityManager.createQuery("select t from Teacher t where t.lastName like :lastname", Teacher.class)
                .setParameter("lastname", lastName)
                .getResultList();
    }

    public List<Teacher> getAllTeachers() {
        return entityManager.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }

    public Teacher replaceTeacher(Long id, Teacher teacher) {
        Teacher foundTeacher = findTeacherById(id);

        if (teacher != null) {
            foundTeacher.setFirstName(teacher.getFirstName());
            foundTeacher.setLastName(teacher.getLastName());
            foundTeacher.setEmail(teacher.getEmail());
            foundTeacher.setPhoneNumber(teacher.getPhoneNumber());
        }
        entityManager.merge(foundTeacher);
        return foundTeacher;
    }

    public Teacher updateEmail(Long id, String email) {
        Teacher foundTeacher = findTeacherById(id);
        foundTeacher.setEmail(email);
        return foundTeacher;
    }

    public void deleteTeacher(Long id) {
        Teacher foundTeacher = findTeacherById(id);
        foundTeacher.getSubjects().forEach(s -> s.setTeacher(null));
        entityManager.remove(foundTeacher);
    }
}
