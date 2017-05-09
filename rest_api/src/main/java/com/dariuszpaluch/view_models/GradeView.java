package com.dariuszpaluch.view_models;

import com.dariuszpaluch.models.Course;
import com.dariuszpaluch.models.Grade;
import com.dariuszpaluch.models.Student;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

import static org.glassfish.jersey.linking.InjectLink.Style.ABSOLUTE;

@XmlRootElement
public class GradeView {
    @NotNull
    Course course;

    @NotNull
    Student student;
    private static int idCounter = 0;
    @NotNull
    protected int id;

    @NotNull
    protected Double value;
    @NotNull
    protected String created;

    @InjectLinks({
            @InjectLink(value = "students/{index}/grades/{id}", bindings = {@Binding(name = "id", value = "${instance.id}"), @Binding(name = "index", value = "${instance.student.index}")}, rel = "self", style = ABSOLUTE),
            @InjectLink(value = "students/{index}/grades", bindings = {@Binding(name = "index", value = "${instance.student.index}")}, rel = "parent", style = ABSOLUTE),
    })
    @XmlElement(name = "link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

    public GradeView(Grade grade, Course course, Student student) {
        this.id = grade.getId();
        this.value = grade.getValue();
        this.created = grade.getCreated();
        this.course = course;
        this.student = student;
    }

    public GradeView() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        GradeView.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

//    public Grade getGrade() {
//        return new Grade(this.value, this.created, this.course.getId(), this.student.getIndex());
//    }
}
