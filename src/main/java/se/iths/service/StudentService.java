package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
        entityManager.persist(student);
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> findStudentByLastName(String lastName) {
        return entityManager.createQuery("select s from Student s where s.lastName like :lastname", Student.class)
                .setParameter("lastname", lastName)
                .getResultList();
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("select s from Student s", Student.class).getResultList();
    }

    public Student replaceStudent(Long id, Student student) {
        Student foundStudent = entityManager.find(Student.class, id);

        // TODO: check if mandatory field is null or empty

        if (student != null) {
            foundStudent.setFirstName(student.getFirstName());
            foundStudent.setLastName(student.getLastName());
            foundStudent.setEmail(student.getEmail());
            foundStudent.setPhoneNumber(student.getPhoneNumber());
        }

        entityManager.merge(foundStudent);
        return foundStudent;
    }

    public Student updateEmail(Long id, String email) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setEmail(email);
        return foundStudent;
    }

    public void deleteStudent(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
    }
}
