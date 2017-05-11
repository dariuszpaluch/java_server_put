package com.dariuszpaluch.models;

import com.dariuszpaluch.utils.ObjectIdJaxbAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.mongodb.morphia.annotations.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

import static org.glassfish.jersey.linking.InjectLink.Style.ABSOLUTE;

@Entity("students")
@XmlRootElement
public class Student {
  private static int indexCounter = 0;

  @XmlTransient
  @Id
  @XmlJavaTypeAdapter(ObjectIdJaxbAdapter.class)
  private ObjectId id;

  @Indexed(name = "index", unique = true)
  private int index;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @JsonFormat(shape = JsonFormat.Shape.STRING,
          pattern = "yyyy-MM-dd", timezone = "CET")
  private Date dateOfBirth;

  @InjectLinks({
          @InjectLink(value = "students/{index}", bindings = {@Binding(name = "index", value = "${instance.index}")}, rel = "self", style = ABSOLUTE),
          @InjectLink(value = "students", rel = "parent", style = ABSOLUTE),
          @InjectLink(value = "students/{index}/grades", bindings = {@Binding(name = "index", value = "${instance.index}")}, rel = "grades", style = ABSOLUTE)
  })
  @XmlElement(name = "link")
  @XmlElementWrapper(name = "links")
  @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
  List<Link> links;

  public Student() {
  }

  public Student(String firstName, String lastName, Date dateOfBirth) {
    this.index = generateNewIndex();
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
  }


  @XmlTransient
  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public int getIndex() {
    return index;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  private int generateNewIndex() {
    indexCounter += 1;
    return indexCounter;
  }
}
