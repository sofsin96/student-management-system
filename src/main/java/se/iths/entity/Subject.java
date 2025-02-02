package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    private String name;

    public Subject( @NotEmpty @NotNull String name) {
        this.name = name;
    }

    public Subject() {
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Student> students = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    public void addStudent(Student student) {
        this.students.add(student);
        student.getSubjects().add(this);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        student.getSubjects().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
