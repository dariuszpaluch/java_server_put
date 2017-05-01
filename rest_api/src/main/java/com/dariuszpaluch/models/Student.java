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
public class Student extends Person{
    @NotNull
    private String index;

    @InjectLinks({
            @InjectLink(value = "students/{index}", bindings = { @Binding(name = "index", value = "${instance.index}") }, rel = "self", style=ABSOLUTE)
    })
    @XmlElement(name="link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

//    @Override
//    public Map<String, String> unmarshal(StrStrMyMap v) throws Exception {
//        Map<String, String> map = new HashMap<String,String>();
//        for (Iterator<StrStrKeyVal> it = v.map.iterator(); it.hasNext();) {
//            StrStrKeyVal strStr = it.next();
//            map.put(strStr.key, strStr.value);
//        }
//        return map;
//    }

//    private Set<Grade> grades;

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

//    public Set<Grade> getGrades() {
//        return grades;
//    }
//
//    public void setGrades(Set<Grade> grades) {
//        this.grades = grades;
//    }
}
