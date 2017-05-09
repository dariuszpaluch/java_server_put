package com.dariuszpaluch.models;

import com.dariuszpaluch.utils.ObjectIdJaxbAdapter;
import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

import static org.glassfish.jersey.linking.InjectLink.Style.ABSOLUTE;

@Entity("grades")
@XmlRootElement
public class Grade {
    @Id
    @XmlJavaTypeAdapter(ObjectIdJaxbAdapter.class)
    private ObjectId id;

    @NotNull
    private Double value;
    @NotNull
    private Date created;

    @NotNull
    @Reference
    private Student student;

    @NotNull
    @Reference
    private Course course;

    @InjectLinks({
            @InjectLink(value = "students/{index}/grades/{id}", bindings = {@Binding(name = "id", value = "${instance.id}"), @Binding(name = "index", value = "${instance.studentIndex}")}, rel = "self", style = ABSOLUTE),
            @InjectLink(value = "students/{index}/grades", bindings = {@Binding(name = "index", value = "${instance.studentIndex}")}, rel = "parent", style = ABSOLUTE),
    })
    @XmlElement(name = "link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

    public Grade() {
    }

    public Grade(Double value, Date created, Course course, Student student) {
        this.value = value;
        this.created = created;
        this.student = student;
        this.course = course;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
