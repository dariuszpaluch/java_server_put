package com.dariuszpaluch.models;

import com.dariuszpaluch.resources.StudentResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.*;

import static org.glassfish.jersey.linking.InjectLink.Style.ABSOLUTE;

@XmlRootElement
public class Student extends Person {
    @NotNull
    private String index;

    @InjectLinks({
            @InjectLink(value = "students/{index}", bindings = {@Binding(name = "index", value = "${instance.index}")}, rel = "self", style = ABSOLUTE),
            @InjectLink(value = "students", rel = "parent", style = ABSOLUTE),
            @InjectLink(value = "students/{index}/grades",  bindings = {@Binding(name = "index", value = "${instance.index}")}, rel = "grades", style = ABSOLUTE)
    })
    @XmlElement(name = "link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

    public Student() {
    }

    public Student(String name, String surname, Date dateOfBirth, String index) {
        super(name, surname, dateOfBirth);
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
