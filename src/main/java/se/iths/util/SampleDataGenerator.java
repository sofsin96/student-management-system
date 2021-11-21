package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {
        Student student1 = new Student("Sofia", "Rodriguez", "sofia@iths.se");
        Student student2 = new Student("Gabrielle", "Eltonius", "gabrielle@nexergroup.se", "0123456789");

        Subject subject1 = new Subject("C#");
        Subject subject2 = new Subject("Java");
        Subject subject3 = new Subject("Test");

        Teacher teacher1 = new Teacher("Pontus", "Redig", "pontus@iths.se");
        Teacher teacher2 = new Teacher("John", "Smith", "john@gmail.com");

        student1.addSubject(subject1);
        student1.addSubject(subject2);
        student2.addSubject(subject3);
        student2.addSubject(subject2);

        teacher1.addSubject(subject2);
        teacher2.addSubject(subject3);

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
    }
}
