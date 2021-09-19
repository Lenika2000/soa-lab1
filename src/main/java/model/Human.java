package model;

import model.typesForXml.JaxbHuman;
import model.typesForXml.LocalDateTimeAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Entity
@Table
@XmlRootElement(name = "governor")
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlElement
    private int id;
    @XmlElement
    private double height; //Значение поля должно быть больше 0
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private java.time.LocalDateTime birthday;

    public Human(int id, double height, LocalDateTime birthday) {
        this.id = id;
        this.height = height;
        this.birthday = birthday;
    }

    public Human() {
    }

    public void update(JaxbHuman humanData) {
        this.birthday = humanData.getBirthday();
        this.height = humanData.getHeight();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
}

