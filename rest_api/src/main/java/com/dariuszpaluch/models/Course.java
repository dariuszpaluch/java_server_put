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
import java.util.ArrayList;
import java.util.List;

import static org.glassfish.jersey.linking.InjectLink.Style.ABSOLUTE;

@XmlRootElement
public class Course {
    private static int idCounter = 0;
    @NotNull
    private int id;

    @NotNull
    private String name;
    @NotNull
    private String teacher;

    @InjectLinks({
            @InjectLink(value = "courses/{id}", bindings = {@Binding(name = "id", value = "${instance.id}")}, rel = "self", style = ABSOLUTE),
            @InjectLink(value = "courses", rel = "parent", style = ABSOLUTE),
            @InjectLink(value = "courses/{id}/grades",  bindings = {@Binding(name = "id", value = "${instance.id}")}, rel = "grades", style = ABSOLUTE)
    })
    @XmlElement(name = "link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

    public Course() {
        this.id = generateNewId();
    }

    public Course(String name, String teacher) {
        this.id = generateNewId();
        this.name = name;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    private int generateNewId() {
        idCounter +=1;
        return idCounter;
    }
}
