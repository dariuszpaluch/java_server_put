package com.dariuszpaluch.models;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.glassfish.jersey.linking.InjectLink.Style.ABSOLUTE;

@XmlRootElement
public class Grade {
    @NotNull
    private int id;

    @NotNull
    private Double value;
    @NotNull
    private Date created;

    @NotNull
    private int courseId;
    @NotNull
    private String studentIndex;

    @InjectLinks({
            @InjectLink(value = "grades/{id}", bindings = {@Binding(name = "id", value = "${instance.id}")}, rel = "self", style = ABSOLUTE),
            @InjectLink(value = "grades", rel = "parent", style = ABSOLUTE),
    })
    @XmlElement(name = "link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

    public Grade() {
    }

    public Grade(Double value, Date created, int courseId, String studentIndex) {
        this.value = value;
        this.created = created;
        this.courseId = courseId;
        this.studentIndex = studentIndex;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }

    public int getId() {
        return id;
    }
}
